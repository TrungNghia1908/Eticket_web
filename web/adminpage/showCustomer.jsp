<jsp:include page="/includes/headeradmin.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<section>
    <center class="wrap">
        <h1>List of Customer</h1>
        <hr>
        <c:if test="${userList != null}">
            <form action="<c:url value="/admin/displayReport"/>" method="POST">
                <input type="hidden" name="reportName" value="userList">
                <input type="hidden" name="reportTitle" value="The User Email report">
                <input type="submit" value="Download Customer List" class="button sub">
            </form>
            <table class="texts tabl" style="width: 80%">  
                <tr>
                    <th>User ID</th>
                    <th>Full Name</th>
                    <th>Email</th>
                    <th>User Name</th>
                    <th>Phone Number</th>
                </tr>
                <c:forEach var = "u" items="${userList.users}">
                    <tr>
                        <td>${u.userId}</td>
                        <td>${u.fullName}</td>
                        <td>${u.email}</td>
                        <td>${u.userName}</td>
                        <td>${u.phoneNumber}</td>
                        <td style="border:none">
                            <form action="<c:url value='/admin/removeAccount'/>" method="get">
                                <input type="hidden" name="userEmail" 
                                       value="<c:out value='${u.email}'/>">
                                <input type="submit" value="Remove Account" class="button sub">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 