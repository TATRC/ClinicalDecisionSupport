<%-- 
    Document   : AuthSucess
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
      <h1>KMR Authentication Success</h1>

      <%
       String msg = (String) session.getAttribute("message");
       String userId = (String) session.getAttribute("userId");
       String patientId = (String) session.getAttribute("patientId");
       String url = (String) session.getAttribute("iburl");

       String userName = (String) session.getAttribute("userName");
       String patientName = (String) session.getAttribute("patientName");
       String location = (String) session.getAttribute("location");
       String locationId = (String) session.getAttribute("locationId");

       System.out.println("\n\n========================"
                          +"\nIBX: userId = "+userId
                          +"\nIBX: userName = "+userName
                          +"\nIBX: patientId = "+patientId
                          +"\nIBX: patientName = "+patientName
                          +"\nIBX: location = "+location
                          +"\nIBX: locationId = "+locationId
                          +"\n========================\n\n");

      /* testing ONLY
      providerId = "1";
      patientId = "D123401";
      url = "http://208.75.163.61:8080/UniversalInboxGUI/inbox.iface";
      */
      %>

      <%-- DEBUG only --%>
      [SUCESS]  <%= msg%>
      [provider ID] <%= userId%>
      [patient ID] <%= patientId%>
      [Inbox URL] <%= url%>
      [userName] <%= userName%>
      
      
      <form name="IBX" method="POST" action="<%= url%>">
         <input type="hidden" name="providerId" value="<%= userId%>">
         <% if (session.getAttribute("patientId") != null) { %>
         	<input type="hidden" name="patientId" value="<%= patientId%>">
         <% } %>
         <% if (session.getAttribute("userName") != null) { %>
         	<input type="hidden" name="userName" value="<%= userName%>">
         <% } %>
      </form>

      <SCRIPT LANGUAGE="javascript" type="text/javascript">
         submitThisPage();
         function submitThisPage()
         {
            document.IBX.submit();
         }
      </SCRIPT>

   </body>
</html>
