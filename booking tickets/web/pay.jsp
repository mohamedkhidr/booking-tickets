<%-- 
    Document   : pay
    Created on : Dec 8, 2017, 11:29:48 PM
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
        <%!   public static boolean isNumeric(String str)  
{  
  try  
  {  
    double d = Double.parseDouble(str);  
  }  
  catch(NumberFormatException nfe)  
  {  
    return false;  
  }  
  return true;  
} %>
       <%String card_number = request.getParameter("card_number");
            String ex_month = request.getParameter("ex_month");
             String ex_year = request.getParameter("ex_year");
             String cvv =request.getParameter("cvv");%>
             
  <% if(card_number == null || ex_month == null ||ex_year == null ||cvv == null || card_number.length()==0||ex_month.length()==0||ex_year.length()==0||cvv.length()==0){%>
                  
              <meta http-equiv="refresh" content="0;url=./error.html"> 

           <%}else if (!isNumeric(card_number)||!isNumeric(cvv)||cvv.length()!=3||card_number.length()!=16){%>
           
<meta http-equiv="refresh" content="0;url=./error.html">

        <%}else{%>
        <meta http-equiv="refresh" content="0;url=./done.html">
        <!--db-->
          <% }%> 
    </body>
</html>
