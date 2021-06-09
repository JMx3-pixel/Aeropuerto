/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesCompartidas;

import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Jean Paul
 */
public class JsonClass { 

        public ArrayList<String> datos;

    public JsonClass() {
        this.datos = new ArrayList<String>();
    }
    
        public void readJson(){
            Gson gson = new Gson();

            try (Reader reader = new FileReader("src\\ClasesCompartidas\\aviones.json")) {
                
                // Convert JSON File to Java Object
                Avion[] arregloAvion = gson.fromJson(reader, Avion[].class);
                
                for(Avion avion : arregloAvion) {
                    System.out.println(avion.toString());
                }
            } 
            catch (IOException e) {
                System.out.println("Error al leer el json");
            }
        }
        
        public void writeJson(){
            Gson gson = new Gson();

            
        }
}
