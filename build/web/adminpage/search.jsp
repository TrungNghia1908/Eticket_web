<jsp:include page="/includes/headeradmin.jsp" />
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<section>
<center class="wrap">
    <form action= "<c:url value='/admin/search'/>" method="post">
        <h1>Search Trips</h1>
        <hr>
        <table>
            <tr>
            <td align="right" class="texts">Keyword</td>
            <td><input type="text" name="searchKey" class="input" /></td>
            </tr>
            <tr>
                <td colspan="2">${message}</td>
            </tr>
        </table>
            <input type="submit" value="Search" class="button sub">
            <input type="reset" value="Reset" class="button sub2">
    </form>
            
    <c:if test="${tripList != null}">
    <table class="tabl texts">  
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
            <form action="<c:url value='/admin/showTrip'/>" method="get">
            <input type="hidden" name="tripId" value="${trip.tripId}">
            <input type="submit" value="Edit" class="button sub" style="margin: 5px 0px">
            </form>
            <form action="<c:url value="/admin/removeTrip"/>" method="get">
                <input type="hidden" name="tripId" value="${trip.tripId}">
                <input type="submit" value="Remove" class="button sub2">
            </form>
        </td>
        
        </tr>
        </c:forEach>
    </table>
    </c:if>
 </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 