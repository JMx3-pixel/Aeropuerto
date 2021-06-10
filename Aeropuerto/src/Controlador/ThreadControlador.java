/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ClasesCompartidas.Mensaje;
import VentanaControlador.ThreadVentana;
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
    public DataOutputStream writerUTF;
    public DataInputStream readerUTF;
    public ServidorControlador server;
    public String nombre;
    
    public ThreadControlador(Socket socketRef, ServidorControlador server) throws IOException {
        this.socketRef = socketRef;
        this.reader = new ObjectInputStream(socketRef.getInputStream());
        this.writer = new ObjectOutputStream(socketRef.getOutputStream());
        this.writerUTF = new DataOutputStream(socketRef.getOutputStream());
        this.readerUTF = new DataInputStream(socketRef.getInputStream());
        this.server = server;
        nombre = "";
    }
    
    public void run (){
        Mensaje instruccionId;
        while (running){
            try {
                instruccionId = (Mensaje) reader.readObject(); // esperar hasta que reciba un enum
                
                switch (instruccionId){
                    case CREACIONAVIONES: // pasan el nombre del usuario
                        nombre = reader.readUTF();
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
    
    public void escribir(Mensaje sms){
        try {
            writer.writeObject(sms);
        } catch (IOException ex) {
            Logger.getLogger(ThreadVentana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escribirTexto(String texto){
        try {
            writerUTF.writeUTF(texto);
        } catch (IOException ex) {
            Logger.getLogger(ThreadVentana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
