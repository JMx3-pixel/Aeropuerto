/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaControlador;

import ClasesCompartidas.*;
import Vuelos.ClienteVuelos;
import java.io.DataInputStream;
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
public class ClienteVentana{
    public Socket socketRef;
    public DataOutputStream writer;
    public DataInputStream reader;
    public ArrayList<Avion> aviones;
    public ArrayList<Avion> avionesPrevios;
    public String nombre;
    public String leido;
    public boolean running;
    
    public ClienteVentana() {
        aviones = new ArrayList<Avion>();
        avionesPrevios = new ArrayList<Avion>();
        nombre = "VentanaControlador";
        leido = "vacio";
        running = true;
    }
    
    public void conectar(){
        try{
                socketRef = new Socket("localhost", 35578);
                reader = new DataInputStream(socketRef.getInputStream());
                writer = new DataOutputStream(socketRef.getOutputStream());
                escribir(nombre);
                System.out.println("pidiendo avion");
                //sleep(1000);
                avionesPrevios = JsonClass.arrayFromString(reader.readUTF());
                System.out.println(avionesPrevios.size() + " aviones recibidos");
                copiarAviones();
                socketRef.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        
    }
    
    public void copiarAviones(){
        for (int i = 0; i < avionesPrevios.size(); i++) {
            aviones.add(avionesPrevios.get(i));
            try {
                sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClienteVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void enviarAviones(){
        try{
            socketRef = new Socket("localhost", 35578);
            reader = new DataInputStream(socketRef.getInputStream());
            writer = new DataOutputStream(socketRef.getOutputStream());
            escribir("reenvio");
            String listos = JsonClass.arrayString(aviones);
            escribir(listos);
            socketRef.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
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
}
