/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casino;

import ConexionBD.OperacionesSQL;
import java.util.*;

/**
 *
 * @author alumno
 */
public class Jugada_Slots extends Jugada{
    
    private int resultado1;
    private int resultado2;
    private int resultado3;
    private double cantidaGanada;

    public Jugada_Slots(int resultado1, int resultado2, int resultado3, double cantidaGanada) {
        super();
        this.resultado1 = resultado1;
        this.resultado2 = resultado2;
        this.resultado3 = resultado3;
        this.cantidaGanada = cantidaGanada;
    }
    
    public Jugada_Slots(int resultado1, int resultado2, int resultado3, double cantidaGanada, Date fecha) {
        super(fecha);
        this.resultado1 = resultado1;
        this.resultado2 = resultado2;
        this.resultado3 = resultado3;
        this.cantidaGanada = cantidaGanada;
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

    public double getCantidaGanada() {
        return cantidaGanada;
    }
    
    /**
     * Guarda la jugada en la base de datos
     * @param usuario al que pertenece la jugada
     */
    @Override
    public void guardar(String usuario) {
        OperacionesSQL.instancia().addJugada_Slots(this, usuario);
    }
    
    
    
    


    
    
    
}
