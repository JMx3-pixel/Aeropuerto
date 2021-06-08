/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vuelos;

import ClasesCompartidas.JsonClass;
import VentanaControlador.*;

/**
 *
 * @author Jean Paul
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteVuelos vuelos = new ClienteVuelos();
        vuelos.conectar();
        vuelos.crearAviones();
        vuelos.enviarAviones();
    }
}
