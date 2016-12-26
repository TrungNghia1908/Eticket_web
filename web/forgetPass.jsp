<jsp:include page="/includes/header.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>

    <center class="wrap texts">
        <h2>Please enter your email address to change password</h2>
        <form action="<c:url value="/email/forgetPass"/>" method="post">
            <input type="text" name="email" class="input" style="width: 50%">
            <input type="submit" value="submit" class="button sub">
        </form>
            ${message}
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
