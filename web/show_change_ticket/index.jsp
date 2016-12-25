<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<section>
    <center class="wrap texts">
        <h1>View Booking</h1>
        <hr>
        <h2>Please enter Pin code to get Ticket Information or change Ticket</h2>
        <form action="<c:url value="/change/showTicket"/>" method="get">
            <input type="text" name="pincode" class="input" style="width: 50%">
            <input type="submit" value="Show Ticket" class="button sub">
        </form>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
