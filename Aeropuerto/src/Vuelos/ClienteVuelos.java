/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vuelos;

import ClasesCompartidas.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    public ObjectOutputStream writer;
    public DataOutputStream writerUTF;
    private JsonClass jsonObj;
    public ArrayList<Avion> aviones;
    public String nombre;
    
    public ClienteVuelos(){
        jsonObj = new JsonClass();
        aviones = new ArrayList<Avion>();
        nombre = "Vuelos";
    }
    
    public void conectar(){
        try{
            socketRef = new Socket("localhost", 35578);
            writer = new ObjectOutputStream(socketRef.getOutputStream());
            writerUTF = new DataOutputStream(socketRef.getOutputStream());
            escribir(Mensaje.ENVIONOMBRE);
            escribirTexto(nombre);
            System.out.println("Solicitud enviada");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        enviarAviones();
    }
    
    
    public void escribir(Mensaje sms){
        try {
            writer.writeObject(sms);
        } catch (IOException ex) {
            Logger.getLogger(ClienteVuelos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escribirTexto(String texto){
        try {
            writerUTF.writeUTF(texto);
        } catch (IOException ex) {
            Logger.getLogger(ClienteVuelos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void crearAviones(){
        int n = Funciones.getRandom(0, 20);
        for (int i = 0; i < n; i++) {
            Avion avion = new Avion();
            avion.doRandom();
            aviones.add(avion);
        }
    }
    
    public void enviarAviones(){
        //escribir los aviones en el json
        escribir(Mensaje.CREACIONAVIONES);
        System.out.println("Enviado");
    }
}
