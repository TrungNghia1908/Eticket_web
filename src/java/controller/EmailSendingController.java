package controller;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import util.EmailUtility;
import DAO.UserDAO;

import bean.Bus;
import bean.Ticket;
import bean.Trip;
import bean.User;

public class EmailSendingController extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;
    
    @Override
    public void init() {
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) 
                          throws ServletException, IOException {
        
        String url = "/login.jsp";
        String requestURI = request.getRequestURI();
        
        if (requestURI.endsWith("/thanksMail")) {
            url = thanksMail (request, response);
        } else if (requestURI.endsWith("/registerMail")) {
            url = registerMail(request, response);
        } else if (requestURI.endsWith("/forgetPass")) {
            url = forgetPassMail(request, response);
        }
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
        
    }
    
     protected void goGet(HttpServletRequest request,
                HttpServletResponse response) throws ServletException, IOException {
          doPost( request,response);
     }

    private String thanksMail(HttpServletRequest request,
                                HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("user");
        Ticket ticket = (Ticket) session.getAttribute("ticket");
        Trip t = (Trip)session.getAttribute("trip");
        Bus b = (Bus)session.getAttribute("bus");
        String[] seatNo = (String[]) session.getAttribute("seatsNo");
        
        // send email to user
            String to = u.getEmail();
            String from = "meoconthichhoc@gmail.com";
            String subject = "chuyenxedem";
            String body = "Dear " + u.getFullName() + ",\n\n"
                    + "You just booking tikcet from chuyenxedem website \n"
                    + "\n=====================================\n"
                    + "Your ticket information: .\n"
                    + "Your Full Name:  " + u.getFullName() + ",\n\n"
                    + "Email:  " + u.getEmail()+ ",\n\n"
                    + "Phone Number " + u.getPhoneNumber()+ ",\n\n"
                    + "Trip: " + t.getArrival() + " to " + t.getDestination() + ",\n\n"
                    + "Pin_Code: " + ticket.getPinCode() + ",\n\n"
                    + "Ticket Price: " + ticket.getTicketPrice() + ",\n\n"
                    + "Bus Number: " + b.getBusNo() + ",\n\n"
                    + "Run Time:  " + ticket.getTimeAvailable()+ ",\n\n"
                    + "Drivet Name " + b.getDriverName()+ ",\n\n"
                    + "Your seat No " + Arrays.toString(seatNo)+ ",\n\n"
                    + "Your seat No " + "http://localhost:8080/Eticket_Project/user/profile"+ ",\n\n"
                    + "Have a great day and thanks again!\n\n"
                    + "Nguyen Trung Nghia\n"
                    + "A Di Da Phat";
            boolean isBodyHTML = false;
        
            try {
                EmailUtility.sendEmail(host, port, user, pass, to, body, isBodyHTML, subject);
            } catch (MessagingException e) {
                this.log(
                        "Unable to send email. \n"
                        + "Here is the email you tried to send: \n"
                        + "=====================================\n"
                        + "TO: " + "meoconthichhoc@gmail.com" + "\n"
                        + "FROM: " + from + "\n"
                        + "SUBJECT: " + subject + "\n"
                        + "\n"
                        + body + "\n\n");
            }
            
            String resultMessage = "Booking success thank for using our service\n"
                        + "We have send an email for you to check information";
            request.setAttribute("message", resultMessage);
        
        return "/credit_card/thanks.jsp";
    }

    private String registerMail(HttpServletRequest request,
                                HttpServletResponse response) {
        User u = (User)request.getAttribute("user");
        
        String to = u.getEmail();
            String from = "meoconthichhoc@gmail.com";
            String subject = "chuyenxedem";
            String body = "Dear " + u.getFullName() + ",\n\n"
                    + "You just register an account from chuyenxedem.com \n"
                    + "\n=====================================\n"
                    + "To comfirm account forward to this link: .\n"
                    + "Your Full Name:  " + u.getFullName() + ",\n\n"
                    + "Email:  " + u.getEmail()+ ",\n\n"
                    + "Phone Number " + u.getPhoneNumber()+ ",\n\n"
                    + "http://localhost:8080/Eticket_Project/user/confirmAccount?idConfirm="+u.getHashSaltPass()+"\n\n"
                    + "Have a great day and thanks again!\n\n"
                    + "Nguyen Trung Nghia\n"
                    + "A Di Da Phat";
            boolean isBodyHTML = false;
            try {
                EmailUtility.sendEmail(host, port, user, pass, to, body, isBodyHTML, subject);
            } catch (MessagingException e) {
                this.log(
                        "Unable to send email. \n"
                        + "Here is the email you tried to send: \n"
                        + "=====================================\n"
                        + "TO: " + to + "\n"
                        + "FROM: " + from + "\n"
                        + "SUBJECT: " + subject + "\n"
                        + "\n"
                        + body + "\n\n");
            }
        String resultMessage = "Register success please confirm the account"
                                + "by your email to login";
        request.setAttribute("message", resultMessage);    
        
        return "/registerConfirm.jsp";
    }
    
    private String forgetPassMail(HttpServletRequest request,
                                HttpServletResponse response) {
        String email = request.getParameter("email");
        User u = UserDAO.select(email);
        
        String to = u.getEmail();
            String from = "meoconthichhoc@gmail.com";
            String subject = "chuyenxedem";
            String body = "Dear " + u.getFullName() + ",\n\n"
                    + "To change password forward this link \n"
                    + "\n=====================================\n"
                    + "http://localhost:8080/Eticket_Project/user/forgetPass?email=" + u.getEmail()+ "&passId="+u.getHashSaltPass()+ "\n\n"
                    + "Have a great day and thanks again!\n\n"
                    + "Nguyen Trung Nghia\n"
                    + "A Di Da Phat";
            boolean isBodyHTML = false;
            try {
                EmailUtility.sendEmail(host, port, user, pass, to, body, isBodyHTML, subject);
            } catch (MessagingException e) {
                this.log(
                        "Unable to send email. \n"
                        + "Here is the email you tried to send: \n"
                        + "=====================================\n"
                        + "TO: " + to + "\n"
                        + "FROM: " + from + "\n"
                        + "SUBJECT: " + subject + "\n"
                        + "\n"
                        + body + "\n\n");
            }
        String resultMessage = "Please check your email to change password";
        request.getSession().setAttribute("user", u);
        request.setAttribute("message", resultMessage);    
        
        return "/changePasswordComfirm.jsp";
    }
}