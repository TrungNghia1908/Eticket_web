<%-- 
    Document   : checkLogin
    Created on : Dec 4, 2016, 3:54:09 PM
    Author     : A Di Da Phat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checking Login</title>
    </head>
    <body>
        <h1>You are login already</h1>
        <table align="center">
            <tr>
            <td>Email</td>
            <td>${user.email}</td>
            </tr>
            
            <tr>
            <td>Username</td>
            <td>${user.userName}</td>
            </tr>
            
            <tr>
            <td>Password</td>
            <td>${user.password}</td>
            </tr>
        
            <tr>
            <td>Phone Number</td>
            <td>${user.phoneNumber}</td>
            </tr>
        
            <tr>
            <td>You Name</td>
            <td>${user.fullName}</td>
            </tr>
            
            <tr>
            <td>${message}<td>
            </tr>
            
            <tr>
            <td>
            <form action="<c:url value='/user/register'/>" method="post">
            <input type="submit" value="register">
            </form>
            </td>
                
            <td>
                <form action="<c:url value='/user/logout'/>" method="post">
                <input type="submit" value="logout">
                </form>
            </td>
            
            <td>
                <form action="<c:url value='/user/login'/>" method="post">
                <input type="submit" value="login">
                </form>
            </td>
            
            <td>
                <form action="<c:url value='/user/removeAccount'/>" method="post">
                <input type="hidden" name="userEmail" 
                    value="<c:out value='${user.email}'/>">
                <input type="submit" value="removeAccount">
                </form>
            </td>
            
            <td>
                <form action="<c:url value='/user/showProfile'/>"  method="post">
                <input type="hidden" name="userEmail"
                       value="<c:out value='${user.email}'/>">
                <input type="submit" value="Show Profile">       
                </form>  
            </td>
            </tr>
            
        </table>
    </body>
</html>
