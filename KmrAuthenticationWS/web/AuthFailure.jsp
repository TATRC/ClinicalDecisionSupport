<%-- 
    Document   : authFailure
    Created on : Jun 4, 2010, 1:14:28 AM
    Author     : tmn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KMR Authentication Status</title>
    </head>
    <body>
        <h1>KMR Authentication Failure</h1>

        <%
         String msg = (String) session.getAttribute("message");
         String userId = (String) session.getAttribute("userId");
        %>
        Failed for User ID <%= userId %><br/>
        [ERROR]  <%= msg %>
    </body>
</html>
