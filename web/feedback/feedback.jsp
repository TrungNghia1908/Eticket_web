<%-- 
    Document   : feedback
    Created on : Dec 8, 2016, 2:31:29 AM
    Author     : A Di Đà Phật
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feed Back</title>
    </head>
    <body>
    <h1>Hello World!</h1>
    <form action= "<c:url value='/user/sendFeedback'/>" method="post">
        <table align="center">
            <tr>
            <td>Name</td>
            <td><input type="text" name="fullname" value="${user.fullName}" /></td>
            </tr>
            
            <tr>
            <td>Email</td>
            <td><input type="email" name="email"  value="${user.email}"/></td>
            </tr>
            
            <tr>
            <td>Subject</td>
            <td><input type="text" name="subject" /></td>
            </tr>
            
            <tr>
            <td>Comment</td>
            <td><input type="textarea" name="comment" /></td>
            </tr>
        
            <tr>
            <td>${message}</td>
            </tr>

            <tr>
            <td></td>
            <td><input type="submit" value="Register"></input><input type="reset" value="Reset"></input></td>
            </tr>
        </table>
    </form>
    </body>
</html>
