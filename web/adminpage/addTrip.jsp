<jsp:include page="/includes/headeradmin.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>
    <center>
        <h1>Add Trip</h1>
        <hr>
        <form action= "<c:url value='/admin/addTrip'/>" method="post">
            <table>
                <tr>
                    <td align="right" class="texts">Arrival</td>
                    <td><input type="text" name="arrival" class="input" required=""/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Destination</td>
                    <td><input type="text" name="destination" class="input" required=""/></td>
                    <!--<td><a href="addTrip.jsp?check=1">Add new destination</a></td>-->
                </tr>

                <tr>
                    <td align="right" class="texts">Price</td>
                    <td><input type="text" name="price" class="input" required=""/></td>
                </tr>

            </table>
            ${message}<br>
            <input type="submit" value="Add Trip" class="button sub">
            <input type="reset" value="Reset" class="button sub2">
        </form>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" />