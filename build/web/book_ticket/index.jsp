<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section>
    <center class="wrap">
        <h1>Booking</h1>
        <hr>
    <form action= "<c:url value='/booking/selectTime'/>" method="get">
        <table class="texts">
            <tr>
            <td>Destination</td>
            <td>${trip.destination}</td>
            </tr>
            
            <tr>
            <td>Arrival</td>
            <td>${trip.arrival}</td>
            </tr>
            
            <tr>
            <td>Date</td>
            <td><input type="date" id="theDate" name="date" min="14/12/2016"></td>
            </tr>
            
        </table>
            <input type="submit" value="Next" class="button sub">
    </form>
        
    <script src="./js/Time_min.js"></script>
</center>
</section>
<jsp:include page="/includes/footer.jsp" /> 