/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaInformacion;

import ClasesCompartidas.*;
import Vuelos.ClienteVuelos;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean Paul
 */
public class ClienteInformacion extends Thread{
    public Socket socketRef;
    public DataOutputStream writer;
    public DataInputStream reader;
    public ArrayList<Avion> aviones;
    public String avionesString;
    public boolean running;
    public PantallaInformacion pantalla;
    
    public ClienteInformacion(){
        pantalla = new PantallaInformacion();
        aviones = new ArrayList<Avion>();
        running = true;
        pantalla.setVisible(true);
    }
    
    @Override
    public void run(){
        while(running){
            try{
                socketRef = new Socket("localhost", 35578);
                writer = new DataOutputStream(socketRef.getOutputStream());
                reader = new DataInputStream(socketRef.getInputStream());
                escribir("consulta");
                avionesString = reader.readUTF();
                aviones = JsonClass.arrayFromString(avionesString);
                sleep(500);
            }
            catch(Exception e){
                System.out.println(e.getMessage());     
            }
            
            pantalla.setInformacion(this);
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
