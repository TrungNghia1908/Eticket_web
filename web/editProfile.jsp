<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Register</title>
</head>
<body>
    <form action= "<c:url value='/user/editProfile'/>" method="post">
        <table align="center">
            <tr>
            <td>Full Name</td>
            <td><input type="text" name="fullname" 
                       value="<c:out value='${user.fullName}'/>"/></td>
            </tr>
            
            <tr>
            <td>Email</td>
            <td><input type="email" name="email" 
                       value="<c:out value='${user.email}'/>"/></td>
            </tr>
            
            <tr>
            <td>Username</td>
            <td><input type="text" name="username"
                       value="<c:out value='${user.userName}'/>"/></td>
            </tr>
            
            <tr>
            <td>Phone Number</td>
            <td><input type="text" name="phonenumber"
                       value="<c:out value='${user.phoneNumber}'/>"/></td>
            </tr>
            
            <tr>
            <td>${message}</td>
            </tr>
        
            <tr>
            <td></td>
            <td><input type="submit" value="Edit"></td>
            </tr>
        </table>
    </form>
</body>
</html>