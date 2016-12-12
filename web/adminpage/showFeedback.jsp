<jsp:include page="/includes/headeradmin.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>
    <center class="wrap">
        <h1>Feedback</h1>
        <hr>
        <c:if test="${feedbackList != null}">
            <table class="tabl texts" style="width: 80%">  
                <tr>
                    <th>User Name</th>
                    <th>User Email</th>
                    <th>Subject</th>
                    <th>Comment</th>
                    <th>Date Create</th>
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
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 