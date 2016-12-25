<%-- 
    Document   : selectTime
    Created on : Dec 14, 2016, 3:20:12 PM
    Author     : A Di Đà Phật
--%>
<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<section>
    <center class="wrap">
        <h1>Booking</h1>
        <hr>
    <h2>${ticket.getDateBooking()}</h2>
    <form name="testform" id="testform" action="<c:url value="/booking/selectTime"/>" method="get">
        <select name="runTime" id="dropdown" onChange="document.testform.submit()">
        <c:forEach var="time" begin="0" end="${times.size()}" items="${times.iterator()}" >
            <option value="${time}">${time}</option>
        </c:forEach>
        </select>
    </form>
    ${message}
    <form action= "<c:url value='/booking/selectSeat'/>" method="post">
    <table>
        <c:forEach var="seats" items="${bus.getSeats()}">
            <tr>
                <c:if test="${seats.status.equals('N') == false}">
                    <td>G${bus.getSeats().indexOf(seats)} x</td>
                </c:if>
                <c:if test="${seats.status.equals('N') == true}">
                    <td>G${bus.getSeats().indexOf(seats)}
                    <input type="checkbox" name="seatNo" 
                           value="${seats.seatNo}"/></td>
                </c:if>
            </tr>
        </c:forEach>
            <tr><input type="submit" value="next" class="button sub"></tr>
    </table>
    </form>
</center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
