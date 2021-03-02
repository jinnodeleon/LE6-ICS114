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

/**
 *
 * @author Jinno
 */

public class ControllerLogout extends HttpServlet {

    

 
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            RequestDispatcher rd;
            HttpSession session = request.getSession(false);
            session.invalidate();
            rd = request.getRequestDispatcher("index.jsp");
                    rd.include(request, response); 
    }
    }
     
        
      


  


