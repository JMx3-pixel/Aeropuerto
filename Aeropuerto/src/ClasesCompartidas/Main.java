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
        JsonClass json = new JsonClass();
        Avion avion = new Avion("s", "d", true);
        Avion avionb = new Avion("b", "c", true);
        ArrayList<Avion> aviones = new ArrayList<Avion>();
        aviones.add(avion);
        aviones.add(avionb);
        json.writeJson(aviones, "prueba");
    }

}
