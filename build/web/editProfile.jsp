<jsp:include page="/includes/header.jsp" />
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<section>
    <center class="wrap">
        <h1>Register</h1>
        <hr>
        <form action= "<c:url value='/user/editProfile'/>" method="post" >
            <table>
                <tr>
                    <td align="right" class="texts">Full Name</td>
                    <td><input type="text" name="fullname" class="input" required=""/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Email</td>
                    <td><input type="email" name="email" class="input" required=""/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Username</td>
                    <td><input type="text" name="username" class="input" required=""/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Password</td>
                    <td><input type="password" name="password" class="input" required=""/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Confirm Password</td>
                    <td><input type="password" name="conpassword" class="input" required=""/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Phone Number</td>
                    <td><input type="text" name="phonenumber" class="input" required=""/></td>
                </tr>

                <tr>
                    <td colspan="2">${message}</td>
                </tr>

                <tr>
                    <td></td>
                    <td></td>
                </tr>
            </table>
                <input type="submit" value="Register" class="button sub">
                <input type="reset" value="Reset" class="button sub2">
        </form>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 