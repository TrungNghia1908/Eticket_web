<%-- 
    Document   : select_seat
    Created on : Dec 14, 2016, 4:00:38 PM
    Author     : A Di Đà Phật
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Choose Route</title>
</head>
<body>
    <form action= "<c:url value='/booking/chooseSeat'/>" method="post">

        <table>
            <c:forEach var="i" items="${bus.getSeats()}">
                <tr>
                <c:if test="${i != null}">
                <td>G1<input type="checkbox" name="seatNo" value="1"/></td>
                </c:if>
                <!--<td>G${i+1}<input type="checkbox" name="seatNo" value="2"/></td>-->
                </tr>
            </c:forEach>
            
<!--            <tr>
                <td>G3<input type="checkbox" name="seatNo" value="3"/></td>
                <td>G4<input type="checkbox" name="seatNo" value="4"/></td>
            </tr>
             <tr>
                <td>G5<input type="checkbox" name="seatNo" value="5"/></td>
                <td>G6<input type="checkbox" name="seatNo" value="6"/></td>
            </tr>
            <tr>
                <td>G7<input type="checkbox" name="seatNo" value="7"/></td>
                <td>G8<input type="checkbox" name="seatNo" value="8"/></td>
            </tr>
            <tr>
                <td>G9<input type="checkbox" name="seatNo" value="9"/></td>
                <td>G10<input type="checkbox" name="seatNo" value="10"/></td>
            </tr>
            <tr>
                <td>G11<input type="checkbox" name="seatNo" value="11"/></td>
                <td>G12<input type="checkbox" name="seatNo" value="12"/></td>
            </tr>-->
            <input type="submit" value="next">
        </table>
        </form>
    </body>
</html>
