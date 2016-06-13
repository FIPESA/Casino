/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos;

/**
 *
 * @author Lara
 */
public enum Frutas {
    CEREZA,UVA,NARANJA,LIMON,BAR,CAMPANA,SANDIA,PLATANO,SIETE;
    
    
    public int toInt(){
        int valor = 0;
        if(this == CEREZA){
            valor = 1;
        } else if(this == UVA){
            valor = 2;
        } else if(this == NARANJA){
            valor = 3;
        } else if(this == LIMON){
            valor = 4;
        } else if(this == BAR){
            valor = 5;
        } else if(this == CAMPANA){
            valor = 6;
        } else if(this == SANDIA){
            valor = 7;
        } else if(this == PLATANO){
            valor = 8;
        } else if(this == SIETE){
            valor = 9;
        }
        
        return valor;
    }
}
