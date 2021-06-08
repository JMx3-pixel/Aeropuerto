/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import ClasesCompartidas.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Jean Paul
 */
public class ServidorControlador {
    public ArrayList<ThreadControlador> conexiones;
    private boolean running = true;
    private ServerSocket server;
    public ArrayList<Puerta> listaPuerta;
    public ArrayList<Avion> listaPendientes;
    public ArrayList<Pista> listaPuertas;
    

    public ServidorControlador() {
        this.conexiones = new ArrayList<ThreadControlador>();
    }
    
    public void stopserver(){
        running = false;
    }
    
    public void runServer(){
        try{
            server = new ServerSocket(35577);
            while (running){
                Socket nuevaConexion = server.accept();
                // nuevo thread
                ThreadControlador newThread = new ThreadControlador(nuevaConexion, this);
                conexiones.add(newThread);
                newThread.start();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
