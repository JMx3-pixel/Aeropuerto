/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaInformacion;

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
public class ThreadInformacion extends Thread{
    private Socket socketRef;
    public ObjectInputStream reader;
    public ObjectOutputStream writer;
    public DataInputStream readerUTF;
    public DataOutputStream writerUTF;
    public String nombre;
    private boolean running = true;

    public ThreadInformacion(Socket socketRef) throws IOException{
        this.socketRef = socketRef;
        reader = new ObjectInputStream(socketRef.getInputStream());
        writer = new ObjectOutputStream(socketRef.getOutputStream());
        readerUTF = new DataInputStream(socketRef.getInputStream());
        writerUTF = new DataOutputStream(socketRef.getOutputStream());
    }
    
    /**
     *
     */
    @Override
    public void run (){
        Mensaje instruccionId;
        while (running){
            try {
                instruccionId = (Mensaje) reader.readObject(); // esperar hasta que reciba un entero 
                switch (instruccionId){
                        
                    default:
                        break;
                }
                
            } catch (IOException ex) {
            }
            catch (ClassNotFoundException ex) {
                    Logger.getLogger(ThreadInformacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void escribir(Mensaje sms){
        try {
            writer.writeObject(sms);
        } catch (IOException ex) {
            Logger.getLogger(ThreadInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escribirTexto(String texto){
        try {
            writerUTF.writeUTF(texto);
        } catch (IOException ex) {
            Logger.getLogger(ThreadInformacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
