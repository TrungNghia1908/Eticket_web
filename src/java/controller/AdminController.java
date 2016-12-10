package controller;

import DAO.FeedbackDAO;
import DAO.TripDAO;
import DAO.UserDAO;
import bean.FeedbackList;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Trip;
import bean.TripList;
import bean.UserList;

/**
 * @author A Di Đà Phật
 */
public class AdminController extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest request,
                        HttpServletResponse response)
                        throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        String url;
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
        } else if (requestURL.endsWith("/showTrip")) {
            url = showTrip(request, response);
        } else if (requestURL.endsWith("/editTrip")) {
            url = editTrip(request, response);
        } else if (requestURL.endsWith("/removeTrip")) {
            url = removeTrip(request, response);
        } else if (requestURL.endsWith("/showFeedback")) {
            url = showFeedback(request, response);
        } else if (requestURL.endsWith("/showCustomer")) {
            url = showCustomer(request, response);
        }
        
        // forward the request, response
        getServletContext().
                getRequestDispatcher(url).
                forward(request, response);
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
            message = "you should fill out all the bland";
            url = "/adim/addTrip.jsp";
        } else {
            Trip trip = new Trip();
            trip.setArrival(arrival);
            trip.setDestination(destination);
            trip.setPrice(Integer.parseInt(price));
            
            TripDAO.insert(trip);
            message = "add trip success";
            url = "/adim/addTrip.jsp";
        }
        request.setAttribute("message", message);
        return url;
    }

    private String showTrip(HttpServletRequest request,
            HttpServletResponse response) {
        int tripId = Integer.parseInt(request.getParameter("tripId"));
        Trip trip = TripDAO.select(tripId);
        request.setAttribute("trip", trip);
       
        return "/adim/editTrip.jsp";
    }
    
    private String editTrip(HttpServletRequest request,
                            HttpServletResponse response) {
        
        int price = Integer.parseInt(request.getParameter("price"));
        int tripId = Integer.parseInt(request.getParameter("tripId"));
        
        String url;
        
        if(TripDAO.updateTrip(tripId, price) != 0) {
            request.setAttribute("message", "data fail update");
            url = "/adim/editTrip.jsp";
        } else {
            url = "/search.jsp";
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
        
        return "/search.jsp";
    }
    
    private String showFeedback(HttpServletRequest request,
                                HttpServletResponse response) {
        
        FeedbackList feedbackList = new FeedbackList();
        feedbackList.setFeedbacks(FeedbackDAO.select());
    
        String url;
        String message;
        
        request.setAttribute("feedbackList", feedbackList);
        message = "feedback list";
        
        url = "/adim/showFeedback.jsp";
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
        
        url = "/adim/showCustomer.jsp";
        request.setAttribute("message", message);
        
        return url;
    }
}
