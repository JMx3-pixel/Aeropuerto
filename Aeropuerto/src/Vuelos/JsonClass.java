/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vuelos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Jean Paul
 */
public class JsonClass {

    
        public void readJson(){
        
            Gson gson = new Gson();

            try (Reader reader = new FileReader("src\\Vuelos\\aviones.json")) {
                
                // Convert JSON File to Java Object
                Avion[] arregloAvion = gson.fromJson(reader, Avion[].class);
                

                
                for(Avion avion : arregloAvion) {
                    System.out.println(avion.toString());
                }
                System.out.println("a");
            } 
            catch (IOException e) {
                System.out.println("b");
            }
            
        }
}
