/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos;

import java.util.*;


public class Slots{
    
    public final static int CANTIDAD_NUMEROS = 45;
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
        return aleatorio.nextInt(CANTIDAD_NUMEROS + 1);        
    }
    
    /**
     * metodo que aleatoriza tres resultados y los asigna a los valores de los slots
     */
    public void girarRollos(){
        int aleatorio;
        
        aleatorio = aleatorio();
        if(aleatorio>=0 && aleatorio<10 ){
             resultado1 = CEREZA;
        }else if(aleatorio>=10 && aleatorio<18){
             resultado1 = UVA;
        }else if(aleatorio>=18 && aleatorio<25){
             resultado1 = NARANJA;
        }else if(aleatorio>=25 && aleatorio<31){
             resultado1 = LIMON;
        }else if(aleatorio>=31 && aleatorio<36){
             resultado1 = BAR;
        }else if(aleatorio>=36 && aleatorio<40){
             resultado1 = CAMPANA;
        }else if(aleatorio>=40 && aleatorio<43){
             resultado1 = SANDIA;
        }else if(aleatorio>=43 && aleatorio<45){
             resultado1 = PLATANO;
        }else if(aleatorio==45 ){
             resultado1 = TRIPLESIETE;
        }
        
        aleatorio = aleatorio();
        if(aleatorio>=1 && aleatorio<15 ){
             resultado2 = CEREZA;
        }else if(aleatorio>=15 && aleatorio<27){
             resultado2 = UVA;
        }else if(aleatorio>=27 && aleatorio<38){
             resultado2 = NARANJA;
        }else if(aleatorio>=38 && aleatorio<47){
             resultado2 = LIMON;
        }else if(aleatorio>=47 && aleatorio<55){
             resultado2 = BAR;
        }else if(aleatorio>=55 && aleatorio<62){
             resultado2 = CAMPANA;
        }else if(aleatorio>=62 && aleatorio<68){
             resultado2 = SANDIA;
        }else if(aleatorio>=68 && aleatorio<70){
             resultado2 = PLATANO;
        }else if(aleatorio==70 ){
             resultado2 = TRIPLESIETE;
        }
        
        aleatorio = aleatorio();
        if(aleatorio>=1 && aleatorio<15 ){
             resultado3 = CEREZA;
        }else if(aleatorio>=15 && aleatorio<27){
             resultado3 = UVA;
        }else if(aleatorio>=27 && aleatorio<38){
             resultado3 = NARANJA;
        }else if(aleatorio>=38 && aleatorio<47){
             resultado3 = LIMON;
        }else if(aleatorio>=47 && aleatorio<55){
             resultado3 = BAR;
        }else if(aleatorio>=55 && aleatorio<62){
             resultado3 = CAMPANA;
        }else if(aleatorio>=62 && aleatorio<68){
             resultado3 = SANDIA;
        }else if(aleatorio>=68 && aleatorio<70){
             resultado3 = PLATANO;
        }else if(aleatorio==70 ){
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
