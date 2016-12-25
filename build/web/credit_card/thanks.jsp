<jsp:include page="/includes/headeruser.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<section>
    <center class="wrap">
        <h1>${message}</h1>
        <hr>
        <h2>Card Id: ${card.getCardId()}</h2>
        <h2>Your Amount: ${card.getAmount()}</h2>
        <h2>Ticket Price: ${ticket.getTicketPrice()}</h2>
        
        <c:if test="${status != null}">
            <h2>Old Ticket Price: ${oldTicket.getTicketPrice()}</h2>
            <c:set var="pay" value="${ticket.getTicketPrice()- oldTicket.getTicketPrice()}"/>
            <h2>Your must pay: ${pay}</h2>
            <h2>After pay: ${card.getAmount() - pay} </h2>
        </c:if>
            
        <c:if test="${status == null}">
            <h2>After pay: ${card.getAmount() - ticket.getTicketPrice()}</h2>
        </c:if>
    </center>
</section>
<jsp:include page="/includes/footer.jsp" /> 
