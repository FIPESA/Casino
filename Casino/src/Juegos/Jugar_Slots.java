
package Juegos;

import Casino.Jugada_Slots;
import Exceptions.*;
import Usuarios.*;
import Usuarios.User;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Jugar_Slots implements Jugable{
    
    private final static double APUESTA = 0.5;
    private User jugador;
    private Slots slots; 
    
    
    public Jugar_Slots(User jugador){       
        this.jugador = jugador;
        this.slots = new Slots();
    }
    
    /**
     * metodo uqe comprueba si los 3 resultados de los slots son el mismo
     * @return true si son iguales
     */
    public boolean comprobar(){
        boolean ganador = false;
        
        if(slots.getResultado1() == slots.getResultado2() && slots.getResultado1() == slots.getResultado3()){
            ganador = true;
        }
        
        return ganador;
    }
    
    /**
     * metodo que devuelve cuanta ganancia se tiene dependiendo del tipo de premio
     * @return la cantidad ganada
     */
    public double premios(){
        double premio = 0;
        
        if(slots.getResultado1() == Slots.TRIPLESIETE){
            premio = 1000;
        } else if(slots.getResultado1() == Slots.PLATANO){
            premio = 750;
        } else if(slots.getResultado1() == Slots.SANDIA){
            premio = 600;
        } else if(slots.getResultado1() == Slots.CAMPANA){
            premio = 400;
        } else if(slots.getResultado1() == Slots.BAR){
            premio = 200;
        } else if(slots.getResultado1() == Slots.LIMON){
            premio = 100;
        } else if(slots.getResultado1() == Slots.NARANJA){
            premio = 50;
        } else if(slots.getResultado1() == Slots.UVA){
            premio = 25;
        } else if(slots.getResultado1() == Slots.CEREZA){
            premio = 10;
        }
        
        return premio;
    }
  
    /**
     * Metodo principal de juego de los Slots
     * @return una Jugada_Slots, previamente es almacenada en la base de datos
     * @throws ImposibleJugar si no hay fondos disponibles
     */
    @Override
    public Jugada_Slots jugar() throws ImposibleJugar {
        double ganancia = 0;
        Jugada_Slots jugada = null;
        try {
            jugador.retirarFondos(APUESTA);
            slots.girarRollos();
            if(comprobar()){
                ganancia = this.premios();
                jugador.añadirFondos(ganancia);
                
            }
            if(!(jugador instanceof F2P)){
                jugada = new Jugada_Slots(slots.getResultado1(), slots.getResultado2(), slots.getResultado3(), ganancia);
                jugada.guardar(this.jugador.getUsername());
            }
            
        } catch (TransaccionIncorrecta ex) {
            throw new ImposibleJugar("Error al añadir o retirar fondos");
        }
        return jugada;
    }

    public Slots getSlots() {
        return slots;
    }
}
    
