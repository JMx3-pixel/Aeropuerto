/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaControlador;

/**
 *
 * @author Jean Paul
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //PantallaControlador pantalla = new PantallaControlador();
        //pantalla.setVisible(true);
        ClienteVentana cliente = new ClienteVentana();
        cliente.conectar();
        
        cliente.start();
    }
}
