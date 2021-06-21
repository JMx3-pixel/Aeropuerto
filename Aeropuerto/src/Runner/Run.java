/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runner;

/**
 *
 * @author Mauricio
 */
public class Run {
    
    public static void main(String [] args){
        try{

            Controlador.Main.main(new String[0]);
            Vuelos.Main.main(new String[0]);
            VentanaControlador.Main.main(new String[0]);
            VentanaInformacion.Main.main(new String[0]);
        }
        catch(Exception e){
            System.out.println("a");
        }
    }
    
    
}
