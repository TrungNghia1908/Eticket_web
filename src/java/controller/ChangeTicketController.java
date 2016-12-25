package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.*;
import bean.*;
import java.util.Arrays;

/**
 * @author A Di Đà Phật
 */
public class ChangeTicketController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                                HttpServletResponse response)
                                throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "/show_change_ticket/index.jsp";
        if (requestURI.endsWith("/showTicket")) {
            url = showTicket(request, response);
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                                HttpServletResponse response)
                                throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "/show_change_ticket/index.jsp";
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String showTicket(HttpServletRequest request,
                            HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String url = "/show_change_ticket/showTicket.jsp";
        String message = "";
                
        String pincode = request.getParameter("pincode");
        Ticket ticket = TicketDAO.select(pincode);
        if(ticket == null ) {
            message = "incorrect pin code";
        } else if (TicketDAO.checkTime(pincode) == false) {
            message = "your ticket is out of date";
        } else {
            
            session.setAttribute("oldTicket", ticket);
            List<Seat> seats = SeatDAO.selectSeats(ticket.getPinCode());
            session.setAttribute("oldSeatsBooked", seats);
            
            String [] seatsNo = new String[seats.size()];
            for (int i = 0; i < seats.size(); i++) {
                Seat s = seats.get(i);
                seatsNo[i] = s.getSeatNo() ;
            }
            
            session.setAttribute("oldSeatsNo", seatsNo);
            int busId = seats.get(0).getBusId();
            Bus bus = BusDAO.selectBus(busId);
            session.setAttribute("oldBus", bus);
            Trip trip = bus.getTrip();
            session.setAttribute("oldTrip", trip);
            
        }
        request.setAttribute("message", message);
        return url;
    }
}
