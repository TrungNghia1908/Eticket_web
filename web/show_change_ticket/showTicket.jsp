<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<section>
    <center class="wrap">
        <h1>Ticket Information</h1>
        <hr>
        <c:set var="user" value="${oldTicket.getUser()}"/>
        <h3>Your Name: ${oldTicket.getUser().getFullName()}</h3>
        <h3>Email Address: ${oldTicket.getUser().getEmail()}</h3>
        <h3>Phone Number: ${oldTicket.getUser().getPhoneNumber()}</h3>
        <%--<c:set var="trip" value="${ticket.getTrip()}"/>--%>
        <h3>${oldTrip.getArrival()} to ${oldTrip.getDestination()}</h3>
        <h3>Pin Code: ${oldTicket.getPinCode()}</h3>
        
        <h3>Number of Ticket: ${oldTicket.getNumOfTicket()}</h3>
        <h3>Ticket price: ${oldTicket.getTicketPrice()}</h3>
        
        <%--<c:set var="bus" value="${ticket.getBus()}"/>--%>
        <h3>Bus Number: ${oldBus.getBusNo()}</h3>
        <h3>Running Time: ${oldTicket.getTimeAvailable()}</h3>
        <h3>Driver Name: ${oldBus.getDriverName()} </h3>
        <h3>Seat No: <c:forEach var="no" items="${oldSeatsNo}">${no} </c:forEach> </h3>
            
        
        <form action="<c:url value="/search.jsp"/>" method="get">
            <c:set scope="session" var="status" value="change"/>
            <input type="submit" value="Change Ticket" class="button sub2">
        </form>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
