<%-- 
    Document   : success
    Created on : 03 22, 20, 2:44:21 PM
    Author     : Jinno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
        <%
    
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.
    
    

    if(session.getAttribute("user")==null)
                {
                     out.print("<h1><center>Previous Session has expired!</center><h1>");
                    request.getRequestDispatcher("index.jsp").include(request, response);
                }
                else{
%>
    </head>
    <center>
    <header>

<%
    ServletContext context = getServletContext();
    String header = context.getInitParameter("header");
                          
                          RequestDispatcher rd=request.getRequestDispatcher(header);
                        rd.include(request, response);
                        
String seshuser = session.getAttribute("user").toString();
    String seshrole = session.getAttribute("role").toString();
ResultSet results = (ResultSet) request.getAttribute("results");
request.setAttribute("results",results);

     %>
    </header>

    <body>
    
                <h1> WELCOME <%=seshuser%> </h1>
                <h2> Your Role: <%=seshrole%> </h2>

        
       
        <form action="viewProcess" method="post">
            <input type="submit" value="View All Records">
        </form>
        
        <form >
            <input type="hidden" value="<%=seshuser%>" name="user">
        </form>
        <form >
            <input type="hidden" value="<%=seshrole%>" name="role">
        </form>
                
        <form action="MyPDF" method="post">
            <input type="submit" value="Generate PDF Report">
        </form>
                <br>
        <form action="ControllerLogout" method="post">
            <input type="submit" value="Logout">
        </form>
    </body>
    <footer>

            <%=
                context.getAttribute("time")
            %>
    </footer>
</center>
</html>
<%}%>
