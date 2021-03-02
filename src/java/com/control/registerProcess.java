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
import java.sql.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
/**
 *
 * @author Jinno
 */
public class registerProcess extends HttpServlet {
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


public String[] signUp(String loginUser, String loginPass, String confloginPass, ResultSet rs) throws SQLException {
            String ar[] = new String[2];
             if ((loginUser.equals("")) || (loginPass.equals("")) || (confloginPass.equals(""))) {
                 ar[0]="no entry";
                return ar;
            } 
             do{
                 String username = rs.getString("username");
                 if(!username.equals(loginUser)){
                     if(loginPass.equals(confloginPass)){
                     ar[0]="Eligible for Sign Up";
                     return ar;
                     }
                     else{
                         ar[0]="Passwords are not the same";
                         return ar;
                     }
                 }
                 else{
                     ar[0]="Username Already Exists";

                 }
             }while(rs.next());
             

return ar;
        }
//ServletContext context = getServletContext();
                        
//String myCipher = context.getInitParameter("myCipher");
 //String draftKey = getInitParameter("Key");
 //String keyFormat = getInitParameter("keyFormat");
 //   private byte[] key = {'l','a','w','r','e','n','c','e','d','e','c','a','m','o','r','a'};
/*	public String encrypt(String strToEncrypt) {
		String encryptedString = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return encryptedString;
	}
        */

       private static byte[] key = {
	 	0x74, 0x68, 0x69, 0x73, 0x69, 0x73, 0x6a, 0x69, 0x6e, 0x6e, 0x6f, 0x64, 0x73, 0x6b, 0x65, 0x79 };
    
    
	public static String encrypt(String strToEncrypt) {
		String encryptedString = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return encryptedString;
	}

 
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("regName");
        String pass = request.getParameter("regPass");
        String pass2 = request.getParameter("regconfPass");
        String role = request.getParameter("role");
        RequestDispatcher rd;
        try {

            if (conn != null) {
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from userdb");
                if (!rs.next()){
                String encPass = encrypt(pass);
                String sql = "INSERT INTO UserDB (USERNAME, PASSWORD, ROLE) VALUES ('"+user+"', '"+encPass+"', '"+role+"')";
                PreparedStatement ps = conn.prepareStatement(sql);
                //rs.next();
                registerProcess control = new registerProcess();
                ps.execute();
                request.setAttribute("user", user);
                    request.setAttribute("role", role);
                    rd = request.getRequestDispatcher("success.jsp");
                    rd.include(request, response); 
                }else{
                    registerProcess control = new registerProcess();
                    String[] register = control.signUp(user,pass,pass2,rs);
                    if(register[0].equals("Eligible for Sign Up")){
                    String encPass = encrypt(pass);
                String sql = "INSERT INTO UserDB (USERNAME, PASSWORD, ROLE) VALUES ('"+user+"', '"+encPass+"', '"+role+"')";
                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.execute();
                request.setAttribute("user", user);
                    request.setAttribute("role", role);
                    rd = request.getRequestDispatcher("success.jsp");
                    rd.include(request, response); 
                    }
                    else{
                                            request.setAttribute("error", register[0]);
                    request.getRequestDispatcher("error.jsp").include(request, response);
                    }
                }
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
    }
     
        
      


  


