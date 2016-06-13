/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos;

import javax.swing.Icon;

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
    
    public Icon toIcon(){
        Icon icono = null;
        if(this == CEREZA){
            icono = new javax.swing.ImageIcon(getClass().getResource("/resources/Slots/Cerezas.png"));
        } else if(this == UVA){
            icono = new javax.swing.ImageIcon(getClass().getResource("/resources/Slots/Uvas.png"));
        } else if(this == NARANJA){
            icono = new javax.swing.ImageIcon(getClass().getResource("/resources/Slots/Naranja.png"));
        } else if(this == LIMON){
            icono = new javax.swing.ImageIcon(getClass().getResource("/resources/Slots/Limon.png"));
        } else if(this == BAR){
            icono = new javax.swing.ImageIcon(getClass().getResource("/resources/Slots/Bar.png"));
        } else if(this == CAMPANA){
            icono = new javax.swing.ImageIcon(getClass().getResource("/resources/Slots/Campana.png"));
        } else if(this == SANDIA){
            icono = new javax.swing.ImageIcon(getClass().getResource("/resources/Slots/Sandia.png"));
        } else if(this == PLATANO){
            icono = new javax.swing.ImageIcon(getClass().getResource("/resources/Slots/Platanos.png"));
        } else if(this == SIETE){
            icono = new javax.swing.ImageIcon(getClass().getResource("/resources/Slots/Siete.png"));
        }
        
        return icono;
    }
}
