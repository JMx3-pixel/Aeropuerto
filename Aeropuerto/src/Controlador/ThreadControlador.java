/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ClasesCompartidas.Funciones;
import ClasesCompartidas.Mensaje;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    public ObjectInputStream reader;
    public ObjectOutputStream writer;
    public DataInputStream readerUTF;
    public ServidorControlador server;
    public String nombre;
    
    public ThreadControlador(Socket socketRef, ServidorControlador server) throws IOException {
        this.socketRef = socketRef;
        this.server = server;
        this.reader = new ObjectInputStream(socketRef.getInputStream());
        //System.out.println("hola");
        this.writer = new ObjectOutputStream(socketRef.getOutputStream());
        this.readerUTF = new DataInputStream(socketRef.getInputStream());
        this.nombre = "";
    }
    
    /**
     *
     */
    @Override
    public void run (){
        Mensaje instruccionId;
        while (running){
            try {
                instruccionId = (Mensaje) reader.readObject();
                switch (instruccionId){
                    case ENVIONOMBRE:
                        nombre = readerUTF.readUTF();
                        System.out.println(nombre + " Registrado");
                    case CREACIONAVIONES:
                        for (int i = 0; i < server.conexiones.size(); i++)
                            if(server.conexiones.get(i).nombre == "VentanaControlador")
                                server.conexiones.get(i).escribir(Mensaje.REENVIOAVIONES);
                        System.out.println("Recibido");
                        break;
                        
                    default:
                        break;
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void escribir(Mensaje sms) throws IOException{
        writer.writeObject(sms);
    }

}
