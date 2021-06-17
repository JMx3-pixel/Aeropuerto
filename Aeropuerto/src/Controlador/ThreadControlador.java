/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean Paul
 */
public class ThreadControlador extends Thread{
    private Socket socketRef;
    private boolean running = true;
    public DataOutputStream writer;
    public DataInputStream reader;
    public ServidorControlador server;
    public String nombre;
    
    public ThreadControlador(Socket socketRef, ServidorControlador server) throws IOException {
        this.socketRef = socketRef;
        this.writer = new DataOutputStream(socketRef.getOutputStream());
        this.reader = new DataInputStream(socketRef.getInputStream());
        this.server = server;
        nombre = "";
    }
    
    @Override
    public void run (){
            String instruccionId;
            try {
                instruccionId = reader.readUTF(); // esperar hasta que reciba un string
                switch (instruccionId){
                    case "nombre":
                        nombre = reader.readUTF();
                        System.out.println("nombre: " + nombre);
                        break;
                    case "avion":
                        String avion = reader.readUTF();
                        server.avionesString.add(avion);
                        System.out.println("avion " +avion+ " recibido");
                        
                    default:
                        break;
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch(Exception e){
                System.out.println("error error");
            }
    }
    
    public void escribir(String texto){
        try {
            writer.writeUTF(texto);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(ThreadControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarAvion(){
          
    }
}
