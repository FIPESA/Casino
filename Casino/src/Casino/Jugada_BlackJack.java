/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casino;

import ConexionBD.OperacionesSQL;
import java.util.Date;

/**
 *
 * @author alumno
 */
public class Jugada_BlackJack extends Jugada{
    
    private double cantidadApostada;
    private double cantidadGanada;
    private int cartasJugador;
    private int cartasCroupier;

    public Jugada_BlackJack(double cantidadApostada, double cantidadGanada, int cartasJugador, int cartasCroupier) {
        super();
        this.cantidadApostada = cantidadApostada;
        this.cantidadGanada = cantidadGanada;
        this.cartasJugador = cartasJugador;
        this.cartasCroupier = cartasCroupier;        
    }

    public Jugada_BlackJack(double cantidadApostada, double cantidadGanada, int cartasJugador, int cartasCroupier, Date fecha) {
        super(fecha);
        this.cantidadApostada = cantidadApostada;
        this.cantidadGanada = cantidadGanada;
        this.cartasJugador = cartasJugador;
        this.cartasCroupier = cartasCroupier;
    }    

    public double getCantidadApostada() {
        return cantidadApostada;
    }

    public double getCantidadGanada() {
        return cantidadGanada;
    }

    public int getCartasJugador() {
        return cartasJugador;
    }

    public int getCartasCroupier() {
        return cartasCroupier;
    }
    
    @Override
    public void guardar(String usuario) {
        OperacionesSQL.instancia().addJugada_Blackjack(this, usuario);
    }
    
    
    
}
