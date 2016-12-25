<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<section>
    <center class="wrap texts">
        <h1>Booking</h1>
        <hr>
        <h2>Please Enter your ID Card </h2>
        <form action="<c:url value="/booking/payProcess"/>" method="post">
            Card ID: <input type="text" name="cardId" class="input" style="width: 50%">
            <input type="submit" value="sumbit" class="button sub">
        </form>
            ${message}
</center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
