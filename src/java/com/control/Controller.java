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
class urlAppender{
    
    public StringBuffer urlApp(String url, String host, String port, String databaseName){
        StringBuffer tempurl = new StringBuffer(url).append("://").append(host).append(":").append(port).append("/").append(databaseName);
    return tempurl;

    }
}
public class Controller extends HttpServlet {
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


public String[] verification(String loginUser, String loginPass, ResultSet rs) throws SQLException {
            String ar[] = new String[2];
             if ((loginUser.equals("")) || (loginPass.equals(""))) {
                 ar[0]="no entry";
                return ar;
            } 
             do{
                 String username = rs.getString("username");
                 String password = rs.getString("password");
                 String decPass = decrypt(password);
                 if(username.equals(loginUser)){
                     if(decPass.equals(loginPass)){
                     ar[0]="success";
                     ar[1]=rs.getString("role");
                     return ar;
                     }
                     else{
                         ar[0]="incorrect password";
                         return ar;
                     }
                 }
                 else{
                     ar[0]="incorrect username";

                 }
             }while(rs.next());

return ar;
        }

private static byte[] key = {
	 	0x74, 0x68, 0x69, 0x73, 0x69, 0x73, 0x6a, 0x69, 0x6e, 0x6e, 0x6f, 0x64, 0x73, 0x6b, 0x65, 0x79 };

	public static String decrypt(String codeDecrypt){
		String decryptedString = null;
		try{
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			decryptedString = new String(cipher.doFinal(Base64.decodeBase64(codeDecrypt)));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return decryptedString;
	}
 
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("userName");
        String pass = request.getParameter("userPass");
        RequestDispatcher rd;
        try {

            if (conn != null) {
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from userdb");
                HttpSession session = request.getSession();
                Controller control = new Controller();
                if(!rs.next()){
                                        request.setAttribute("error", "database is empty");
                    request.getRequestDispatcher("error.jsp").include(request, response);
                }
                String[] verify = control.verification(user, pass, rs);
                
                if (verify[0].equals("success")) {
                    
                    session.setAttribute("user", user);
                    session.setAttribute("role", verify[1]);
                    request.setAttribute("user", user);
                    request.setAttribute("role", verify[1]);
                    request.setAttribute("results", rs);
                    rd = request.getRequestDispatcher("success.jsp");
                    rd.include(request, response); 
                    rs.close();
                } else {
                    request.setAttribute("error", verify[0]);
                    request.getRequestDispatcher("error.jsp").include(request, response);
                }
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
    }
     
        
      


  


