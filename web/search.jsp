<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Search Trip
    </title>
</head>
<body>
    <form action= "<c:url value='/user/searchTrip'/>" method="post">
        <table align="center">
            <tr>
            <td>Search</td>
            <td><input type="text" name="searchKey"/></td>
            </tr>
            
            <tr>
            <td></td>
            <td><input type="submit" value="Search"></input><input type="reset" value="Reset"></input></td>
            <td></td>
            <td>
            </td>
            </tr>
            
            <tr>
            <td>${message}</td>
            </tr>
        </table>
    </form>
            
    <c:if test="${tripList != null}">
    <table align="center">  
        <tr>
        <th>Arrival</th>
        <th>Destination</th>
        <th>Price</th>
        <th>&nbsp;</th>
        </tr>
        <c:forEach var = "trip" items="${tripList.trips}">
        <tr>
        <td>${trip.arrival}</td>
        <td>${trip.destination}</td>
        <td>${trip.price}</td>
        <td>
            <form action="<c:url value='/admin/showTrip'/>" method="post">
            <input type="hidden" name="tripId" 
                   value="${trip.tripId}">
            <input type="submit" value="Edit">
            </form>    
        </td>
        
        <td>
            <form action="<c:url value="/admin/removeTrip"/>" method="post">
                <input type="hidden" name="tripId"
                       value="${trip.tripId}">
                <input type="submit" value="Remove">
            </form>
        </td>
        </tr>
        </c:forEach>
        <h1>${testTrip.arrival}</h1>
    </table>
    </c:if>
    
</body>
</html>