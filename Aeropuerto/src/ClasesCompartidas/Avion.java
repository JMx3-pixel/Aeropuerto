/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesCompartidas;

/**
 *
 * @author Jean Paul
 */
public class Avion {
    public int codigo;
    public String tamano;
    public boolean aTiempo;
    public int pista = -1;
    public int puerta = -1;
    public Puerta refPuerta;
    public String estado;
    public int tiempo;
    public boolean aterrizado = false;
    
    public Avion(int codigo){
        this.codigo = codigo;
        this.tamano = "";
        this.aTiempo = true;
        this.estado = "llegando";
    }
    
    public Avion(int codigo, String tamano, boolean atiempo){
        this.codigo = codigo;
        this.tamano = tamano;
        this.aTiempo = atiempo;
        this.estado = "llegando";
        if(! aTiempo)
            this.tiempo += 5;
    }
    
    @Override
    public String toString(){
        return "tamano: " + tamano + ", a tiempo: " + aTiempo;
    }
    
    public String tamanoAleatorio(){
        int n = Funciones.getRandom(0, 2);
        switch(n){
            case 0:
                return "Carga";
            case 1:
                return "Pasajeros";
            default:
                return "Privado";
        }
    }
    
    public String tipoAleatorio(){
        int n = Funciones.getRandom(0, 2);
        switch(n){
            case 0:
                return "Carga";
            case 1:
                return "Pasajeros";
            default:
                return "Privado";
        }
    }
    
    public boolean tiempoAleatorio(){
        int n = Funciones.getRandom(0, 1);
        return n == 0;
    }
    
    public void doRandom(int i){
        this.aTiempo = tiempoAleatorio();
        this.tamano = tamanoAleatorio();
        this.tiempo = i*2+2;
        if(!aTiempo)
            tiempo += 10;
    }
}
