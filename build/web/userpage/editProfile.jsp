<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>
    <center class="wrap">
        <h1>Edit Profile</h1>
        <hr>
    <form action= "<c:url value='/user/editProfile'/>" method="post">
        <table>
            <tr>
            <td align="right" class="texts">Full Name</td>
            <td><input type="text" name="fullname" 
                       value="<c:out value='${user.fullName}'/>" class="input" required=""/></td>
            </tr>
            
            <tr>
            <td align="right" class="texts">Email</td>
            <td><input type="email" name="email" 
                       value="<c:out value='${user.email}'/>" class="input" required=""/></td>
            </tr>
            
            <tr>
            <td align="right" class="texts">Username</td>
            <td><input type="text" name="username"
                       value="<c:out value='${user.userName}'/>" class="input" required=""/></td>
            </tr>
            
            <tr>
            <td align="right" class="texts">Phone Number</td>
            <td><input type="text" name="phonenumber"
                       value="<c:out value='${user.phoneNumber}'/>" class="input" required=""/></td>
            </tr>
        
        </table>
            ${message}<br>
            <input type="submit" value="Edit" class="button sub">
    </form>
</center>
</section>
<jsp:include page="/includes/footer.jsp" /> 