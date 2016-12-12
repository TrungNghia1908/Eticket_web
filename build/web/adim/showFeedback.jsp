<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Search Trip</title>
</head>
<body>
    <c:if test="${feedbackList != null}">
    <table align="center">  
        <tr>
        <th>User Name</th>
        <th>User Email</th>
        <th>Subject</th>
        <th>Comment</th>
        <th>Date Create</th>
        <th>&nbsp;</th>
        </tr>
        <c:forEach var = "feedback" items="${feedbackList.getFeedbacks()}">
        <tr>
        <td>${feedback.user.fullName}</td>
        <td>${feedback.user.email}</td>
        <td>${feedback.subject}</td>
        <td>${feedback.comment}</td>
        <td>${feedback.feedbackDate}</td>
        </c:forEach>
    </table>
    </c:if>
    
</body>
</html>