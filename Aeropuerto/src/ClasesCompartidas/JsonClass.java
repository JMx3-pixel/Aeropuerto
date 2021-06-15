/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesCompartidas;

import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Jean Paul
 */
public class JsonClass { 

        public ArrayList<String> datos;

    public JsonClass() {
        this.datos = new ArrayList<String>();
    }
    
        public static ArrayList<Avion> readJson(String direccion){
            Gson gson = new Gson();
            ArrayList<Avion> arreglo = new ArrayList();
            try (Reader reader = new FileReader("src\\ClasesCompartidas\\"+ direccion +".json")) {
                
                // Convert JSON File to Java Object
                Avion[] arregloAvion = gson.fromJson(reader, Avion[].class);
                
                arreglo.addAll(Arrays.asList(arregloAvion));
            } 
            catch (IOException e) {
                System.out.println("Error al leer el json");
            }
            return arreglo;
        }
        
        public static void writeJson(ArrayList<Avion> arregloAvion, String direccion){
            Gson gson = new Gson();

            try (Writer writer = new FileWriter("src\\ClasesCompartidas\\" + direccion +".json")) {
                Avion[] array = new Avion[arregloAvion.size()];
                for (int i = 0; i < arregloAvion.size(); i++) {
                    array[i] = arregloAvion.get(i);
                }
                gson.toJson(array, writer);            } 
            catch (IOException e) {
                System.out.println("Error al leer el json");
            }
        }
}
