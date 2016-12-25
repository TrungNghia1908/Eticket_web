<%-- 
    Document   : thanks
    Created on : Dec 4, 2016, 9:17:20 AM
    Author     : Administrator
--%>
<jsp:include page="/includes/header.jsp" />
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thanks Page</title>
    </head>
    <body>
        <h1>Thanks for join our web page</h1>
        <p>Here is the information that you entered:</p>

        <label  class="texts">Email</label>
        <span>${user.email}</span><br>
        <label class="texts">Full Name</label>
        <span>${user.fullName}</span><br>
        <label class="texts">User Name</label>
        <span>${user.userName}</span><br>
        <label class="texts">Phone Number</label>
        <span>${user.phoneNumber}</span><br>
        
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
