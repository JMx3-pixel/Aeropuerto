/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaControlador;

import VentanaInformacion.ClienteInformacion;
import ClasesCompartidas.*;
import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */

public class PantallaControlador extends javax.swing.JFrame {
    ClienteInformacion cliente;
    /**
     * Creates new form PantallaVuelos
     */
    ArrayList<Avion> aviones = new ArrayList<Avion>();
    public PantallaControlador() {
        cliente = new ClienteInformacion();
        cliente.conectar();
        initComponents();
        setItems();
        actualizar();
        aviones = JsonClass.readJson("aviones");
    }
    Pista pista1;
    Pista pista2;
    Pista pista3;
    
    
    void setItems(){
        pista1 = new Pista("carga");
        pista2 = new Pista("pasajeros");
        pista3 = new Pista("privado");
        Puerta puerta1 = new Puerta(1);
        Puerta puerta2 = new Puerta(2);
        Puerta puerta3 = new Puerta(3);
        this.cmbPista.addItem("Carga");
        this.cmbPista.addItem("Pasajeros");
        this.cmbPista.addItem("Privado");
        this.cmbPuerta.addItem("" +puerta1.num);
        this.cmbPuerta.addItem("" +puerta2.num);
        this.cmbPuerta.addItem("" +puerta3.num);
    }
    
    void asignarPista(int codigo, String pista){
        Avion avion = null;
        for (int i = 0; i < aviones.size(); i++) {
            if(aviones.get(i).codigo == codigo){
                avion = aviones.get(i);
                break;
            }
            
        }
        if(!(avion == null)){   
            switch(pista.toLowerCase()){
                case "carga":
                    if(avion.tamano.equalsIgnoreCase("carga") && pista1.disponible){
                        txfBit.append("Avion "+ avion.codigo + " asignado a pista de carga.");
                        pista1.disponible = false;
                        break;
                    }
                    if(!pista1.disponible){
                        txfBit.append("Avion "+ avion.codigo + " no asignado a pista de carga, pista llena.\n");
                        return;
                    }
                    txfBit.append("Avion "+ avion.codigo + " no asignado a pista de carga, pista incorrecta. \n");
                    
                case "pasajeros":
                    if(avion.tamano.equalsIgnoreCase("carga") && pista2.disponible){
                        txfBit.append("Avion "+ avion.codigo + " asignado a pista de carga.\n");
                        pista2.disponible = false;
                        break;
                    }
                    if(!pista2.disponible){
                        txfBit.append("Avion "+ avion.codigo + " no asignado a pista de pasajeros, pista llena.\n");
                        return;
                    }
                    txfBit.append("Avion "+ avion.codigo + " no asignado a pista de carga, pista incorrecta. \n");
                    
                case "privado":
                    if(avion.tamano.equalsIgnoreCase("carga") && pista3.disponible){
                        txfBit.append("Avion "+ avion.codigo + " asignado a pista privada.\n");
                        pista3.disponible = false;
                        break;
                    }
                    if(!pista3.disponible){
                        txfBit.append("Avion "+ avion.codigo + " no asignado a pista privada, pista llena.\n");
                        return;
                    }
                    txfBit.append("Avion "+ avion.codigo + " no asignado a pista privada, pista incorrecta. \n");
            }
        }
    }
    
    void actualizar(){
        cmbAprox.removeAllItems();
        cmbEnPista.removeAllItems();
        txfAprox.setText("");
        txfPista.setText("");
        Avion a1 = new Avion(1, "Carga", "Carga", true);
        Avion a2 = new Avion(2, "Pasajeros", "Pasajeros", true);
        aviones.add(a1);
        aviones.add(a2);
        for (int i = 0; i < aviones.size(); i++) {
            Avion avion = aviones.get(i);
            if(avion.tiempo > 0 && avion.puerta == -1){
                txfAprox.append("Avión número "+ avion.codigo + ", de "+ avion.tamano + " aproximándose en " + avion.tiempo +".\n");
                cmbAprox.addItem("" + avion.codigo);
            }
            if(avion.tiempo == 0 && avion.pista == -1 && avion.puerta == -1){
                txfAprox.append("Avión número "+ avion.codigo + ", de "+ avion.tamano + "esperando pista.\n");
                cmbAprox.addItem("" +avion.codigo);
            }
            if(avion.tiempo > 0 && avion.pista != -1 && avion.puerta == -1 ){
                txfPista.append("Avión número "+ avion.codigo + ", de "+ avion.tamano + "haciendo taxi a la puerta " + avion.puerta +".\n");
                cmbPista.addItem("" +avion.codigo);
            }
            if(avion.tiempo == 0 && avion.pista != -1 && avion.puerta != -1){
                txfPista.append("Avión número "+ avion.codigo + ", de "+ avion.tamano + "llegando en " + avion.tiempo + "segundos a la puerta " + avion.puerta +".\n");
                cmbPista.addItem("" +avion.codigo);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbPuerta = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txfBit = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbPista = new javax.swing.JComboBox<>();
        botonPuerta = new javax.swing.JButton();
        botonPista = new javax.swing.JButton();
        cmbAprox = new javax.swing.JComboBox<>();
        cmbEnPista = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txfAprox = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txfPista = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Aviones aproximándose");

        cmbPuerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPuertaActionPerformed(evt);
            }
        });

        jLabel2.setText("Pista a asignar");

        txfBit.setColumns(20);
        txfBit.setRows(5);
        jScrollPane1.setViewportView(txfBit);

        jLabel3.setText("Bitácora");

        jLabel4.setText("Aviones en pista");

        jLabel5.setText("Puerta a asignar");

        cmbPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPistaActionPerformed(evt);
            }
        });

        botonPuerta.setText("Asignar puerta");
        botonPuerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPuertaActionPerformed(evt);
            }
        });

        botonPista.setText("Asignar pista");
        botonPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPistaActionPerformed(evt);
            }
        });

        cmbAprox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAproxActionPerformed(evt);
            }
        });

        cmbEnPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEnPistaActionPerformed(evt);
            }
        });

        txfAprox.setColumns(20);
        txfAprox.setRows(5);
        jScrollPane2.setViewportView(txfAprox);

        txfPista.setColumns(20);
        txfPista.setRows(5);
        jScrollPane4.setViewportView(txfPista);

        jLabel6.setText("Avión a elegir");

        jLabel7.setText("Avión a elegir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addGap(373, 373, 373)
                .addComponent(jLabel4))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(380, 380, 380)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel2)
                .addGap(408, 408, 408)
                .addComponent(jLabel5))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(botonPista)
                .addGap(366, 366, 366)
                .addComponent(botonPuerta))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbAprox, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPista, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(351, 351, 351)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPuerta, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEnPista, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAprox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEnPista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPuerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonPista)
                    .addComponent(botonPuerta)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPuertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPuertaActionPerformed

    private void cmbPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPistaActionPerformed

    private void botonPuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPuertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonPuertaActionPerformed

    private void botonPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPistaActionPerformed
        asignarPista(Integer.parseInt(cmbAprox.getSelectedItem().toString()), cmbPista.getSelectedItem().toString() );
    }//GEN-LAST:event_botonPistaActionPerformed

    private void cmbAproxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAproxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAproxActionPerformed

    private void cmbEnPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEnPistaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEnPistaActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaControlador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaControlador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaControlador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaControlador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaControlador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonPista;
    private javax.swing.JButton botonPuerta;
    private javax.swing.JComboBox<String> cmbAprox;
    private javax.swing.JComboBox<String> cmbEnPista;
    private javax.swing.JComboBox<String> cmbPista;
    private javax.swing.JComboBox<String> cmbPuerta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea txfAprox;
    private javax.swing.JTextArea txfBit;
    private javax.swing.JTextArea txfPista;
    // End of variables declaration//GEN-END:variables
}
