/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.base;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastian
 */
public class Select {
    
    Connection con=null;
    public Select(Connection con){
    this.con=con;
    }

    public  ResultSet select(String from ,String where ,String equal){
    ResultSet rs=null;
    Statement st;
                
                try{
                st=con.createStatement();
                rs=st.executeQuery("select * from "+from+" where "+where+"="+equal+";" );
                }catch(SQLException ex){
                    //System.out.println("no fue posible aceder a la tabla "+ from);
                }
    return rs;
    }
    
    public  ResultSet selectTabla(String from){
    ResultSet rs=null;
    Statement st;
                try{
                st=con.createStatement();
                rs=st.executeQuery("select * from "+from+";" );
                }catch(SQLException ex){
                    //System.out.println("no fue posible aceder a la tabla "+ from);
                }
    return rs;
    }
    public  Boolean selectLogin(String from,String where1,String equal1 ,String where2,String equal2){
    ResultSet rs;
    Statement st;
                try{
                st=con.createStatement();
                rs=st.executeQuery("SELECT * FROM "+from+
                        " WHERE "+ where1+"='"+equal1+"' AND "
                        +where2+"='"+equal2+"';");
                    if(rs.next()){
                        return true;
                    }
                }catch(SQLException ex){
                    //System.out.println("no fue posible aceder a la tabla "+ from);
                    return false;
                }
                return false;
    }
    
    public ResultSet selectGeneral(String select){
    ResultSet rs=null;
    Statement st;
                try{
                st=con.createStatement();
                rs=st.executeQuery(select);
                }catch(SQLException ex){
                    //System.out.println("no fue posible aceder a la tabla "+ from);
                }
    return rs;
    }
    

}