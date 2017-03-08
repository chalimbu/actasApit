/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.base;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sebastian
 */
public class Conexion {
 public Connection conectar(String table,String user,String pass){
     Connection conn=null;
     try {
         
         String url = "jdbc:mysql://127.0.0.1:3306/"+table+"?user="+user+"&password="+pass;
         Driver d = (Driver)Class.forName("com.mysql.jdbc.Driver").newInstance();
         conn = DriverManager.getConnection(url);
         
         if(conn.isClosed()){
             JOptionPane.showMessageDialog(null, "se perdio la conexion con la base de datos contacte con soporte ");
         }
         return conn;
     } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
         JOptionPane.showMessageDialog(null, "se perdio la conexion con la base de datos contacte con soporte");
         Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
      }
     return conn;
    }   
}
