<%-- 
    Document   : index
    Created on : Jun 3, 2010, 1:39:24 PM
    Author     : tmn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KMR Intermediate Auth Page</title>
    </head>
    <body>
        <h1>Retrieve DUZ</h1>
        <p>This will be a simple form to get DUZ and token. </p>

        <%
        /*--------------------------------------------------
        //Expecting session to have foloowing attributes:        
   					"userName"
						"userId"     ==> the DUZ for logged in user (provider or non-provider)
						"patientName"
						"patientId"
						"providerName" ==> may be deprecated along with providerId
						"providerId"   ==> deprecated
						"location"
						"locationId"
						"token"
        -------------------------------------------------- */
        String userId = (String) request.getParameter("userId");
        String token = (String) request.getParameter("token");
        //String providerId = (String) request.getParameter("providerId");        
        String patientId = (String) request.getParameter("patientId");

        //String providerName = (String) request.getParameter("providerName");
        String userName = (String) request.getParameter("userName");
        String patientName = (String) request.getParameter("patientName");
        String location = (String) request.getParameter("location");
        String locationId = (String) request.getParameter("locationId");

        //--------------------------------------------------
        //STORE attributes from session for use later in servlet.
        // keeping this because having prob seeing bean from servlet.
        //--------------------------------------------------
        session.setAttribute("userId", userId);
        session.setAttribute("token", token);
        //session.setAttribute("providerId", providerId);
        session.setAttribute("patientId", patientId);

        //session.setAttribute("providerName", providerName);
        session.setAttribute("userName", userName);
        session.setAttribute("patientName", patientName);
        session.setAttribute("location", location);
        session.setAttribute("locationId", locationId);
        %>


        <%-- ------------------------------------
             Store Parameters into a session bean
             WHY if I can get it from session?
        ----------------------------------------- --%>
        <%--
        <jsp:useBean id="reqInfo" class="gov.hhs.fha.nhinc.kmr.util.RequestInfo" scope="session">
           <jsp:setProperty name="reqInfo" property="*"/>           
        </jsp:useBean>
        THE token = <jsp:getProperty name="reqInfo" property="token" />
        THE userId = <jsp:getProperty name="reqInfo" property="userId" />
        --%>

        <%-- Calling Servlet --%>
        <jsp:forward page="/KmrAuthServlet"/>
      
        <%-- Allows a Wait before redirect .. JUST FOR TESTING
        <form action="KmrAuthServlet" method="post" name="myform">
           <input type="submit" name="submit" value="Submit">
        </form>
        --%>

    </body>
</html>
