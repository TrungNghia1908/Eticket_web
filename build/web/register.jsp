<jsp:include page="/includes/header.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>
    <center class="wrap">
        <h1>Register</h1>
        <hr>
        <form action= "<c:url value='/user/register'/>" method="post">
            <table>
                <tr>
                    <td align="right" class="texts">Full Name</td>
                    <td><input type="text" name="fullname" required="" class="input"/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Email</td>
                    <td><input type="email" name="email" required="" class="input"/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Username</td>
                    <td><input type="text" name="username" required="" class="input"/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Password</td>
                    <td><input type="password" name="password" required="" class="input"></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Confirm Password</td>
                    <td><input type="password" name="conpassword" required="" class="input"/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Phone Number</td>
                    <td><input type="text" name="phonenumber" required="" class="input"/></td>
                </tr>

                <tr>
                    <td colspan="2">${message}</td>
                </tr>
            </table>
            <input type="submit" value="Register" class="button sub">
            <input type="reset" value="Reset" class="button sub2">
        </form>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
