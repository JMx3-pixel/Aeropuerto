/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vuelos;

/**
 *
 * @author Jean Paul
 */
public class Avion {
    int tamano;
    int tipo;
    boolean aTiempo;
    public Avion(int tamano, int tipo, boolean atiempo){
        this.tamano = tamano;
        this.aTiempo = atiempo;
        this.tipo = tipo;
    }
    @Override
    public String toString(){
        return "tamano: " + tamano + ", a tiempo: " + aTiempo + ", tipo: " + tipo;
    }
}
