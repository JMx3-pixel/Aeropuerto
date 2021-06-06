/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vuelos;

import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Jean Paul
 */
public class ClienteVuelos {
    public Socket socketRef;
    
    public ClienteVuelos(){
        
    }
    
    public void conectar(){
        try{
            socketRef = new Socket("localhost", 35577);
            //hiloCliente.writer.writeInt(1); //instruccion para el switch del thraed servidor
            //hiloCliente.writer.writeUTF(nombre); //instruccion para el switch del thraed servidor
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
