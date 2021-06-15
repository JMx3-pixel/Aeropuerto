/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaControlador;

import ClasesCompartidas.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Jean Paul
 */
public class ClienteVentana {
    public Socket socketRef;
    public ThreadVentana hilo;
    private JsonClass jsonObj;
    public ArrayList<Avion> aviones;
    public String nombre;
    
    public ClienteVentana(){
        jsonObj = new JsonClass();
        aviones = new ArrayList<Avion>();
        nombre = "VentanaControlador";
    }
    
    public void conectar(){
        try{
            socketRef = new Socket("localhost", 35578);
            hilo = new ThreadVentana(socketRef, this);
            hilo.start();
            hilo.escribir(Mensaje.ENVIONOMBRE);
            hilo.escribirTexto(nombre);
            System.out.println("Solicitud enviada");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void leerAviones(){
        aviones = JsonClass.readJson("prueba");
    }
}
