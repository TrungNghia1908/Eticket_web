package controller;

import DAO.FeedbackDAO;
import DAO.ReportDAO;
import DAO.TripDAO;
import DAO.UserDAO;
import bean.FeedbackList;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Trip;
import bean.TripList;
import bean.UserList;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author A Di Đà Phật
 */
public class AdminController extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest request,
                        HttpServletResponse response)
                        throws ServletException, IOException {
        
        String requestURL = request.getRequestURI();
        String url = "/login.jsp";
        if (requestURL.endsWith("/showTrip")) {
            url = showTrip(request, response);
        } else if (requestURL.endsWith("/removeTrip")) {
            url = removeTrip(request, response);
        } else if (requestURL.endsWith("/removeAccount")) {
            url = removeAccount(request, response);
        }
        // forward the request, response
        getServletContext().
                getRequestDispatcher(url).
                forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                            HttpServletResponse response)
                            throws ServletException, IOException {
        
        //get url form request
        String requestURL = request.getRequestURI();
        String url = "/login.jsp"; // default url
        
        //scan the url, forward request to true case
        if (requestURL.endsWith("/addTrip")) {
            url = addTrip(request, response);
        } else if (requestURL.endsWith("/editTrip")) {
            url = editTrip(request, response);
        } else if (requestURL.endsWith("/search")) {
            url = search(request, response);
        } else if (requestURL.endsWith("/ViewBooking")) {
            url = "/show_change_ticket/index.jsp";
        } else if (requestURL.endsWith("/showFeedback")) {
            url = showFeedback(request, response);
        } else if (requestURL.endsWith("/showCustomer")) {
            url = showCustomer(request, response);
        } else if (requestURL.endsWith("/displayReport")) {
            displayReport(request, response);
        }
        
        
        // forward the request, response
        getServletContext().
                getRequestDispatcher(url).
                forward(request, response);
    }

    private String search(HttpServletRequest request,
                                HttpServletResponse response) {
        
        String search = request.getParameter("searchKey");
        
        String url;
        
        if(search == null || search.equals("")) {
            url = "/adminpage/search.jsp";
        } else {
            TripList tripList = TripDAO.select(search);
            if (tripList.isEmpty()) {
                String message = "There are not exit these trips";
                request.setAttribute("message", message);
                url = "/adminpage/search.jsp";
            } else {
                String message = "Search success";
                request.setAttribute("message", message);
                request.getSession().setAttribute("tripList", tripList);
                url = "/adminpage/search.jsp";
            }
        }
        return url;
    }
    
    private String removeAccount(HttpServletRequest request,
                                HttpServletResponse response) {
        String message;
        String email = request.getParameter("userEmail");
        
        if (!UserDAO.removeUser(email)) {
            message = "something wrong";
            request.setAttribute("message", message);
        } else {
            message = "Remove accout successfull";
            request.setAttribute("message", message);
        }
        
        return "/adminpage/index.jsp";
    }
    
    private String addTrip(HttpServletRequest request,
                        HttpServletResponse response) {
        
        String arrival = request.getParameter("arrival");
        String destination = request.getParameter("destination");
        String price = request.getParameter("price");

        String url;
        String message;
        
        if (arrival == null || arrival.equals("") ||
            destination == null || destination.equals("") 
                || price == null || price.equals("")
                ) {
            message = "you should fill out all the blank";
            url = "/adminpage/addTrip.jsp";
        } else {
            Trip trip = new Trip();
            trip.setArrival(arrival);
            trip.setDestination(destination);
            trip.setPrice(Integer.parseInt(price));
            
            TripDAO.insert(trip);
            message = "add trip success";
            url = "/adminpage/addTrip.jsp";
        }
        request.setAttribute("message", message);
        return url;
    }

    private String showTrip(HttpServletRequest request,
            HttpServletResponse response) {
        int tripId = Integer.parseInt(request.getParameter("tripId"));
        Trip trip = TripDAO.select(tripId);
        request.setAttribute("trip", trip);
       
        return "/adminpage/editTrip.jsp";
    }
    
    private String editTrip(HttpServletRequest request,
                            HttpServletResponse response) {
        
        int price = Integer.parseInt(request.getParameter("price"));
        int tripId = Integer.parseInt(request.getParameter("tripId"));
        
        String url;
        
        if(TripDAO.updateTrip(tripId, price) == 0) {
            request.setAttribute("message", "data fail update");
            url = "/adminpage/editTrip.jsp";
        } else {
            url = "/adminpage/search.jsp";
        }
        
        return url;
    }
    
    private String removeTrip(HttpServletRequest request,
                                HttpServletResponse response) {
        
        int tripId = Integer.parseInt(request.getParameter("tripId"));
        String searchKey = request.getParameter("searchKey");
        
        TripDAO.removeTrip(tripId);
        TripList tripList = TripDAO.select(searchKey);
        request.getSession().setAttribute("tripList", tripList);
        
        return "/adminpage/search.jsp";
    }
    
    private String showFeedback(HttpServletRequest request,
                                HttpServletResponse response) {
        
        FeedbackList feedbackList = new FeedbackList();
        feedbackList.setFeedbacks(FeedbackDAO.select());
    
        String url;
        String message;
        
        request.setAttribute("feedbackList", feedbackList);
        message = "feedback list";
        
        url = "/adminpage/showFeedback.jsp";
        request.setAttribute("message", message);
        
        return url;
    }
    
    private String showCustomer (HttpServletRequest request,
                                 HttpServletResponse response) {
        UserList userList = new UserList();
        userList.setUsers(UserDAO.select());
       
        String message;
        String url;
        
        request.setAttribute("userList", userList);
        message = "List of Customer:";
        
        url = "/adminpage/showCustomer.jsp";
        request.setAttribute("message", message);
        
        return url;
    }

    private void displayReport(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        
        String reportName = request.getParameter("reportName");
        
        Workbook workbook;
        if(reportName.equalsIgnoreCase("userList")) {
            workbook = ReportDAO.getUserList();
        } else if (reportName.equalsIgnoreCase("FeedbackList")) {
            workbook = ReportDAO.getFeedbackList();
        } else {
            workbook = new HSSFWorkbook();
        }
        
        
        response.setHeader("content-disposition",
                "attachment; filename=" + reportName + ".xls");
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        }
    }
}
