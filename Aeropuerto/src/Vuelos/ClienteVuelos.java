/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vuelos;

import ClasesCompartidas.*;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean Paul
 */
public class ClienteVuelos {
    public Socket socketRef;
    public DataOutputStream writer;
    public ArrayList<Avion> aviones;
    public String nombre;
    int contador;
    
    public ClienteVuelos(){
        contador = Funciones.getRandom(3, 13);
        aviones = new ArrayList<Avion>();
        nombre = "Vuelos";
    }
    
    public void conectar(){
        enviarNombre();
        for (int i = 0; i < contador; i++) {
            enviarAviones(i);
        }
    }
    
    public void enviarNombre(){
        try {
            socketRef = new Socket("localhost", 35578);
            writer = new DataOutputStream(socketRef.getOutputStream());
            escribir("nombre");
            escribir(nombre);
            socketRef.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteVuelos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarAviones(int i){
        try {
            socketRef = new Socket("localhost", 35578);
            writer = new DataOutputStream(socketRef.getOutputStream());
            
            escribir("avion");
            String avion = crearAvion(i);
            
            escribir(avion);
            //System.out.println("Avion " + i +" enviado");
            socketRef.close();
            sleep(2000);
        } catch (IOException ex) {
            Logger.getLogger(ClienteVuelos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClienteVuelos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escribir(String texto){
        try {
            writer.writeUTF(texto);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClienteVuelos.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public String crearAvion(int i){
        Avion avion = new Avion(i);
        avion.doRandom();
        //hacer el el avion string
        return i+"";
    }
    
}
