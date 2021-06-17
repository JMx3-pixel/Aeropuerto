/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VentanaInformacion;

import ClasesCompartidas.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Jean Paul
 */
public class ClienteInformacion {
    public Socket socketRef;
    public DataOutputStream writer;
    public DataInputStream reader;
    public ArrayList<Avion> aviones;
    public String nombre;
    
    public ClienteInformacion(){
        aviones = new ArrayList<Avion>();
        nombre = "VentanaInformacion";
    }
    
    public void conectar(){
        try{
            socketRef = new Socket("localhost", 35578);
            writer = new DataOutputStream(socketRef.getOutputStream());
            reader = new DataInputStream(socketRef.getInputStream());
            System.out.println("Solicitud enviada");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public void leerInformacion(){
        aviones = JsonClass.readJson("aviones");
    }
}
