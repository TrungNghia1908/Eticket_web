package controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DAO.*;
import bean.*;
import util.PinCode;

/**
 * @author A Di Đà Phật
 */
public class BookTicketController extends HttpServlet {

    private static final String defaultURL = "/book_ticket/index.jsp";

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String url = defaultURL;
        if (requestURI.endsWith("/selectDate")) {
            url = selectDate(request, response);
        } else if (requestURI.endsWith("/selectTime")) {
            try {
                url = selectTime(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BookTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (requestURI.endsWith("/change")) {
//            url = change(request, response);
        } else if (requestURI.endsWith("/payFee")) {
            url = "/credit_card/index.jsp";
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
        String url = defaultURL;

        if (requestURI.endsWith("/selectSeat")) {
            url = selectSeat(request, response);
        } else if (requestURI.endsWith("/payProcess")) {
            url = payProcess(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String selectDate(HttpServletRequest request,
            HttpServletResponse response) {

        String url = "/book_ticket/index.jsp";
        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("user");
        if (u == null)
        {
            String message = "You must login to do booking";
            request.setAttribute("message", message);
            url = "/login.jsp";
        }
        int tripId = Integer.parseInt(request.getParameter("tripId"));
        request.setAttribute("tripId", tripId);
        Trip trip = TripDAO.select(tripId);
        request.getSession().setAttribute("trip", trip);

        return url;
    }

    private String selectTime(HttpServletRequest request,
            HttpServletResponse response)
            throws ClassNotFoundException {

        String url = "/book_ticket/selectTime.jsp";
        HttpSession session = request.getSession();

        // set Date into ticket
        String date = request.getParameter("date");
        if (date != null) {
            Ticket ticket = new Ticket();
            ticket.setDateBooking(date);
            session.setAttribute("ticket", ticket);
        }

        // get trip in session
        Trip trip = (Trip) session.getAttribute("trip");
        List<Time> times = new ArrayList<>();

        // get run time by trip Id
        times = BusDAO.selectRunTime(trip.getTripId());
        session.setAttribute("times", times);
        // get Run time from view to select seat
        String timeString = request.getParameter("runTime");
        if (timeString != null) {
            Time runTime = Time.valueOf(timeString);

            // select bus by runTime and tripId to view seat for customer
            Bus bus = BusDAO.selectBus(trip.getTripId(), runTime);
            session.setAttribute("bus", bus);
        }
        return url;
    }

    private String selectSeat(HttpServletRequest request,
            HttpServletResponse response) {

        String url = "/book_ticket/complete.jsp";
        HttpSession session = request.getSession();

        Ticket ticket = (Ticket) session.getAttribute("ticket");
        User user = (User) session.getAttribute("user");
        Trip trip = (Trip) session.getAttribute("trip");
        Bus bus = (Bus) session.getAttribute("bus");

        //set PinCode for ticket
        PinCode pincodeCreater = new PinCode();
        String pincode = pincodeCreater.getPinCode();
        ticket.setPinCode(pincode);

        // Set user into tiket object
        ticket.setUser(user);

        String[] seatsNo = request.getParameterValues("seatNo");
        if(seatsNo == null)
        {
            String message="Please choose the seat";
            request.setAttribute("message", message); 
            url="/book_ticket/selectTime.jsp";
        }
        else
        {
            session.setAttribute("seatsNo", seatsNo);

        // set the seat list of ticket
        List<Seat> seats = new ArrayList<>();
        for (String seatsNo1 : seatsNo) {
            Seat s = new Seat();
            s.setBusId(bus.getBusId());
            s.setPinCode(pincode);
            s.setSetNo(seatsNo1);
            seats.add(s);
        }
        session.setAttribute("seatBooked", seats);

        // set number of ticket into Ticket object
        int numOfTicket = seatsNo.length;
        ticket.setNumOfTicket(numOfTicket);

        // set ticket Price into ticket object.
        int tripPrice = trip.getPrice();
        int ticketPrice = tripPrice * numOfTicket;
        ticket.setTicketPrice(ticketPrice);

        // set time available into ticket object
        String date = ticket.getDateBooking();
        String time = bus.getRuntime().toString();
        ticket.setTimeAvailable(date, time);
        }
        return url;
    }

    private String payProcess(HttpServletRequest request,
            HttpServletResponse response) {

        HttpSession session = request.getSession();
        String status = (String) session.getAttribute("status");
        Ticket t = (Ticket) session.getAttribute("ticket");
        List<Seat> seats = (List<Seat>) session.getAttribute("seatBooked");
        Ticket oldT = (Ticket) session.getAttribute("oldTicket");

        String url = "/email/thanksMail";
        String message = "";

        // get cerdit card account
        int cardId = Integer.parseInt(request.getParameter("cardId"));
        CreditCard card = CreditCardDAO.selsect(cardId);
        if (card == null) {
            message = "Invalid creadit card";
            url = "/credit_card/index.jsp";
        } else {
            int pay;
            if (status != null) { // Change ticket process
                // caculate the price
                pay = t.getTicketPrice() - oldT.getTicketPrice();

                if (card.getAmount() < pay) { // account not enough money to pay
                    message = "Your accout not enough money to pay";
                    url = "/credit_card/index.jsp";
                } else {

                    CreditCardDAO.payFee(cardId, pay); // payment

                    // get the oldSeats to cancer booking
                    List<Seat> oldSeats = (List<Seat>) session.getAttribute("oldSeatsBooked");
                    for (Seat seat : oldSeats) {
                        SeatDAO.resetSeat(seat);
                    }

                    TicketDAO.deleteTicket(oldT.getPinCode()); // delete old ticket

                    for (Seat seat : seats) { //book the new seat
                        SeatDAO.checkSeat(seat);
                    }
                    TicketDAO.insert(t);//add ticket
                }
            } else { // Booking new ticket process
                // caculate the price
                pay = t.getTicketPrice();

                if (card.getAmount() < pay) {
                    message = "Your accout not enough money to pay";
                    url = "/credit_card/index.jsp";
                } else {
                    // payment
                    CreditCardDAO.payFee(cardId, pay);

                    //book the seat
                    for (Seat seat : seats) {
                        SeatDAO.checkSeat(seat);
                    }
                    //add ticket
                    TicketDAO.insert(t);
                }
            }

            request.setAttribute("card", card);
        }

        request.setAttribute("message", message);
        return url;
    }
}
