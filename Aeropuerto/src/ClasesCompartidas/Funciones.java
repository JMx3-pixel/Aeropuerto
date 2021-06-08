/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesCompartidas;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Jean Paul
 */
public class Funciones {
    
    public static int getRandom(int menor, int mayor){
        return (int)Math.floor(Math.random()*(mayor - menor + 1) + menor);
    }
    
    public static void escribirMensaje(ObjectOutputStream writer, Mensaje sms){
        try {
            writer.writeObject(sms);
        } catch (IOException ex) {
            System.out.println("Error al escribir");
        }
    }
    
}
