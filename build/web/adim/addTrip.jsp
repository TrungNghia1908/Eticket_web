<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Trip</title>
</head>
<body>
    <form action= "<c:url value='/admin/addTrip'/>" method="post">
        <table align="center">
            <tr>
            <td>Arrival</td>
            <td><input type="text" name="arrival" value="${trip.arrival}" /></td>
            </tr>
            
            <tr>
            <td>Destination</td>
            <td><input type="text" name="destination" value="${trip.destination}"/></td>
            <!--<td><a href="addTrip.jsp?check=1">Add new destination</a></td>-->
            </tr>
            
            <tr>
            <td>Price</td>
            <td><input type="text" name="price" value="${trip.price}"/></td>
            </tr>
            
            <tr>
            <td>${message}</td>
            </tr>

            <tr>
            <td></td>
            <td><input type="submit" value="Add Trip"></input>
                <input type="reset" value="Reset"></input></td>
            <td></td>
            </tr>
        </table>
    </form>
</body>
</html>