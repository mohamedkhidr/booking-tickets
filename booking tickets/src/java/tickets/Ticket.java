/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickets;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mohamed Mahmoud
 */
public class Ticket {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author khidr
 */
    
    final  private int A =200;
    final private int B=150 ;
    final private int C=100 ;
private String firstName ;
private String lastName ; 
private String nationalId  ;
private String ticketClass;
private int numOfTickets;
private String userName;
private String password;

public void creatFanTicket (String firstName, String lastName, String nationalId,String ticketClass,int numOfTickets){
this.firstName = firstName;
this.lastName = lastName ; 
this.nationalId = nationalId;
this.ticketClass = ticketClass;
this.numOfTickets = numOfTickets;

}
public void creatMemberTicket (String userName, String password){
this.userName = userName;
this.password = password ; 
}
public boolean completeMemberTicket (String ticketClass,int numOfTickets) throws IOException, SQLException{
this.ticketClass = ticketClass;
this.numOfTickets = numOfTickets;
   ResultSet resultset= Db.setStat().executeQuery("SELECT firstname,lastname,national_id from khidr.MEMBER where username = '"+userName+"' and password ='"+password+"'");
   if(resultset.next()){
         firstName = resultset.getString("firstname");
         lastName = resultset.getString("lastname");
         nationalId = resultset.getString("national_id");
   }
         resultset= Db.setStat().executeQuery("SELECT national_id  from khidr.TICKET where username = '"+userName+"'");
        if(resultset.next())
            if(nationalId==resultset.getString("national_id"))
                return false ;
        return true ;

}
public boolean authentication () throws SQLException, IOException {
     ResultSet resultset= Db.setStat().executeQuery("SELECT username , password  from khidr.MEMBER where username = '"+userName+"'and password ='"+password+"'");
   if(resultset.next())
   if(resultset.getString("username").equals(userName) && resultset.getString("password").equals(password))
    return true ; 
return false ;   
  
}

   
public int getTicketPrice(){
    
    int price =0;
  if(ticketClass.equals("A")) price = numOfTickets*200 ;
  else if (ticketClass.equals("B")) price = numOfTickets*150;
  else if (ticketClass.equals("C")) price = numOfTickets*100 ;
       
   return price ;
}

public void book () throws IOException, SQLException{

Db.setStat().executeUpdate("insert into khidr.TICKET (firstname , lastname , national_id , ticketclass , number_of_tickets )"
            + " values ('"+firstName+"','"+lastName+"','"+nationalId+"','"+ticketClass+"',"+numOfTickets+")");

    
}
public static boolean isAvailable() throws IOException, SQLException{
  ResultSet resultest = Db.setStat().executeQuery("select sum(number_of_tickets) from khidr.TICKET ");
  if(resultest.next())
      if( resultest.getString(1)!=null && 100==Integer.parseInt(resultest.getString(1)) )
          return false ;
       return true;   
}
    
public boolean isIndexSwitched() throws IOException, SQLException{
    if(isAvailable())
        return true ;
        return false ;
    
}
public int isFanSwitched(String firstname, String lastname, String national_id,String ticketClass,int numOfTickets) throws IOException, SQLException{
    ResultSet resultset= Db.setStat().executeQuery("SELECT national_id  from khidr.TICKET where national_id = '"+national_id+"'");
    if(isString(firstname) && isEnough(firstname, 10,false) && isAlpha(firstname) && isString(lastname) && isEnough(lastname, 10,false) && isAlpha(lastname) && isString(national_id) && isEnough(national_id, 14,true) && isNumeric(national_id)){   
    if(resultset.next())
    if(!resultset.getString("national_id").equals(national_id)){ 
    creatFanTicket(firstname, lastname, national_id, ticketClass, numOfTickets);
    }
    else return 0 ;
    }
    else
    return -1  ;
    return 1;
}
public int isLoginSwitched (String username, String password) throws SQLException, IOException{
    if(isString(username) && isEnough(username, 10,false) &&isString(password) && isEnough(password, 10,false)  ){
    creatMemberTicket(username, password);
    if(authentication())
        return 1 ;
    else 
        return 0;
    }
    return -1 ;
    
}


   public static boolean isNumeric(String str)  
{  
  try  
  {  
BigInteger number = new BigInteger(str);  
}  

  catch(NumberFormatException nfe)  
  {  
      System.out.println("ex");
    return false;  
  }  
  return true;  
} 
  public static boolean isAlpha(String input) {
    for (int i = 0; i != input.length(); ++i) {
        if (!Character.isLetter(input.charAt(i))) {
            return false;
        }
    }


    return true;
}

  public static boolean isString(String input) {
    if(input == null || input.length()==0) return false;
    return true ;
    }

 public static boolean  isEnough(String input,int digits , boolean isConst) {
    if(!isConst && input.length() <= digits || isConst && input.length()== digits) return true;
    return false ;
    }

    /**
     * @param args the command line arguments
     */
    public static  void main(String[] args) throws IOException, SQLException {
     
    
  
        
       
        // TODO code application logic here
    }
    
}

    

