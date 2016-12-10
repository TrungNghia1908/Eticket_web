package controller;

import DAO.FeedbackDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.TripDAO;
import DAO.UserDAO;
import bean.Feedback;
import bean.TripList;
import bean.User;


public class UserController extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request,
                HttpServletResponse response)
                throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String url;
        
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
        } else if (requestURI.endsWith("/logout")) {
            url = logout(request, response);
        } else if (requestURI.endsWith("/removeAccount")) {
            url = removeAccount(request, response);
        } else if (requestURI.endsWith("/showProfile")) {
            url = showProfile(request, response);
        } else if (requestURI.endsWith("/editProfile")) {
            url = editProfile(request, response);
        } else if (requestURI.endsWith("/searchTrip")) {
            url = searchTrip(request, response);
        } else if (requestURI.endsWith("/sendFeedback")) {
            url = sendFeedback(request, response);
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
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
        
        // set Attribute for user
        request.setAttribute("user", user);
        
        String url;
        String message = "";
        
//        // Check exception condition
//        if (user.getEmail() == null || user.getFullName() == null ||
//            user.getPassword() == null || phoneParse == null ||
//            user.getUserName() == null || user.getEmail().equals("") ||
//            user.getFullName().equals("") || user.getPassword().equals("") ||
//            phoneParse.equals("") || user.getUserName().equals("")) {
//            
//            message = "You should fill out all the bland";
//            url = "/register.jsp";
//        }
        if (!user.getPassword().equals(conPassword)) {
            message = "The password not match";
            url = "/register.jsp";
        }
        else if (UserDAO.emailExist(email)) {
            message = "This email address already exists.<br>"
                    + "Please enter another email address.";
            url = "/register.jsp";
        }
        else if(UserDAO.userNameExist(userName)) {
            message = "This user name already exists.<br>"
                    + "Please enter another user name.";
            url = "/register.jsp";
        } else {
            UserDAO.insert(user);
            url = "/login.jsp";
        }
        request.setAttribute("message", message);
        return url;
    }

    private String login(HttpServletRequest request,
                                HttpServletResponse response) {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        String url;
        
        User user = UserDAO.select(email, password);
        if (user == null) {
            String message = "email or password incorrect";
            request.setAttribute("message", message);
            url = "/login.jsp";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            url = "/checkLogin.jsp";
        }
        
        return url;
    }

    private String logout(HttpServletRequest request,
                                HttpServletResponse response) {
        
        HttpSession session = request.getSession(false);
        session.removeAttribute("user");
        String message = "Logout already";
        request.setAttribute("message", message);
        
        return "/checkLogin.jsp";
    }

    private String removeAccount(HttpServletRequest request,
                                HttpServletResponse response) {
        String email = request.getParameter("userEmail");
        
        if (!UserDAO.removeUser(email)) {
            String message = "something wrong";
            request.setAttribute("message", message);
        } else {
            String message = "Remove accout successfull";
            request.setAttribute("message", message);
        }
        
        return "/adim/showCustomer.jsp";
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
            url = "/editProfile.jsp";
        }
        return url;
    }

    private String editProfile(HttpServletRequest request,
                                HttpServletResponse response) {
        
        String message;
        
        String fullName = request.getParameter("fullname");
//        String email = request.getParameter("email");
        String userName = request.getParameter("username");
        String phoneParse = request.getParameter("phonenumber");
        int phoneNumber = Integer.parseInt(phoneParse);
        
        HttpSession session = request.getSession();
        User oldUser = (User) session.getAttribute("user");
        
        if (UserDAO.userNameExist(userName) 
            && !userName.equals(oldUser.getUserName())) {
            message= "The user name all ready exit";
            request.setAttribute("message", message);
            return  "/editProfile.jsp";
        }
                
        User user = new User();
        user.setEmail(oldUser.getEmail());
        user.setFullName(fullName);
        user.setUserName(userName);
        user.setPoneNumber(phoneNumber);
        
        if (!UserDAO.updateInfo(user)) {
            message = "DataBase update false";
            request.setAttribute("message", message);
        } else {
            User newUser =
                UserDAO.select(oldUser.getEmail(), oldUser.getPassword());
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
        
        if(search == null || search.equals("")) {
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
                url = "/feedback/feedback.jsp";
            } else {
                message = "Thank for your contribute";
                url = "/feedback/feedback.jsp";
            }
        }
        request.setAttribute("message", message);
        
        return url;
    }
}
