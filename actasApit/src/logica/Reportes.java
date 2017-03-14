/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import logica.base.Select;

/**
 *
 * @author sebastian
 */
public class Reportes {
    public String generarReporteActa(Connection con,int numeroActa){
        String informacionActa="COMITÉ PRIMARIO \n" +
                    "AREA DE PROGRAMAS INFORMÁTICOS Y TELECOMUNICACIONES\n" +
                    "FACULTAD DE INGENIERÍAS\n" +
                    "ACTA N°:"+numeroActa;
        try {
            
            //informacionActa=informacionActa+"\n FECHA:"
            Select select=new Select(con);
            //fecha
            ResultSet rs=select.selectGeneral("SELECT DATE_FORMAT(FECHA, '%d-%m-%Y') AS FECHA FROM ACTA WHERE ACTAID="+numeroActa+";");
            rs.next();
            String fecha=(String)rs.getObject(1);
            //System.out.println(fecha);
            //hora
            rs=select.selectGeneral( "SELECT DATE_FORMAT(FECHA, '%H:%i:%s') AS FECHA FROM ACTA WHERE ACTAID="+numeroActa+";");
            rs.next();
            String hora=(String)rs.getObject(1);
            //System.out.println(" "+hora);
            //lugar
            rs=select.selectGeneral("SELECT LUGAR FROM ACTA WHERE ACTAID="+numeroActa+";");
            rs.next();
            String lugar=(String)rs.getObject(1);
            //System.out.println(lugar);
            
            informacionActa=informacionActa+"\nFECHA:"+fecha+"\n" +
                                            "HORA:"+hora+"\n" +
                                            "LUGAR:"+lugar+"\n" +"\n" +
                                            "ASISTENTES: \n";
            for (int i = 1; i < 5; i++) {
                switch(i){
                    case 1:
                        informacionActa+="\nCoordinador Programas Profesionales";
                        break; 
                    case 2:
                        informacionActa+="\n\nDocentes";
                        break;
                    case 3:
                        informacionActa+="\n\nAuxiliar Administrativa";
                        break;
                    case 4:
                        informacionActa+="\n\nAusente sin excusa";
                        break;
                }
                rs=select.selectGeneral("SELECT P.NOMBRE FROM ASISTENTE AS A INNER JOIN PROFESOR AS P ON A.ID_PROFESOR=P.PROFESOR_ID\n" +
                        "WHERE A.TIPO_ASISTENTE="+i+" AND A.NUMERO_ACTA="+numeroActa+";");
                while(rs.next()){
                informacionActa=informacionActa+"\n"+rs.getObject(1);
                }
            }
            rs=select.selectGeneral("SELECT ORDEN_DEL_DIA FROM ACTA WHERE ACTAID="+numeroActa+";");
            rs.next();
            String ordenDia=(String)rs.getObject(1);
            informacionActa+="\n Orden del dia:\n"+ordenDia;
            
            
            rs=select.selectGeneral("SELECT DESARROLLO FROM ACTA WHERE ACTAID="+numeroActa+";");
            if(rs.next()){
            String desarrolo=(String)rs.getObject(1);
            informacionActa+="\nDesarrolo:\n"+desarrolo;
            }
            //System.out.println(informacionActa);
        } catch (SQLException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return informacionActa;
    }
}
