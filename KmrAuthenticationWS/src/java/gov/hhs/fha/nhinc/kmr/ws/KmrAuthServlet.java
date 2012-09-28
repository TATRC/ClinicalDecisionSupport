/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.kmr.ws;

import gov.hhs.fha.nhinc.properties.PropertyAccessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import org.medsphere.util.service.AuthenticationService;
import org.medsphere.util.service.MSCAuthenticationService;
import org.medsphere.util.service.UserInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author tmn
 */
public class KmrAuthServlet extends HttpServlet {
   private String inboxProperty;
   private static String FAILURE_PAGE = "AuthFailure.jsp";
   private static String SUCCESS_PAGE = "AuthSuccess.jsp";
   private ApplicationContext context;
   private Map authenServiceProps;
   private final static String AUTHEN_SERVICE_CLIENT_PROPS = "authenservice.client";
   private MSCAuthenticationService service;

   @Override
   public void init(ServletConfig config) throws ServletException {
      URL baseUrl;
      
      super.init(config);

      // ----------------------------------------------------
      // Get the property file for KMR
      // ----------------------------------------------------
      String[] cfiles = new String[]{
        PropertyAccessor.getPropertyFileLocation() + "kmr/kmr-service-context.xml"
      };

      try {
         // ----------------------------------------------------
         //Read KMR property file and get the WSDL URL
         // ----------------------------------------------------
         context = new FileSystemXmlApplicationContext(cfiles);
         authenServiceProps = (Map) context.getBean(AUTHEN_SERVICE_CLIENT_PROPS);

         // ----------------------------------------------------
         //Overlay the URL with the one listed in property file.
         // ----------------------------------------------------
         baseUrl = org.medsphere.util.service.MSCAuthenticationService.class.getResource(".");
         URL url = new URL(baseUrl, (String) authenServiceProps.get("serviceEndpoint"));
         service = new MSCAuthenticationService(url, new QName("http://service.util.medsphere.org/", "MSCAuthenticationService"));

         //TODO
         // ----------------------------------------------------
         // Get Inbox URL
         // ----------------------------------------------------
         inboxProperty = (String) authenServiceProps.get("inboxURL");

      } catch (MalformedURLException e) {
         System.err.println("Failed to create URL for the wsdl Location: " + authenServiceProps.get("serviceEndpoint"));
      } catch (Exception es) {
         es.printStackTrace();
      }
   }

   /**
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      
      String inboxURL;
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      try {
         // initialize WS operation arguments
         HttpSession session = request.getSession();

         String duz = (String) request.getParameter("userId");
         String token = (String) request.getParameter("token");
         //String providerId = (String) request.getParameter("providerId");
         String patientId = (String) request.getParameter("patientId");


        //String providerName = (String) request.getParameter("providerName");
        String userName = (String) request.getParameter("userName");
        String patientName = (String) request.getParameter("patientName");
        String location = (String) request.getParameter("location");
        String locationId = (String) request.getParameter("locationId");

         //..............DBG LOGGING............................
         System.out.println(
                 "\nCALLING KmrAuthServlet with userId=" + duz + " and patientId=" + patientId + "\n"
                 //+"\nSESSION:providerId="+ session.getAttribute("providerId")
                 + "\nSESSION ID=" + session.getId()
                 + "\nREQUEST:token=" + token
                 //+ "\nREQUEST:providerName=" + providerName
                 + "\nREQUEST:userName=" + userName
                 + "\nREQUEST:patientName=" + patientName
                 + "\nREQUEST:location=" + location
                 + "\nREQUEST:locationId=" + locationId
                 );

         //String elem;
         for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
            //elem = (String) e.nextElement();
            System.out.print("\n\t\tREQ:elem=" + e.nextElement());
                    //+" "+ request.getParameter(elem));
         }         
         //..............DBG LOGGING............................


         // Call Web Service Operation
         AuthenticationService port = service.getAuthenticationServicePort();

         
         //=======================================================
         //FORCE failure for testing ...simulating a hacked access.
         //patient:  Zzzuni, Dora
         //-------------------------------------------------------
         if ((patientId != null) && patientId.equals("36527"))  {
            token = "999";
            System.out.println("\n\t\t========> FORCING FAILURE ... SIMULATING HACKED ACCESS!! <=======\n");
         }
         //=======================================================

         // Process result
         UserInfo userInfo = port.validateAuthenticationInformation(duz, token);

         /* output your page 
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet KmrAuthServlet</title>");
         out.println("</head>");
         out.println("<body>");
         out.println("<h1>Servlet KmrAuthServlet at " + request.getContextPath() + "</h1>");
         out.println("<p>================================================"+"</b>");
         out.println("<p>providerId: <b>" + providerId + "</b>");
         out.println("<p>patientId: <b>" + patientId + "</b>");
         out.println("<p>userId(DUZ): <b>" + duz + "</b>");
         out.println("<p>token: <b>" + token + "</b>");
         out.println("<p>================================================"+"</b>");
         out.println("<p>Title: <b>" + userInfo.getTitle() + "</b>");
         out.println("<p>User Full Name: <b>" + userInfo.getUserFullName() + "</b>");
         out.println("<p>User Name: <b>" + userInfo.getUserName() + "</b>");
         out.println("<p>Division ID: <b>" + userInfo.getDivisionID() + "</b>");
         out.println("<p>DTime: <b>" + userInfo.getDTime() + "</b>");
         out.println("<p>Language: <b>" + userInfo.getLanguage() + "</b>");
         out.println("<p>Service Section: <b>" + userInfo.getServiceSection() + "</b>");
         out.println("<p>Station Number: <b>" + userInfo.getStationName() + "</b>");
         out.println("<p>Authentication Status: <b>" + userInfo.getErrorMessage() + "</b>");
         out.println("</body>");
         out.println("</html>");
         */

         //get any returned error message from AUTH WS
         String message = userInfo.getErrorMessage();

         //save away attributes for next page.
         session.setAttribute("message", message);
         //session.setAttribute("providerId", providerId);
         session.setAttribute("patientId", patientId);
         session.setAttribute("userId", duz);
         
         session.setAttribute("userName", userName);
         session.setAttribute("patientName", patientName);
         session.setAttribute("location", location);
         session.setAttribute("locationId", locationId);
         
         //determine redirect destination
         if ((userInfo == null) 
             ||(userInfo.getUserName() == null)
             || userInfo.getUserName().isEmpty()
             )
         {
            System.out.println("FAIL !!!!");
            response.sendRedirect(FAILURE_PAGE);
         }
         else {
            System.out.println(
                   "\n\nPROV-ID="+session.getAttribute("userId")+
                   "\nPATIENT-ID="+session.getAttribute("patientId")+"\n\n");

            session.setAttribute("iburl", inboxProperty);

            //option1: REDIRECTING to INBOX --> this deos not carry the request attributes.
            //response.sendRedirect(SUCCESS_PAGE);

            //option2: FORWARDING to INBOX
            String nextJSP = "/"+SUCCESS_PAGE;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request,response);
         }
     
      } finally {
         out.close();
      }
   }

   private String prepInboxURL(String patientid, String providerid) {

      String url;
      url = inboxProperty + "patientId="+patientid;
      url = url + "%26" +"providerId="+providerid;

      System.out.println("Inbox URL="+ url);
      return url;
   }

   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   /**
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      processRequest(request, response);
   }

   /**
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      processRequest(request, response);
   }

   /**
    * Returns a short description of the servlet.
    * @return a String containing servlet description
    */
   @Override
   public String getServletInfo() {
      return "Short description";
   }// </editor-fold>
}
