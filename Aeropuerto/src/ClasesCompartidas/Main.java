/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesCompartidas;

import java.util.ArrayList;

/**
 *
 * @author Mauricio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Avion avion = new Avion(0, "s", "d", true);
        Avion avionb = new Avion(1, "b", "c", true);
        ArrayList<Avion> aviones = new ArrayList<Avion>();
        aviones.add(avion);
        aviones.add(avionb);
        JsonClass.writeJson(aviones, "prueba");
        String a;
        a = JsonClass.avionString(aviones);
        System.out.println(a);
    }

}
