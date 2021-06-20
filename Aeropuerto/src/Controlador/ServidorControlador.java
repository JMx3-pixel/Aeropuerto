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
public class ServidorControlador extends Thread{
    private ServerSocket server;
    private boolean running = true;
    public String avionesString;
    public ArrayList<Avion> aviones;
    public ArrayList<Puerta> listaPuerta;
    public ArrayList<Avion> listaPendientes;
    public ArrayList<Pista> listaPuertas;
    

    public ServidorControlador() {
        this.aviones = new ArrayList<Avion>();
        this.avionesString = "";
    }
    
    public void stopserver(){
        running = false;
    }
    
    @Override
    public void run(){
        try{
            server = new ServerSocket(35578);
            while (running) {
                //System.out.println("::Esperando conexion...");
                Socket nuevaConexion = server.accept();
                //System.out.println("Conexion aceptada");
                // nuevo thread
                ThreadControlador newThread = new ThreadControlador(nuevaConexion, this);
                newThread.run();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
