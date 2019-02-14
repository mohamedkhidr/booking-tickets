<%-- 
    Document   : main
    Created on : Dec 12, 2017, 10:27:31 PM
    Author     : khidr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="tickets.Account" %>
<%@page import="tickets.Ticket" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Page</title>
    </head>
    <body>
        <jsp:useBean id="ticket" scope="session" class="tickets.Ticket"></jsp:useBean>
        <jsp:useBean id="acc" scope="session" class="tickets.Account"></jsp:useBean>
        
         <%if(request.getParameter("fan")!=null  ){ %>
         <% if (ticket.isIndexSwitched()){ %>
        <meta http-equiv="refresh" content="0;url=./Fans Tickets2.html">
        <%}else {%>
        <meta http-equiv="refresh" content="0;url=./soldout.html">
        <%}%>
    
         <%}else if(request.getParameter("member")!=null){ %>
      <% if (ticket.isIndexSwitched()){ %>
        <meta http-equiv="refresh" content="0;url=./log in.html">
        <%}else {%>
        <meta http-equiv="refresh" content="0;url=./soldout.html">
        <%}%>
        
         <%}else if(request.getParameter("f_Submit")!=null){ %>
           <%    String fan_fname = request.getParameter("f_fname");
             String fan_lname = request.getParameter("f_lname");
             String fan_n_id = request.getParameter("f_n_id");
             String fan_t_class = request.getParameter("f_t_class");
             String fan_n_tickets=request.getParameter("f_n_tickets");%>
             <% if (ticket.isFanSwitched(fan_fname, fan_lname, fan_n_id, fan_t_class, Integer.parseInt(fan_n_tickets))==1){ %>
        <meta http-equiv="refresh" content="0;url=./pay.html">
         <%}else if (ticket.isFanSwitched(fan_fname, fan_lname, fan_n_id, fan_t_class, Integer.parseInt(fan_n_tickets))==0) {%>
         <meta http-equiv="refresh" content="0;url=./no_id.html">
         <%}else{%>
         <meta http-equiv="refresh" content="0;url=./error.html">
         <%}%>
         <%}else if(request.getParameter("login")!=null){ %>
          <%String m_user=request.getParameter("username");
            String m_pass = request.getParameter("password");%> 
            <% if (ticket.isLoginSwitched(m_user, m_pass)==1){ %>
          
            <meta http-equiv="refresh" content="0;url=./member tickets.html">
           <%}else if (ticket.isLoginSwitched(m_user, m_pass)==0) {%>
           <meta http-equiv="refresh" content="0;url=./no_user.html">
          <%}else{%>
                     <meta http-equiv="refresh" content="0;url=./error.html">
          <%}%>
           
     
        
         <%}else if(request.getParameter("m_Submit")!=null){ %>
         <%String m_ticket_class=request.getParameter("m_class");
            String m_num_of_tickets = request.getParameter("m_n_tickets");%>
            <%if(ticket.completeMemberTicket(m_ticket_class, Integer.parseInt(m_num_of_tickets))){%>
          <meta http-equiv="refresh" content="0;url=./pay.html">
          <%}else{%>
          <meta http-equiv="refresh" content="0;url=./no_id.html">
          <%}%>
         <%}else if(request.getParameter("pay")!=null){ %>
              <%String card_number = request.getParameter("card_number");
            String ex_month = request.getParameter("ex_month");
             String ex_year = request.getParameter("ex_year");
             String cvv =request.getParameter("cvv");%>
             <% if (acc.isPaySwitched(card_number, ex_month, ex_year, cvv)==1){ %>
             <% if (ticket.getTicketPrice()<=acc.getBalance()){ %>
             <%ticket.book();%>
             <%acc.withdrawal(ticket.getTicketPrice());%>
              <meta http-equiv="refresh" content="0;url=./done.html">
            <%}else {%>
               <meta http-equiv="refresh" content="0;url=./enoughcredit.html">
            <%}%>
        <%}else if (acc.isPaySwitched(card_number, ex_month, ex_year, cvv)==0) {%>
        <meta http-equiv="refresh" content="0;url=./no_card.html">
        <%}else {%>
        <meta http-equiv="refresh" content="0;url=./error.html">
        <%}}%>
        
        
    </body>
</html>
