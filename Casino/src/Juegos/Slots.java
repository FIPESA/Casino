/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos;

import java.util.*;


public class Slots{
    
    public final static int CANTIDAD_NUMEROS = 9;
    public final static int TRIPLESIETE = 1;
    public final static int PLATANO = 2;
    public final static int SANDIA = 3;
    public final static int CAMPANA = 4;
    public final static int BAR = 5;
    public final static int LIMON = 6;
    public final static int NARANJA = 7;
    public final static int UVA = 8;
    public final static int CEREZA = 9;
    
    private int resultado1;
    private int resultado2;
    private int resultado3;

    public Slots() {
    }
    
    /**
     * Metodo que genera un numero aleatorio entre 0 y CANTIDAD_NUMEROS
     * @return 
     */
    public static int aleatorio(){
        Random aleatorio = new Random();
        return aleatorio.nextInt(CANTIDAD_NUMEROS);        
    }
    
    /**
     * metodo que aleatoriza tres resultados y los asigna a los valores de los slots
     */
    public void girarRollos(){
        int aleatorio;
        
        aleatorio = aleatorio();
        if(aleatorio==0){
             resultado1 = CEREZA;
        }else if(aleatorio==1){
             resultado1 = UVA;
        }else if(aleatorio==2){
             resultado1 = NARANJA;
        }else if(aleatorio==3){
             resultado1 = LIMON;
        }else if(aleatorio==4){
             resultado1 = BAR;
        }else if(aleatorio==5){
             resultado1 = CAMPANA;
        }else if(aleatorio==6){
             resultado1 = SANDIA;
        }else if(aleatorio==7){
             resultado1 = PLATANO;
        }else if(aleatorio==8){
             resultado1 = TRIPLESIETE;
        }
        
        aleatorio = aleatorio();
        if(aleatorio==0){
             resultado2 = CEREZA;
        }else if(aleatorio==1){
             resultado2 = UVA;
        }else if(aleatorio==2){
             resultado2 = NARANJA;
        }else if(aleatorio==3){
             resultado2 = LIMON;
        }else if(aleatorio==4){
             resultado2 = BAR;
        }else if(aleatorio==5){
             resultado2 = CAMPANA;
        }else if(aleatorio==6){
             resultado2 = SANDIA;
        }else if(aleatorio==7){
             resultado2 = PLATANO;
        }else if(aleatorio==8){
             resultado2 = TRIPLESIETE;
        }
        
        aleatorio = aleatorio();
        if(aleatorio==0){
             resultado3 = CEREZA;
        }else if(aleatorio==1){
             resultado3 = UVA;
        }else if(aleatorio==2){
             resultado3 = NARANJA;
        }else if(aleatorio==3){
             resultado3 = LIMON;
        }else if(aleatorio==4){
             resultado3 = BAR;
        }else if(aleatorio==5){
             resultado3 = CAMPANA;
        }else if(aleatorio==6){
             resultado3 = SANDIA;
        }else if(aleatorio==7){
             resultado3 = PLATANO;
        }else if(aleatorio==8 ){
             resultado3 = TRIPLESIETE;
        }
    }

    public int getResultado1() {
        return resultado1;
    }

    public int getResultado2() {
        return resultado2;
    }

    public int getResultado3() {
        return resultado3;
    }
}
