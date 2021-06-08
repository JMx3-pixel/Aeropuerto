/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vuelos;

import ClasesCompartidas.*;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Jean Paul
 */
public class ClienteVuelos {
    public Socket socketRef;
    public ObjectOutputStream writer;
    private JsonClass jsonObj;
    public ArrayList<Avion> aviones;
    public Mensaje mensaje;
    
    public ClienteVuelos(){
        jsonObj = new JsonClass();
        aviones = new ArrayList<Avion>();
    }
    
    public void conectar(){
        try{
            socketRef = new Socket("localhost", 35577);
            writer = new ObjectOutputStream(socketRef.getOutputStream());
            //hiloCliente.writer.writeInt(1); //instruccion para el switch del thraed servidor
            //hiloCliente.writer.writeUTF(nombre); //instruccion para el switch del thraed servidor
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public void crearAviones(){
        int n = Funciones.getRandom(0, 20);
        for (int i = 0; i < n; i++) {
            Avion avion = new Avion();
            avion.doRandom();
            aviones.add(avion);
        }
    }
    
    public void enviarAviones(){
        //escribir los aviones en el json
        Funciones.escribirMensaje(writer, mensaje.CREACIONAVION);
    }
}
