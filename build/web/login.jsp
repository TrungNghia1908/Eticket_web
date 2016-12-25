<jsp:include page="/includes/header.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>

    <center class="wrap">
        <h1>Login</h1>
        <hr>
        <form action="<c:url value="/user/login"/>" method="post">
            <table>
                <tr>
                    <td align="right" class="texts">Email</td>
                    <td><input type="text" name="email" class="input" required=""/></td>
                </tr>
                <tr>
                    <td align="right" class="texts">Password</td>
                    <td><input type="password" name="password" class="input" required=""/></td>
                </tr>
            </table>
            ${message}<br>
            <input type="submit" value="Login" class="button sub">
            Remember Me<input type="checkbox"  name="remember">

        </form>
        <form action="<c:url value='/user/forgetpass'/>"  method="post">
            <input type="submit" value="Forget Password" class="button sub2">       
        </form>  
    </center>

</section>
<jsp:include page="/includes/footer.jsp" /> 
