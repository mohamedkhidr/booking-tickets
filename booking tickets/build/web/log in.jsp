<%-- 
    Document   : log in
    Created on : Dec 8, 2017, 11:29:17 PM
    Author     : khidr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
           <%String m_user=request.getParameter("username");
            String m_pass = request.getParameter("password");%> 
         
           <% if(m_user ==null || m_pass == null || m_user.length()==0||m_pass.length()==0 ||m_pass.length()>20||m_user.length()>20){%>
                  
              <meta http-equiv="refresh" content="0;url=./error.html"> 

           <%}else{%>
           
<meta http-equiv="refresh" content="0;url=./pay.html">
<!--db-->
        <%}%>
    </body>
</html>
