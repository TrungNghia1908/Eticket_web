<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>
    <center class="wrap">
        <h1>FeedBack</h1>
        <hr>
        <form action= "<c:url value='/user/sendFeedback'/>" method="post">
            <table>

                <tr>
                    <td align="right" class="texts">Name</td>
                    <td align="center" class="texts">${user.fullName}</td>
                </tr>

                <tr>
                    <td align="right" class="texts">Email</td>
                    <td align="center" class="texts">&nbsp;${user.email}</td>
                </tr>

                <tr>
                    <td align="right" class="texts">Subject</td>
                    <td><input type="text" name="subject" class="input" required=""/></td>
                </tr>

                <tr>
                    <td align="right" class="texts">Comment</td>
                    <td><textarea name="comment" class="input" required="" rows="10" cols="30"></textarea></td>
                </tr>
            </table>
            ${message}<br>
            <input type="submit" value="Submit" class="button sub">
            <input type="reset" value="Reset" class="button sub2">
        </form>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
