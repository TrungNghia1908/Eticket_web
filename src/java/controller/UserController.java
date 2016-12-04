package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import DAO.UserDao;
import javax.servlet.http.HttpSession;

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
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
        String url = "";
        if (requestURI.endsWith("/register")) {
            url = register(request, response);
        } else if (requestURI.endsWith("/login")) {
            url = login(request, response);
        } else if (requestURI.endsWith("/logout")) {
            url = logout(request, response);
        } else if (requestURI.endsWith("/removeAccount")) {
            url = removeAccount(request, response);
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
        String phoneNumber = request.getParameter("phonenumber");

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
        
        // Check exception condition
        if (user.getEmail() == null || user.getFullName() == null ||
            user.getPassword() == null || user.getPhoneNumber() == null ||
            user.getUserName() == null || user.getEmail().equals("") ||
            user.getFullName().equals("") || user.getPassword().equals("") ||
            user.getPhoneNumber().equals("") || user.getUserName().equals("")) {
            
            message = "Your should fill out all the bland";
            url = "/register.jsp";
        }
        else if (!user.getPassword().equals(conPassword)) {
            message = "The password not match";
            url = "/register.jsp";
        }
        else if (UserDao.emailExist(email)) {
            message = "This email address already exists.<br>"
                    + "Please enter another email address.";
            url = "/register.jsp";
        }
        else if(UserDao.userNameExist(userName)) {
            message = "This user name already exists.<br>"
                    + "Please enter another user name.";
            url = "/register.jsp";
        } else {
            UserDao.insert(user);
            url = "/thanks.jsp";
        }
        request.setAttribute("message", message);
        return url;
    }

    private String login(HttpServletRequest request,
                            HttpServletResponse response) {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        String url = "";
        
        User user = UserDao.selectUser(email, password);
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
        
        if (!UserDao.removeUser(email)) {
            String message = "something wrong";
            request.setAttribute("message", message);
        } else {
            String message = "Remove accout successfull";
            request.setAttribute("message", message);
        }
        
        return "/checkLogin.jsp";
    }
}