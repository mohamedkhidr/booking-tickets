/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mohamed Mahmoud
 */
public class Account {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    private String cardNumber ;
private String expMonth ; 
private String expYear;
private String cvv;
private int balance;
public Account(){
    
}
public void creatAccount (String cardNumber,String expMonth,String expYear,String cvv){
this.cardNumber = cardNumber ;
this.expMonth = expMonth ; 
this.expYear = expYear;
this.cvv = cvv;

}


public boolean authentication() throws SQLException, IOException{
    ResultSet resultset= Db.setStat().executeQuery("SELECT card_number , ex_month , ex_year , cvv   from khidr.BANK where card_number = '"+cardNumber+"' and ex_month ='"+expMonth+"'"+
            "and ex_year ='"+expYear+"' and cvv = '"+cvv+"'");
   if(resultset.next())
   if(resultset.getString("card_number").equals(cardNumber) && resultset.getString("ex_month").equals(expMonth) && resultset.getString("ex_year").equals(expYear) && resultset.getString("cvv").equals(cvv))
    return true ; 
return false ;   
  
}
public int getBalance() throws IOException, SQLException{
    int balance = 0 ;
      ResultSet resultest = Db.setStat().executeQuery("select balance from khidr.BANK where card_number = '"+cardNumber+"'");
  if(resultest.next())
     balance = Integer.parseInt(resultest.getString("balance"));
  return balance ;
}
public void withdrawal(int balance) throws IOException, SQLException{
    balance =getBalance()-balance ;
     Db.setStat().executeUpdate("update khidr.BANK set balance ="
            +balance+"where card_number = '"+cardNumber+"'");
}
public int isPaySwitched(String cardNumber,String expMonth,String expYear,String cvv) throws SQLException, IOException{
if(Ticket.isString(cardNumber) && Ticket.isEnough(cardNumber,16, true) && Ticket.isNumeric(cardNumber)&&Ticket.isString(cvv) && Ticket.isEnough(cvv,3, true) && Ticket.isNumeric(cvv)){
    creatAccount(cardNumber, expMonth, expYear, cvv);
    if(authentication())
        return 1;
    else return 0;
}
return -1 ;
}




    
}

    

