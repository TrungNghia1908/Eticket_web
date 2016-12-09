package controller;

import DAO.TripDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Trip;
import bean.TripList;

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
        
        String requestURL = request.getRequestURI();
        String url = "";
        if (requestURL.endsWith("/addTrip")) {
            url = addTrip(request, response);
        } else if (requestURL.endsWith("/showTrip")) {
            url = showTrip(request, response);
        } else if (requestURL.endsWith("/editTrip")) {
            url = editTrip(request, response);
        } else if (requestURL.endsWith("/removeTrip")) {
            url = removeTrip(request, response);
        }
        
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
        String message = "";
        
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
            
            TripDao.insert(trip);
            message = "add trip success";
            url = "/adim/addTrip.jsp";
        }
        request.setAttribute("message", message);
        return url;
    }

    private String showTrip(HttpServletRequest request,
            HttpServletResponse response) {
        int tripId = Integer.parseInt(request.getParameter("tripId"));
        Trip trip = TripDao.select(tripId);
        request.setAttribute("trip", trip);
       
        return "/adim/editTrip.jsp";
    }
    
    private String editTrip(HttpServletRequest request,
                            HttpServletResponse response) {
        
        int price = Integer.parseInt(request.getParameter("price"));
        int tripId = Integer.parseInt(request.getParameter("tripId"));
//        String 
        
        String url;
        
        if(TripDao.updateTrip(tripId, price) != 0) {
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
        
        
        TripDao.removeTrip(tripId);
        TripList tripList = TripDao.select(searchKey);
        request.getSession().setAttribute("tripList", tripList);
        
        return "/search.jsp";
    }
}
