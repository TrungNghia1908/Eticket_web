<jsp:include page="/includes/header.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<section>
    <center class="wrap texts">
        <form action="<c:url value="/user/changePass"/>" method="post">
            New Password: <input type="password" name="newPass" required="">
            Confirm Password: <input type="password" name="conPass" required="">
            <input type="submit" value="submit" class="button sub">
            <p>${message}</p>
        </form>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
