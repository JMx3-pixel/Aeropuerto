/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ClasesCompartidas.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean Paul
 */
public class ThreadControlador extends Thread{
    private Socket socketRef;
    public DataOutputStream writer;
    public DataInputStream reader;
    public ServidorControlador server;
    
    public ThreadControlador(Socket socketRef, ServidorControlador server) throws IOException {
        this.socketRef = socketRef;
        this.writer = new DataOutputStream(socketRef.getOutputStream());
        this.reader = new DataInputStream(socketRef.getInputStream());
        this.server = server;
    }
    
    @Override
    public void run (){
            String instruccionId;
            try {
                instruccionId = reader.readUTF(); // esperar hasta que reciba un string
                switch (instruccionId){
                    case "avion":
                        String avion = reader.readUTF();
                        Avion actual = JsonClass.fromString(avion);
                         server.aviones.add(actual);
                        System.out.println("avion " + actual.codigo + " recibido");
                        break;
                    case "VentanaControlador":
                        System.out.println("enviando aviones");
                        String array = JsonClass.arrayString(server.aviones);
                        escribir(array);
                        break;
                    case "reenvio":
                        System.out.println("aviones modificados");
                        server.avionesString = reader.readUTF();
                        break;
                    case "consulta":
                        escribir(server.avionesString);
                        //System.out.println("aviones enviados a informacion");
                        break;
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
    
}
