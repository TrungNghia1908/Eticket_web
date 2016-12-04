<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        
        <form action="<c:url value="/user/login"/>" method="post">
        <table align="center">
            <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
            </tr>

            <tr>
            <td>Email</td>
            <td><input type="text" name="email" /></td>
            </tr>
            
            <tr>
            <td><%=request.getAttribute("message")%></td>
            </tr>
            
            <tr>
            <td><input type="submit" value="Login"></td>
            </tr>
        </table>
        </form>
    </body>
</html>
