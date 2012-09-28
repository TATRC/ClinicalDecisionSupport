<%-- 
    Document   : vmr_config
    Created on : Aug 24, 2010, 6:22:38 PM
    Author     : Steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor" %>

<%!
    private String getParam(String name, HttpServletRequest request)
    {
        String value = request.getParameter(name);
        if (value == null)
        {
            value = "";
        }
        return value;
    }
%>

<%
    if (getParam("Cancel", request).equalsIgnoreCase("Cancel"))
    {
        response.sendRedirect("index.jsp");
    }
    if (getParam("Update", request).equalsIgnoreCase("Update"))
    {
        // TODO Add update methods to DatePropertyAccessor
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <script language="JavaScript" type="text/javascript" src="script/CalendarPopup.js"></script>
        <script language="JavaScript">document.write(getCalendarStyles());</script>
        <title>VMR Configuration</title>
        <link href="style/global.css" rel="stylesheet" type="text/css" />
        <link href="style/tooltip.css" rel="stylesheet" type="text/css" />
        <!--[if IE]>
            <style type="text/css">
                body {behavior: url(script/csshover.htc);}
            </style>
            <link href="style/global-ie.css" rel="stylesheet" type="text/css" />
        <![endif]-->
    </head>
    <body>
        <h1>VMR Configuration</h1>
        <br/>
        <form id="form" name="form" method="get" action="vmr_config.jsp">
        <table>
            <tr>
                <th>Domain</th>
                <th>Start</th>
                <th>End</th>
            </tr>
            <tr>
                <td>Global Default</td>
                <td>
                    <input name="globalDefaultBegin" id="globalDefaultBegin" value="<%= PropertyAccessor.getProperty("dss", "Default-Begin") %>" />
                </td>
                <td>
                    <input name="globalDefaultEnd" id="globalDefaultEnd" value="<%= PropertyAccessor.getProperty("dss", "Default-End") %>" />
                </td>
            </tr>
            <tr>
                <td>Patient Default</td>
                <td>
                    <input name="patientDefaultBegin" id="patientDefaultBegin" value="<%= PropertyAccessor.getProperty("dss", "Patient-Default-Begin") %>" />
                </td>
                <td>
                    <input name="patientDefaultEnd" id="patientDefaultEnd" value="<%= PropertyAccessor.getProperty("dss", "Patient-Default-End") %>" />
                </td>
            </tr>
            <tr>
                <td>Demographics</td>
                <td>
                    <input name="demographicsBegin" id="demographicsBegin" value="<%= PropertyAccessor.getProperty("dss", "Patient-Demographics-Begin") %>" />
                </td>
                <td>
                    <input name="demographicsEnd" id="demographicsEnd" value="<%= PropertyAccessor.getProperty("dss", "Patient-Demographics-End") %>" />
                </td>
            </tr>
            <tr>
                <td>Allergies</td>
                <td>
                    <input name="allergiesBegin" id="allergiesBegin" value="<%= PropertyAccessor.getProperty("dss", "Patient-Allergies-Begin") %>" />
                </td>
                <td>
                    <input name="allergiesEnd" id="allergiesEnd" value="<%= PropertyAccessor.getProperty("dss", "Patient-Allergies-End") %>" />
                </td>
            </tr>
            <tr>
                <td>Medications</td>
                <td>
                    <input name="medicationsBegin" id="medicationsBegin" value="<%= PropertyAccessor.getProperty("dss", "Patient-Medications-Begin") %>" />
                </td>
                <td>
                    <input name="medicationsEnd" id="medicationsEnd" value="<%= PropertyAccessor.getProperty("dss", "Patient-Medications-End") %>" />
                </td>
            </tr>
            <tr>
                <td>Problems</td>
                <td>
                    <input name="problemsBegin" id="problemsBegin" value="<%= PropertyAccessor.getProperty("dss", "Patient-Problems-Begin") %>" />
                </td>
                <td>
                    <input name="problemsEnd" id="problemsEnd" value="<%= PropertyAccessor.getProperty("dss", "Patient-Problems-End") %>" />
                </td>
            </tr>
            <tr>
                <td>Results</td>
                <td>
                    <input name="resultsBegin" id="resultsBegin" value="<%= PropertyAccessor.getProperty("dss", "Patient-Results-Begin") %>" />
                </td>
                <td>
                    <input name="resultsEnd" id="resultsEnd" value="<%= PropertyAccessor.getProperty("dss", "Patient-Results-End") %>" />
                </td>
            </tr>
        </table>
        <br/>
        <table>
            <tr>
                <td>
                    <input name="Update" type="submit" id="Update" value="Update" />
                </td>
                <td>
                    <input name="Cancel" type="submit" id="Cancel" value="Cancel" />
                </td>
            </tr>
        </table>
        </form>
        <br/>
<%
    if (getParam("Update", request).equalsIgnoreCase("Update"))
    {
%>
        Date range properties updated.
<%
    }
%>
    </body>
</html>
