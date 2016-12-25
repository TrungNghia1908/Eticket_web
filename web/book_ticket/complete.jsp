<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<section>
    <center class="wrap">
        <h1>View Booking</h1>
        <hr>
        <h2>${error}</h2>
        <c:set var="user" value="${ticket.getUser()}"/>
        <h2>Your Name: ${user.getFullName()}</h2>
        <h2>Email Address: ${user.getEmail()}</h2>
        <h2>Phone Number: ${user.getPhoneNumber()}</h2>
        <%--<c:set var="trip" value="${ticket.getTrip()}"/>--%>
        <h2>${trip.getArrival()} to ${trip.getDestination()}</h2>
        <h2>Pin Code: ${ticket.getPinCode()}</h2>

        <h2>Number of Ticket: ${ticket.getNumOfTicket()}</h2>
        <h2>Ticket price: ${ticket.getTicketPrice()}</h2>

        <%--<c:set var="bus" value="${ticket.getBus()}"/>--%>
        <h2>Bus Number: ${bus.getBusNo()}</h2>
        <h2>Running Time: ${ticket.getTimeAvailable()}</h2>
        <h2>Driver Name: ${bus.getDriverName()} </h2>


        <h2>Seat No: <c:forEach var="seatNo" items="${seatsNo}"> ${seatNo}</c:forEach></h2>

            <form action="<c:url value = "/booking/payFee"/>" method="get">
            <input type="submit" value="next" class="button sub">
        </form>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 