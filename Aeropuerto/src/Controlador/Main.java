/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ClasesCompartidas.JsonClass;

/**
 *
 * @author Jean Paul
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ServidorControlador controlador = new ServidorControlador();
        controlador.start();
        
    }
    
}
