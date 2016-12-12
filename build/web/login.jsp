<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
        crossorigin="anonymous">
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
            <td>${message}</td>
            </tr>
            
            <tr>
            <td><input type="submit" value="Login"></td>
            </tr>
        </table>
        </form>
            
    </body>
</html>
