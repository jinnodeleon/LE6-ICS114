/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Header;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jinno
 */
public class MyPDF extends HttpServlet {
/*
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
    */

    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
      response.setContentType("application/pdf");
          Document document = new Document();
         try {
              PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
              document.open();
              Paragraph paragraph = new Paragraph();
              paragraph.add("this is a test");
              document.add(paragraph);
         } catch (DocumentException ex) {
             Logger.getLogger(MyPDF.class.getName()).log(Level.SEVERE, null, ex);
         }
          



}
}
