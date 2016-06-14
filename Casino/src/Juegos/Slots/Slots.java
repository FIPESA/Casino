/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos.Slots;

import java.util.*;


public class Slots{
    public final static int CANTIDAD_NUMEROS = 45;
    public final static Frutas[] ROLLO = {Frutas.CEREZA,Frutas.UVA,Frutas.NARANJA,Frutas.LIMON,Frutas.BAR,Frutas.CAMPANA,Frutas.SANDIA,Frutas.PLATANO,Frutas.SIETE,Frutas.CEREZA,Frutas.UVA,Frutas.NARANJA,Frutas.LIMON,Frutas.BAR,Frutas.CAMPANA,Frutas.SANDIA,Frutas.PLATANO,Frutas.CEREZA,Frutas.UVA,Frutas.NARANJA,Frutas.LIMON,Frutas.BAR,Frutas.CAMPANA,Frutas.SANDIA,Frutas.CEREZA,Frutas.UVA,Frutas.NARANJA,Frutas.LIMON,Frutas.BAR,Frutas.CAMPANA,Frutas.CEREZA,Frutas.UVA,Frutas.NARANJA,Frutas.LIMON,Frutas.BAR,Frutas.CEREZA,Frutas.UVA,Frutas.NARANJA,Frutas.LIMON,Frutas.CEREZA,Frutas.UVA,Frutas.NARANJA,Frutas.CEREZA,Frutas.UVA,Frutas.CEREZA};
    
    private int resultado1;
    private int resultado2;
    private int resultado3;
    
    private Frutas fruta1;
    private Frutas fruta2;
    private Frutas fruta3;

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
        this.resultado1 = aleatorio();
        this.resultado2 = aleatorio();
        this.resultado3 = aleatorio();
        
        this.fruta1 = ROLLO[resultado1];
        this.fruta2 = ROLLO[resultado2];
        this.fruta3 = ROLLO[resultado3];        
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

    public Frutas getFruta1() {
        return fruta1;
    }

    public Frutas getFruta2() {
        return fruta2;
    }

    public Frutas getFruta3() {
        return fruta3;
    }
    
    
}
