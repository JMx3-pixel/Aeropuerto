/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ClasesCompartidas.Mensaje;
import java.io.IOException;
import java.io.ObjectInputStream;
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
    public ServidorControlador server;
    
    public ThreadControlador(Socket socketRef, ServidorControlador server) throws IOException {
        this.socketRef = socketRef;
        this.reader = new ObjectInputStream(socketRef.getInputStream());
        this.server = server;
    }
    
    public void run (){
        Mensaje instruccionId;
        while (running){
            try {
                instruccionId = (Mensaje) reader.readObject(); // esperar hasta que reciba un enum
            } catch (IOException ex) {
                Logger.getLogger(ThreadControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            switch (instruccionId){
            case 1: // pasan el nombre del usuario
            nombre = reader.readUTF();
            break;
            case 2: // pasan un mensaje por el chat
            String mensaje = reader.readUTF();
            for (int i = 0; i < server.conexiones.size(); i++) {
            ThreadServidor current = server.conexiones.get(i);
            current.writer.writeInt(2);
            current.writer.writeUTF(nombre);
            current.writer.writeUTF(mensaje);
            }
            break;
            }
        }
    }
}
