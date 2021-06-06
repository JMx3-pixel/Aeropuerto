/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Jean Paul
 */
public class ThreadControlador extends Thread{
    private Socket socketRef;
    private boolean running = true;
    ServidorControlador server;
    
    public ThreadControlador(Socket socketRef, ServidorControlador server) throws IOException {
        this.socketRef = socketRef;
        this.server = server;
    }
    
    public void run (){
        int instruccionId = 1;
        while (running){
            /*instruccionId = reader.readInt(); // esperar hasta que reciba un entero
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
            }*/ 
        }
    }
}
