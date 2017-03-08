/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import logica.Validaciones;
import logica.base.Conexion;
import logica.base.Select;

/**
 *
 * @author sebastian
 */
public class FrmVerActas extends javax.swing.JFrame{

    private ButtonGroup bgSeleccionBusqueda;
    /**
     * Creates new form FrmVerActas
     */
    public FrmVerActas() {
        initComponents();
        agruparBotones();
        llenarTablaInicial();
      }
    private void agruparBotones(){
        bgSeleccionBusqueda=new ButtonGroup();
        //colocamos los tres radio butons en un buttonGroup una busqueda a la vez
        bgSeleccionBusqueda.add(this.rbNumeroActa);
        bgSeleccionBusqueda.add(this.rbFecha);
        bgSeleccionBusqueda.add(this.rbLugar);
    }
    private void buscarPorNumeroActa(){
    String numero;
    numero=this.tfNumeroActa.getText();
    numero.replaceAll("\\s","");
    if(numero.length()==0){
        this.lbMensaje.setText("INTRODUZCA UN NUMERO EN LA CASILLA AL FRENTE DE NUMERO DE ACTA");
    }else{
        Validaciones v=new Validaciones();
        if(v.validarNumeros(numero)){
            //busqueda
            Conexion con=new Conexion();
            Connection cone=con.conectar("acta2","root","");
            Select select=new Select(cone);
            ResultSet rs=select.select("ACTA", "ACTAID", numero);
            llenarTabla(rs,cone);
        }else{
        this.lbMensaje.setText("DEBEN IR SOLO NUMEROS EN LA CASILLA");
        }
    }
    //this.
    }
    
    private void buscarPorFecha(){
    String fecha1,fecha2;
    fecha1=this.tfFechaDesde.getText();
    fecha2=this.tfFechaHasta.getText();
        System.out.println(""+fecha1+" "+fecha2);
    }
    
    private void llenarTabla(ResultSet rs,Connection cone){//llena la tabla con el rs/encabezados y cierra la conexion
    DefaultTableModel modelo = new DefaultTableModel();
    this.tbRegistros.setModel(modelo);
    ResultSetMetaData rsMd=null;
       if(rs!=null){
                try {
                //this.lb_informacion.setText(cadena);
                rsMd = rs.getMetaData();
                //La cantidad de columnas que tiene la consulta
                int cantidadColumnas = rsMd.getColumnCount();
                //Establecer como cabezeras el nombre de las colimnas
                for (int i = 1; i <= cantidadColumnas; i++) {
                 modelo.addColumn(rsMd.getColumnLabel(i));
                }
                //System.out.println("3");
            //Creando las filas para el JTable
            //modelo.setColumnIdentifiers(new Object[]("id","casa","cedula","nom"));
                 
                while (rs.next()) {
                Object fila[];
                fila = new Object[cantidadColumnas];
                for(int i = 0; i < cantidadColumnas; i++) {
                    fila[i]=rs.getObject(i+1);
                }
                modelo.addRow(fila);
             }
        } catch (SQLException ex) {
            //Logger.getLogger(registros.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                try {
                    rs.close();
                    cone.close();
                } catch (SQLException ex) {
                    //Logger.getLogger(registros.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
        this.lbMensaje.setText("todavia no hay registros con el criterio");
        }
    }
    
    private void llenarTablaInicial() {
      Conexion con=new Conexion();
      Connection cone=con.conectar("acta2","root","");
      Select select=new Select(cone);
      ResultSet rs=select.selectTabla("ACTA");
      llenarTabla(rs,cone);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgBusqueda = new javax.swing.ButtonGroup();
        rbNumeroActa = new javax.swing.JRadioButton();
        rbFecha = new javax.swing.JRadioButton();
        rbLugar = new javax.swing.JRadioButton();
        tfNumeroActa = new javax.swing.JTextField();
        tfFechaDesde = new javax.swing.JTextField();
        tfFechaHasta = new javax.swing.JTextField();
        tfLugar = new javax.swing.JTextField();
        lbDesde = new javax.swing.JLabel();
        lbHasta = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        lbMensaje = new javax.swing.JLabel();
        spScrollDeTablaActas = new javax.swing.JScrollPane();
        tbRegistros = new javax.swing.JTable();
        btnGenerarTxtActa = new javax.swing.JButton();
        lbActaTxt = new javax.swing.JLabel();
        tfNumeroActaATxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rbNumeroActa.setText("numero acta");

        rbFecha.setText("fecha");

        rbLugar.setText("lugar");

        tfFechaHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFechaHastaActionPerformed(evt);
            }
        });

        lbDesde.setText("desde");

        lbHasta.setText("hasta");

        btnBuscar.setText("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lbMensaje.setForeground(new java.awt.Color(255, 0, 0));

        tbRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spScrollDeTablaActas.setViewportView(tbRegistros);

        btnGenerarTxtActa.setText("generar");

        lbActaTxt.setText("llevar acta a un archivo de texto acta n:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbNumeroActa)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(rbLugar)
                                .addComponent(rbFecha)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbDesde)
                                        .addGap(18, 18, 18)
                                        .addComponent(tfFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbHasta)))
                                .addGap(18, 18, 18)
                                .addComponent(tfFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfNumeroActa, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbActaTxt)
                        .addGap(18, 18, 18)
                        .addComponent(tfNumeroActaATxt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnGenerarTxtActa)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spScrollDeTablaActas))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbNumeroActa)
                            .addComponent(tfNumeroActa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbFecha)
                            .addComponent(tfFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDesde)
                            .addComponent(lbHasta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbLugar)
                            .addComponent(tfLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spScrollDeTablaActas, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarTxtActa)
                    .addComponent(lbActaTxt)
                    .addComponent(tfNumeroActaATxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(lbMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfFechaHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFechaHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFechaHastaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if(this.rbNumeroActa.isSelected()){
        this.buscarPorNumeroActa();
        }else{
        if(this.rbFecha.isSelected()){
        this.buscarPorFecha();
        }else{
        if(this.rbLugar.isSelected()){
        //lugar
        }else{
        this.lbMensaje.setText("Debe escoger una de las opciones para buscar");
        }
        }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmVerActas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVerActas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVerActas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVerActas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVerActas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgBusqueda;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerarTxtActa;
    private javax.swing.JLabel lbActaTxt;
    private javax.swing.JLabel lbDesde;
    private javax.swing.JLabel lbHasta;
    private javax.swing.JLabel lbMensaje;
    private javax.swing.JRadioButton rbFecha;
    private javax.swing.JRadioButton rbLugar;
    private javax.swing.JRadioButton rbNumeroActa;
    private javax.swing.JScrollPane spScrollDeTablaActas;
    private javax.swing.JTable tbRegistros;
    private javax.swing.JTextField tfFechaDesde;
    private javax.swing.JTextField tfFechaHasta;
    private javax.swing.JTextField tfLugar;
    private javax.swing.JTextField tfNumeroActa;
    private javax.swing.JTextField tfNumeroActaATxt;
    // End of variables declaration//GEN-END:variables

}