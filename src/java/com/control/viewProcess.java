/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;
import java.io.IOException;
import java.io.PrintWriter;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author Jinno
 */

public class viewProcess extends HttpServlet {
        Connection conn;
    
public void init(ServletConfig config) throws ServletException {
            super.init(config);

            try {	
                    Class.forName(config.getInitParameter("driverClass"));
                    String username = config.getInitParameter("user");
                    String password = config.getInitParameter("pass");
                    StringBuffer url = new StringBuffer(config.getInitParameter("url"))
                            .append("://")
                            .append(config.getInitParameter("host"))
                            .append(":")
                            .append(config.getInitParameter("port"))
                            .append("/")
                            .append(config.getInitParameter("databaseName"));
                    conn = 
                      DriverManager.getConnection(url.toString(),username,password);
            } catch (SQLException sqle){
                    System.out.println("SQLException error occured - " 
                            + sqle.getMessage());
            } catch (ClassNotFoundException nfe){
                    System.out.println("ClassNotFoundException error occured - " 
                    + nfe.getMessage());
            }
    }


protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        try {

            if (conn != null) {
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from userdb");
                HttpSession session = request.getSession(false);
                
                
                    request.setAttribute("results", rs);
                    rd = request.getRequestDispatcher("view.jsp");
                    rd.include(request, response); 
                    rs.close();
                
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
    }
     
        
      


  


