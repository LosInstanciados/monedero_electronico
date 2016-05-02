/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author Mariana Villegas
 */
public class Mysql {
    private static String db = "monedero_electronico";
    private static String user = "root";
    private static String pass = "";
    private static String url = "jdbc:mysql://localhost/" + db;
    private static Connection Conn;
    
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Conn = DriverManager.getConnection(url,user,pass);
            
        }
        catch(Exception e){ 
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
        return Conn;
    }
    
}
