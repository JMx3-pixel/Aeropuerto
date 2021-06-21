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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean Paul
 */
public class ClienteVentana extends Thread{
    public Socket socketRef;
    public DataOutputStream writer;
    public DataInputStream reader;
    public ArrayList<Avion> aviones;
    public ArrayList<Avion> avionesPrevios;
    public String nombre;
    public boolean running;
    public PantallaControlador refPantalla;
    
    public ClienteVentana() {
        aviones = new ArrayList<Avion>();
        avionesPrevios = new ArrayList<Avion>();
        nombre = "VentanaControlador";
        running = true;
        refPantalla = new PantallaControlador();
        
        refPantalla.cliente = this;
        refPantalla.setVisible(true);
    }
    
    public void conectar(){
        try{
                socketRef = new Socket("localhost", 35578);
                reader = new DataInputStream(socketRef.getInputStream());
                writer = new DataOutputStream(socketRef.getOutputStream());
                escribir(nombre);
                //System.out.println("pidiendo avion");
                //sleep(1000);
                avionesPrevios = JsonClass.arrayFromString(reader.readUTF());
                System.out.println(avionesPrevios.size() + " aviones recibidos");
                socketRef.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        copiarAviones();
    }
    
    public void copiarAviones(){
        for (int i = 0; i < avionesPrevios.size(); i++) {
            aviones.add(avionesPrevios.get(i));
            refPantalla.setItems();
            refPantalla.actualizar();
            try {
                sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClienteVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void run(){
        refPantalla.setItems();
        refPantalla.actualizar();
        refPantalla.actualizarCmb();
        while(running){
            refPantalla.actualizar();
            try{
                Thread.sleep(1000);
                for (int i = 0; i < this.aviones.size(); i++) {
                
                    if(this.aviones.get(i).tiempo > 0)
                        this.aviones.get(i).tiempo --;
                    else
                        this.aviones.get(i).aterrizado = true;
                    refPantalla.actualizar();
                    enviarAviones();
                }
            }
            catch(InterruptedException e){
                
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
