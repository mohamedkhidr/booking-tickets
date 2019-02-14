/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Mohamed Mahmoud
 */
public class Db {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



 public static Connection con;
    public static Statement stat;

    public static Connection getConnection() throws  IOException {
        if (con == null) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/tickets", "khidr", "khidr");
                con.setAutoCommit(true);
            } catch (Exception ex) {
                System.out.println("connection " + ex.getMessage());
            }
        }
        return con;
    }

    public static Statement setStat() throws  IOException {
        try {
            stat = Db.getConnection().createStatement();
        } catch (Exception ex) {
            System.out.println("statement " + ex.getMessage());
        }
        return stat;
    }
    
}

    

