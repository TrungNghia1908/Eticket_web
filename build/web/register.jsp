<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Register</title>
</head>
<body>
    <form action= "<c:url value='/user/register'/>" method="post">
        <table align="center">
            <tr>
            <td>Full Name</td>
            <td><input type="text" name="fullname" required="" /></td>
            </tr>
            
            <tr>
            <td>Email</td>
            <td><input type="email" name="email" required="" /></td>
            </tr>
            
            <tr>
            <td>Username</td>
            <td><input type="text" name="username" required="" /></td>
            </tr>
            
            <tr>
            <td>Password</td>
            <td><input type="password" name="password" required="" /></td>
            </tr>
        
            <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="conpassword" required="" /></td>
            </tr>
            
            <tr>
            <td>Phone Number</td>
            <td><input type="text" name="phonenumber" required="" /></td>
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