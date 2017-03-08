/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author sebastian
 */
public class ArchivosTexto {
    public static void escribirOCrearTxt(String ruta,String escribir) throws IOException{
        /*File f=new File(ruta);
        FileWriter fw = new FileWriter(f);
        PrintWriter pw=new PrintWriter(fw);
        pw.println(escribir);    
        pw.flush();*/
        File archivo = new File(ruta);
        BufferedWriter bw;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(escribir);//reescribo el fichero
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(escribir);
            //creo el fichero
        }
        bw.close();
    }
    
    public static String leer(String ruta){
    String contiene="";
    try{
        File f=new File(ruta);
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String linea="";

        while((linea=br.readLine())!=null)
        {
        contiene=contiene+linea+'\n';
        }
    }catch(IOException ioex)
    {
        System.out.println("error en la lectura del archivo");
    }
    return contiene;
    }
    
}
