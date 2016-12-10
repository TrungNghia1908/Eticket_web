<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Show Customer</title>
</head>
<body>
    <h2>${message}</h2>
    <c:if test="${userList != null}">
    <table align="center">  
        <tr>
        <th>User ID</th>
        <th>Full Name</th>
        <th>Email</th>
        <th>User Name</th>
        <th>Phone Number</th>
        <th>&nbsp;</th>
        </tr>
        <c:forEach var = "u" items="${userList.users}">
        <tr>
        <td>${u.userId}</td>
        <td>${u.fullName}</td>
        <td>${u.email}</td>
        <td>${u.userName}</td>
        <td>${u.phoneNumber}</td>
        <td>
        <form action="<c:url value='/user/removeAccount'/>" method="post">
        <input type="hidden" name="userEmail" 
            value="<c:out value='${u.email}'/>">
        <input type="submit" value="Remove Account">
        </form>
        </td>
        </tr>
        </c:forEach>
    </table>
    </c:if>
    
</body>
</html>