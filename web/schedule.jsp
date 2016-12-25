<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>

    <center class="wrap">
        <h1>List of trip</h1>
        <hr>
        <!--<h1>${schedule}</h1>-->
    
        <c:forEach var="tripList" items="${schedule}">
        <table class="tabl texts">
            <h3 class="texts">${tripList.trips.get(0).getArrival()}</h3>
            <tr>
            <th>Arrival</th>
            <th>Destination</th>
            <th>Price</th>
            </tr>
            <c:forEach var = "trip" items="${tripList.trips}">
                <tr>
                <td>${trip.arrival}</td>
                <td>${trip.destination}</td>
                <td>${trip.price}</td>
                <td style="border:none">
                    <form action="<c:url value="/booking/selectDate"/>" method="get">
                        <input type="hidden" name="tripId" value="${trip.tripId}">
                        <input type="submit" value="Book" class="button sub">
                    </form>
                </td>
                </tr>
            </c:forEach>
        </table>
        </c:forEach>
</center>

</section>
<jsp:include page="/includes/footer.jsp" />
