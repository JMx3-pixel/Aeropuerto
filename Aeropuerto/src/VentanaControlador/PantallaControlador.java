/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaControlador;

import ClasesCompartidas.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mauricio
 */

public class PantallaControlador extends javax.swing.JFrame {
    ClienteVentana cliente;
     
    /**
     * Creates new form PantallaVuelos
     */
   
    public PantallaControlador() {
        initComponents();
        
        //Avion a1 = new Avion(1, "Carga", true);
        //Avion a2 = new Avion(2, "Pasajeros", true);
        //aviones.add(a1);
        //aviones.add(a2);
        
        //aviones = JsonClass.readJson("aviones");
    }
    
    Puerta puerta1 = new Puerta("carga");
    Puerta puerta2 = new Puerta("pasajeros");
    Puerta puerta3 = new Puerta("privado");
    Pista pista1 = new Pista("carga");
    Pista pista2 = new Pista("pasajeros");
    Pista pista3 = new Pista("privado");
    
    
    void setItems(){
        cmbPista.removeAllItems();
        cmbPuerta.removeAllItems();
        this.cmbPista.addItem("Carga");
        this.cmbPista.addItem("Pasajeros");
        this.cmbPista.addItem("Privado");
        this.cmbPuerta.addItem("Carga");
        this.cmbPuerta.addItem("Pasajeros");
        this.cmbPuerta.addItem("Privado");
    }
    
    void asignarPista(int codigo, String pista){
        Avion avion = null;
        
        for (int i = 0; i < cliente.aviones.size(); i++) {
            
            if(cliente.aviones.get(i).codigo == codigo){
                avion = cliente.aviones.get(i);
                break;
            }
            
        }
        if(!(avion == null)){   
            if(avion.tiempo != 0){
                txfBit.append("Avion "+ avion.codigo + " no ha aterrizado.\n ");
                return;
            }
            switch(pista.toLowerCase()){
                case "carga":
                    System.out.println("" + avion.tamano);
                    if(avion.tamano.equals("Carga") && pista1.disponible){
                        txfBit.append("Avion "+ avion.codigo + " asignado a pista de carga.\n");
                        pista1.disponible = false;
                        avion.pista = 1;
                        actualizarCmb();
                        return;
                    }
                    if(!pista1.disponible){
                        txfBit.append("Avion "+ avion.codigo + " no asignado a pista de carga, pista llena.\n");
                        return;
                    }
                    txfBit.append("Avion "+ avion.codigo + " no asignado a pista de carga, pista incorrecta. \n");
                    return;
                case "pasajeros":
                    if(avion.tamano.equals("Pasajeros") && pista2.disponible){
                        txfBit.append("Avion "+ avion.codigo + " asignado a pista de pasajeros.\n");
                        pista2.disponible = false;
                        avion.pista = 2;
                        actualizarCmb();
                        return;
                    }
                    if(!pista2.disponible){
                        txfBit.append("Avion "+ avion.codigo + " no asignado a pista de pasajeros, pista llena.\n");
                        return;
                    }
                    txfBit.append("Avion "+ avion.codigo + " no asignado a pista de carga, pista incorrecta. \n");
                    return;
                case "privado":
                    if(avion.tamano.equals("Privado") && pista3.disponible){
                        txfBit.append("Avion "+ avion.codigo + " asignado a pista privada.\n");
                        pista3.disponible = false;
                        avion.pista = 3;
                        actualizarCmb();
                        return;
                    }
                    if(!pista3.disponible){
                        txfBit.append("Avion "+ avion.codigo + " no asignado a pista privada, pista llena.\n");
                        return;
                    }
                    txfBit.append("Avion "+ avion.codigo + " no asignado a pista privada, pista incorrecta. \n");
            }
        }
    }
    
    void asignarPuerta(int codigo, String puerta){
        Avion avion = null;
        for (int i = 0; i < cliente.aviones.size(); i++) {
            if(cliente.aviones.get(i).codigo == codigo){
                avion = cliente.aviones.get(i);
                break;
            }
            
        }
        if(!(avion == null)){   
            switch(puerta.toLowerCase()){
                case "carga":
                    if(avion.tamano.equals("Carga") && puerta1.disponible){
                        txfBit.append("Avion "+ avion.codigo + " asignado a puerta de carga.\n");
                        puerta1.disponible = false;
                        avion.tiempo += 10;
                        avion.puerta = 1;
                        avion.pista = -1;
                        pista1.disponible = true;
                        avion.refPuerta = puerta1;
                        actualizarCmb();
                        break;
                    }
                    if(!puerta1.disponible){
                        txfBit.append("Avion "+ avion.codigo + " no asignado a puerta de carga, puerta llena.\n");
                        return;
                    }
                    txfBit.append("Avion "+ avion.codigo + " no asignado a puerta de carga, puerta incorrecta. \n");
                    return;
                case "pasajeros":
                    if(avion.tamano.equals("Pasajeros") && puerta2.disponible){
                        txfBit.append("Avion "+ avion.codigo + " asignado a puerta de pasajeros.\n");
                        puerta2.disponible = false;
                        avion.tiempo += 10;
                        avion.puerta = 2;
                        avion.pista = -1;
                        pista2.disponible = true;
                        avion.refPuerta = puerta2;
                        actualizarCmb();
                        break;
                    }
                    if(!puerta2.disponible){
                        txfBit.append("Avion "+ avion.codigo + " no asignado a puerta de pasajeros, puerta llena.\n");
                        return;
                    }
                    txfBit.append("Avion "+ avion.codigo + " no asignado a puerta de carga, puerta incorrecta. \n");
                    return;
                case "privado":
                    if(avion.tamano.equals("Privado") && puerta3.disponible){
                        txfBit.append("Avion "+ avion.codigo + " asignado a puerta privada.\n");
                        puerta3.disponible = false;
                        avion.tiempo += 10;
                        avion.puerta = 3;
                        avion.pista = -1;
                        pista3.disponible = true;
                        avion.refPuerta = puerta3;
                        actualizarCmb();
                        break;
                    }
                    if(!puerta3.disponible){
                        txfBit.append("Avion "+ avion.codigo + " no asignado a puerta privada, puerta llena.\n");
                        return;
                    }
                    txfBit.append("Avion "+ avion.codigo + " no asignado a puerta privada, puerta incorrecta. \n");
            }
        }
    }
    
    
    void actualizarCmb(){
        cmbEnPista.removeAllItems();
        cmbAprox.removeAllItems();


        for (int i = 0; i < cliente.aviones.size(); i++) {
            Avion avion = cliente.aviones.get(i);
            if(avion.tiempo > 0 && !avion.aterrizado){

                cmbAprox.addItem("" + avion.codigo);
            }
            if(avion.aterrizado && avion.pista == -1 && avion.puerta == -1){

                cmbAprox.addItem("" +avion.codigo);
            }
            if(avion.aterrizado && avion.puerta == -1 && avion.pista != -1 ){

                cmbEnPista.addItem("" +avion.codigo);
            }
            if(avion.tiempo > 0 && avion.pista == -1 && avion.puerta != -1){
                
                cmbEnPista.addItem("" +avion.codigo);
            }
        }
    }
    
    
    void actualizar(){
        txfAprox.setText("");
        txfPista.setText("");
        for (int i = 0; i < cliente.aviones.size(); i++) {
            Avion avion = cliente.aviones.get(i);
            if(avion.tiempo > 0 && !avion.aterrizado){
                txfAprox.append("Avi??n n??mero "+ avion.codigo + ", de "+ avion.tamano + " aproxim??ndose en " + avion.tiempo +".\n");
                if (!avion.aTiempo)
                    avion.estado = "Con retraso";
                else
                    avion.estado = "Aproximando";
            }
            if(avion.aterrizado && avion.pista == -1 && avion.puerta == -1){
                txfAprox.append("Avi??n n??mero "+ avion.codigo + ", de "+ avion.tamano + " esperando pista.\n");
                avion.estado = "Aterrizado";
            }
            if(avion.aterrizado && avion.puerta == -1 && avion.pista != -1 ){
                txfPista.append("Avi??n n??mero "+ avion.codigo + ", de "+ avion.tamano + " esperando puerta.\n");
                avion.estado = "En pista";
            }
            if(avion.tiempo > 0 && avion.pista == -1 && avion.puerta != -1){
                txfPista.append("Avi??n n??mero "+ avion.codigo + ", de "+ avion.tamano + " llegando en " + avion.tiempo + " segundos a la puerta " + avion.puerta +".\n");
               avion.estado = "En taxi";
            }
            if(avion.tiempo == 0 && avion.pista == -1 && avion.puerta != -1){
                avion.tiempo = 5;
                avion.pista = -2;
            }
            if(avion.tiempo > 0 && avion.pista == -2){
                txfPista.append("Avi??n n??mero "+ avion.codigo + ", de "+ avion.tamano + " desembarcando en puerta " + avion.puerta + ", " + avion.tiempo + " segundos para terminar.\n");
                //cliente.aviones.remove(avion);
                avion.estado = "Desembarque";
                actualizarCmb();
                cliente.enviarAviones();
            }
            if(avion.tiempo == 0 && avion.pista == -2 && avion.puerta != -1){
                txfBit.append("Avi??n n??mero "+ avion.codigo + ", de "+ avion.tamano + " desembarc?? en puerta " + avion.puerta + " y est?? siendo guardado en un hangar.\n");
                //cliente.avionesPrevios.add(avion);
                cliente.aviones.remove(avion);
                avion.refPuerta.disponible = true;
                actualizarCmb();
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

        jLabel1.setText("Aviones aproxim??ndose");

        cmbPuerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPuertaActionPerformed(evt);
            }
        });

        jLabel2.setText("Pista a asignar");

        txfBit.setColumns(20);
        txfBit.setRows(5);
        jScrollPane1.setViewportView(txfBit);

        jLabel3.setText("Bit??cora");

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

        jLabel6.setText("Avi??n a elegir");

        jLabel7.setText("Avi??n a elegir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(106, 106, 106)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(128, 128, 128))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(118, 118, 118))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(94, 94, 94)
                            .addComponent(cmbPista, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(cmbAprox, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(botonPista)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEnPista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPuerta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(botonPuerta)
                        .addGap(105, 105, 105))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(14, 14, 14)
                        .addComponent(cmbEnPista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAprox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPuerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonPuerta)
                    .addComponent(botonPista))
                .addContainerGap(49, Short.MAX_VALUE))
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
        try{
            asignarPuerta(Integer.parseInt(cmbEnPista.getSelectedItem().toString()), cmbPuerta.getSelectedItem().toString() );
            setItems();
        }
        catch(Exception e){
            txfBit.append("No hay aviones para seleccionar.\n");
        }
    }//GEN-LAST:event_botonPuertaActionPerformed

    private void botonPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPistaActionPerformed
        try{
            asignarPista(Integer.parseInt(cmbAprox.getSelectedItem().toString()), cmbPista.getSelectedItem().toString() );
            cliente.enviarAviones();
            setItems();
        }
         catch(Exception e){
            txfBit.append("No hay aviones para seleccionar.\n");
        }
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
