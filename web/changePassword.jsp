<jsp:include page="/includes/header.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<section>
    <center class="wrap texts">
        <h1>Change Password</h1>
        <hr>
        <form action="<c:url value="/user/changePass"/>" method="post">
            <table>
                <tr>
                    <td align="right" >New Password</td>
                    <td><input type="password" name="newPass" required="" class="input"></td>
                </tr>
                <tr>
                    <td align="right" >Confirm Password</td>
                    <td><input type="password" name="conPass" required="" class="input"></td>
                </tr>
            </table>
            <input type="submit" value="submit" class="button sub"><br>
            <p>${message}</p>
        </form>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
