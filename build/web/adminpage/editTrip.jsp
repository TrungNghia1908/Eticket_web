<jsp:include page="/includes/headeradmin.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>
    <center>
        <h1>Edit Trip</h1>
        <hr>
    <form action= "<c:url value='/admin/editTrip'/>" method="post">
        <table>
            
            <tr>
            <td align="right" class="texts">Arrival</td>
            <td>${trip.arrival}</td>
            </tr>
            
            <tr>
            <td align="right" class="texts">Destination</td>
            <td>${trip.destination}</td>
            <!--<td><a href="addTrip.jsp?check=1">Add new destination</a></td>-->
            </tr>
            
            <tr>
            <td align="right" class="texts">Price</td>
            <td><input type="text" name="price" value="${trip.price}" class="input" required=""/></td>
            </tr>
            
            <input type="hidden" name="tripId" value="${trip.tripId}">
        </table>
            ${message}<br>
            <input type="submit" value="Edit">
                <input type="reset" value="Reset">
    </form>
</center>
</section>
<jsp:include page="/includes/footer.jsp" />