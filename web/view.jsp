<%-- 
    Document   : view
    Created on : 05 23, 20, 11:28:03 AM
    Author     : Jinno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        tr, td, th {
            border: 2px solid black;
            
        }
    </style>
    <body>
        <div style="width: 100%;">
            <table style="width: 100%; border: 2px solid black;">
                <tr>
                    <th>
                        User
                    </th>
                    <th>
                        Role
                    </th>
                </tr>
                <%
                    ResultSet results = (ResultSet) request.getAttribute("results");
                    String seshname = (String) session.getAttribute("user");
                  while(results.next()){
                  if(!seshname.equals(results.getString("USERNAME"))){
                %>
                <tr>
                    <td>
                        <%=results.getString("USERNAME")%>
                    </td>
                    <td>
                        <%=results.getString("ROLE")%>
                    </td>
                </tr>
                <%}%>
                <%}%>
            </table>
            <form action="success.jsp" method="post">
            <input type="submit" value="Back">
        </form>
                
        <form action="MyPDF" method="post">
            <input type="submit" value="Generate PDF Report">
        </form>
        </div>
    </body>
</html>
