<%-- 
    Document   : register
    Created on : 04 19, 20, 3:20:26 PM
    Author     : Jinno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LE5</title>
    </head>
    <center>

    <header>

<%ServletContext context = getServletContext();
                        
                         String header = context.getInitParameter("header");
                          
                          RequestDispatcher rd=request.getRequestDispatcher(header);
                        rd.include(request, response); %>
    </header>
    <body>
        <form action="registerProcess" method="post">
            Name:<input type="text" name="regName" placeholder="must be unique"/><br/>
            Password:<input type="password" name="regPass" placeholder="must be the same"/><br/>
Confirm Password:<input type="password" name="regconfPass" placeholder="must be the same"/><br/>
<label for="role">Select a Role:</label>

<select id="role" name="role">
  <option value="guest">Guest</option>
  <option value="admin">Admin</option>
</select><br/>
<input type="submit" value="Confirm Sign Up"/>
        </form>

    </body>
    <footer>
            <%=
                context.getAttribute("time")
            %>
    </footer>
    </center>
</html>

