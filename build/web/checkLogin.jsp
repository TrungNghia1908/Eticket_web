<%-- 
    Document   : checkLogin
    Created on : Dec 4, 2016, 3:54:09 PM
    Author     : A Di Đà Phật
--%>

<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<section>
    <center class="wrap">
        <h1>My Profile</h1>
        <hr>
        <table class="texts">
            <tr>
            <td >Email</td>
            <td>&nbsp;${user.email}</td>
            </tr>
            
            <tr>
            <td>Username</td>
            <td>&nbsp;${user.userName}</td>
            </tr>
            
            <%--<tr>
            <td>Password</td>
            <td>&nbsp; ${user.password}</td>
            </tr> --%>
        
            <tr>
            <td>Phone Number</td>
            <td>&nbsp;${user.phoneNumber}</td>
            </tr>
        
            <tr>
            <td>You Name</td>
            <td>&nbsp;${user.fullName}</td>
            </tr>
        </table>
                ${message}<br>
                <form action="<c:url value='/user/showProfile'/>"  method="post">
                <input type="hidden" name="userEmail"
                       value="<c:out value='${user.email}'/>">
                <input type="submit" value="Edit Profile" class="button sub2">       
                </form>  
</center>
</section>
<jsp:include page="/includes/footer.jsp" /> 

