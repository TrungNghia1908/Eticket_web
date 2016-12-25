package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.FeedbackDAO;
import DAO.TripDAO;
import DAO.UserDAO;
import bean.Feedback;
import bean.TripList;
import bean.User;
import java.util.ArrayList;
import util.CookieUtil;
import util.PasswordUtil;

public class UserController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String url = "";
        String requestURI = request.getRequestURI();
        String action = request.getParameter("action");

        if (requestURI.endsWith("/confirmAccount")) {
            url = confirmAccount(request, response);
        } else if (requestURI.endsWith("/forgetPass")) {
            url = forgetPass(request, response);
        } else if (action == null) {
            User user = checkCookie(request);
            if (user == null) {
                url = "/login.jsp";
            } else {
                HttpSession session = request.getSession();
                session = request.getSession();
                session.setAttribute("user", user);
                if (user.getEmail().equals("North@gmail.com")) {
                    url = "/adminpage/index.jsp";
                } else {
                    url = "/checkLogin.jsp";
                }
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        // DeteCookies in future
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String url = "/login.jsp";
        if (requestURI.endsWith("/register")) {
            url = register(request, response);
        } else if (requestURI.endsWith("/login")) {
            url = login(request, response);
        } else if (requestURI.endsWith("/editProfile")) {
            url = editProfile(request, response);
        } else if (requestURI.endsWith("/changePass")) {
            url = changePass(request, response);
        } else if (requestURI.endsWith("/logout")) {
            url = logout(request, response);
        } else if (requestURI.endsWith("/showProfile")) {
            url = showProfile(request, response);
        } else if (requestURI.endsWith("/searchTrip")) {
            url = searchTrip(request, response);
        } else if (requestURI.endsWith("/sendFeedback")) {
            url = sendFeedback(request, response);
        } else if (requestURI.endsWith("/showSchedule")) {
            url = showSchedule(request, response);
        } else if (requestURI.endsWith("/goregister")) {
            url = "/register.jsp";
        } else if (requestURI.endsWith("/profile")) {
            url = "/checkLogin.jsp";
        } else if (requestURI.endsWith("/Feedback")) {
            url = "/userpage/feedback.jsp";
        } else if (requestURI.endsWith("/ViewBooking")) {
            url = "/show_change_ticket/index.jsp";
        } else if (requestURI.endsWith("/forgetpass")) {
            url = "/forgetPass.jsp";
        } else if (requestURI.endsWith("/goLogin")) {
            url = "/login.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private User checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        } else {
            String email = CookieUtil.getCookieValue(cookies, "cookEmail");
            String password = CookieUtil.getCookieValue(cookies, "cookPass");
            if (!email.trim().isEmpty() && !password.trim().isEmpty()) {
                User user = UserDAO.select(email, password);
                return user;
            }
        }
        return null;
    }

    private String register(HttpServletRequest request,
            HttpServletResponse response) {

        // get all parameter we need
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String conPassword = request.getParameter("conpassword");
        String phoneParse = request.getParameter("phonenumber");
        int phoneNumber = Integer.parseInt(phoneParse);

        // Store in the bean
        User user = new User();
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPassword(password);
        user.setUserName(userName);
        user.setPoneNumber(phoneNumber);

        // get stalt for the password
        String stalt = PasswordUtil.getSalt();
        String hashStaltPass = null;
        try {
            hashStaltPass = PasswordUtil.hashAndSaltPassword(password, stalt);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // set saltPass and HashSaltPass into User
        user.setSaltPass(stalt);
        user.setHashSaltPass(hashStaltPass);

        // set Attribute for user
        request.setAttribute("user", user);

        String url;
        String message = "";

        if (!user.getPassword().equals(conPassword)) {
            message = "The password not match";
            url = "/register.jsp";
        } else if (UserDAO.emailExist(email)) {
            message = "This email address already exists.<br>"
                    + "Please enter another email address.";
            url = "/register.jsp";
        } else if (UserDAO.userNameExist(userName)) {
            message = "This user name already exists.<br>"
                    + "Please enter another user name.";
            url = "/register.jsp";
        } else {
            UserDAO.insert(user);
//            message = "Register Successfully";
            url = "/email/registerMail";
        }
        request.setAttribute("message", message);
        return url;
    }

    private String login(HttpServletRequest request,
            HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // get staltPass form database
        String salt = UserDAO.getSalt(email);
        // get hashStalt
        String hashStalPass = null;
        try {
            hashStalPass = PasswordUtil.hashAndSaltPassword(password, salt);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String url;
        User user = UserDAO.select(email, hashStalPass);
        if (user == null) {
            String message = "email or password incorrect";
            request.setAttribute("message", message);
            url = "/login.jsp";
        } else if (user.getVail().equals("N")) {
            String message = "you need confirm account by your email";
            request.setAttribute("message", message);
            url = "/login.jsp";
        } else {
            // remember me function
            if (request.getParameter("remember") != null) {

                String remember = request.getParameter("remember");
                Cookie cEmail = new Cookie("cookEmail", user.getEmail());
                Cookie cPassword = new Cookie("cookPass", user.getHashSaltPass());
                Cookie cRemember = new Cookie("cookRemem", remember);

                cEmail.setMaxAge(60 * 60 * 24 * 15); //15 days
                cPassword.setMaxAge(60 * 60 * 24 * 15);
                cRemember.setMaxAge(60 * 60 * 24 * 15);

                response.addCookie(cEmail);
                response.addCookie(cPassword);
                response.addCookie(cPassword);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (user.getEmail().equals("north@gmail.com")) {
                url = "/adminpage/index.jsp";
            } else {
                url = "/checkLogin.jsp";
            }
        }

        return url;
    }

    private String logout(HttpServletRequest request,
            HttpServletResponse response) {

        Cookie cUserName = new Cookie("cookEmail", null);
        Cookie cPassword = new Cookie("cookPass", null);
        Cookie cRemember = new Cookie("cookrem", null);

        cUserName.setMaxAge(0);
        cPassword.setMaxAge(0);
        cRemember.setMaxAge(0);

        response.addCookie(cUserName);
        response.addCookie(cPassword);
        response.addCookie(cRemember);

        HttpSession session = request.getSession();
        session.removeAttribute("user");
        String message = "Logout already";
        request.setAttribute("message", message);

        return "/login.jsp";
    }

    private String showProfile(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String url;
        if (user == null) {
            String message = "To edit profile your must login fist";
            request.setAttribute("message", message);
            url = "/login.jsp";
        } else {
            url = "/userpage/editProfile.jsp";
        }
        return url;
    }

    private String editProfile(HttpServletRequest request,
            HttpServletResponse response) {

        String message;

        String fullName = request.getParameter("fullname");
//        String email = request.getParameter("email");
        String oldpass = request.getParameter("oldpass");
        String passnew = request.getParameter("passnew");
        String userName = request.getParameter("username");
        String phoneParse = request.getParameter("phonenumber");
        int phoneNumber = Integer.parseInt(phoneParse);

        HttpSession session = request.getSession();
        User oldUser = (User) session.getAttribute("user");

        if (UserDAO.userNameExist(userName)
                && !userName.equals(oldUser.getUserName())) {
            message = "The user name all ready exit";
            request.setAttribute("message", message);
            return "/userpage/editProfile.jsp";
        }
        
        
        User user = new User();
        user.setEmail(oldUser.getEmail());
        user.setFullName(fullName);
        user.setUserName(userName);
        user.setPoneNumber(phoneNumber);
        if (!passnew.equals("")) {
            if (!oldUser.getPassword().equals(oldpass)) {
                message = "The password not match";
                request.setAttribute("message", message);
                return "/userpage/editProfile.jsp";
            }
            user.setPassword(passnew);
            //get stalt and hashStalt password
            String stalt = PasswordUtil.getSalt();
            String hashStalt = null;
            try {
                hashStalt = PasswordUtil.hashAndSaltPassword(passnew, stalt);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // set stalt and hashStalt into user
            user.setSaltPass(stalt);
            user.setHashSaltPass(hashStalt);
        } 
        else 
        {
             user.setPassword(oldUser.getPassword());
             user.setSaltPass(oldUser.getSaltPass());
             user.setHashSaltPass(oldUser.getHashSaltPass());
        }
           
        
        if (!UserDAO.updateInfo(user)) {
            message = "DataBase update false";
            request.setAttribute("message", message);
        } else { 
            User newUser
                    = UserDAO.select(oldUser.getEmail(), user.getHashSaltPass());
            session.setAttribute("user", newUser);
            message = "Update successfull";
            request.setAttribute("message", message);
        }
        return "/checkLogin.jsp";
    }

    private String searchTrip(HttpServletRequest request,
            HttpServletResponse response) {

        String search = request.getParameter("searchKey");

        String url;

        if (search == null || search.equals("")) {
            url = "/search.jsp";
        } else {
            TripList tripList = TripDAO.select(search);
            if (tripList.isEmpty()) {
                String message = "There are not exit these trips";
                request.setAttribute("message", message);
                url = "/search.jsp";
            } else {
                String message = "Search success";
                request.setAttribute("message", message);
                request.getSession().setAttribute("tripList", tripList);
                url = "/search.jsp";
            }
        }
        return url;
    }

    private String sendFeedback(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String url;
        String message;

        if (user == null) {
            message = "To write feedback please login first";
            url = "/login.jsp";
        } else {

            Feedback feedback = new Feedback();
            feedback.setComment(request.getParameter("comment"));
            feedback.setSubject(request.getParameter("subject"));
            feedback.setUser(user);

            if (FeedbackDAO.insert(feedback) == 0) {
                message = "Data faid insert feedback!";
                url = "/userpage/feedback.jsp";
            } else {
                message = "Thank for your contribute";
                url = "/userpage/feedback.jsp";
            }
        }
        request.setAttribute("message", message);

        return url;
    }

    private String confirmAccount(HttpServletRequest request,
            HttpServletResponse response) {
        String idConfirm = request.getParameter("idConfirm");
        UserDAO.confirmAccount(idConfirm);
        String message = "confirm account success you can login now";
        request.setAttribute("message", message);
        return "/login.jsp";
    }

    private String forgetPass(HttpServletRequest request,
            HttpServletResponse response) {
        String url = "/changePassword.jsp";
        String email = request.getParameter("email");
        String pass = request.getParameter("passId");

        User u = (User) UserDAO.select(email, pass);
        request.getSession().setAttribute("user", u);
        return url;
    }

    private String changePass(HttpServletRequest request,
            HttpServletResponse response) {

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        String newPass = request.getParameter("newPass");
        String conPass = request.getParameter("conPass");

        String message;
        String url;

        if (!newPass.equals(conPass)) {
            message = "password not match!";
            request.setAttribute("message", message);
            url = "/changePassword.jsp";
        } else {
            // get stalt for the password
            String stalt = PasswordUtil.getSalt();
            String hashStaltPass = null;
            try {
                hashStaltPass = PasswordUtil.hashAndSaltPassword(newPass, stalt);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // set saltPass and HashSaltPass into User
            User newUser = new User();
            newUser = u;
            newUser.setPassword(newPass);
            newUser.setSaltPass(stalt);
            newUser.setHashSaltPass(hashStaltPass);
            request.setAttribute("u", newUser);

            if (!UserDAO.updateInfo(u)) {
                message = "update data fail";
            } else {
                message = "change password success";
            }
            url = "/login.jsp";
        }
        request.setAttribute("message", message);
        return url;
    }

    private String showSchedule(HttpServletRequest request,
            HttpServletResponse response) {
        ArrayList schedule = (ArrayList) TripDAO.selectTripTable();
        request.getSession().setAttribute("schedule", schedule);
        return "/schedule.jsp";
    }
}
