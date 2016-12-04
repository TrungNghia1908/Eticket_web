<%-- 
    Document   : thanks
    Created on : Dec 4, 2016, 9:17:20 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thanks Page</title>
    </head>
    <body>
        <h1>Thanks for join our web page</h1>
        <p>Here is the information that you entered:</p>

        <label  class="no_pad_top">Email</label>
        <span>${user.email}</span><br>
        <label class="no_pad_top">Full Name</label>
        <span>${user.fullName}</span><br>
        <label class="no_pad_top">User Name</label>
        <span>${user.userName}</span><br>
        <label class="no_pad_top">Phone Number</label>
        <span>${user.phoneNumber}</span><br>
    </body>
</html>
