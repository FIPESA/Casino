
package Juegos.Slots;

import Casino.Jugada_Slots;
import Exceptions.*;
import Juegos.Jugable;
import Usuarios.*;
import Usuarios.User;



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
        
        if(slots.getFruta1()== slots.getFruta2()&& slots.getFruta1()== slots.getFruta3()){
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
        
        if(slots.getFruta1() == Frutas.SIETE){
            premio = 1000;
        } else if(slots.getFruta1() == Frutas.PLATANO){
            premio = 500;
        } else if(slots.getFruta1() == Frutas.SANDIA){
            premio = 200;
        } else if(slots.getFruta1() == Frutas.CAMPANA){
            premio = 100;
        } else if(slots.getFruta1() == Frutas.BAR){
            premio = 50;
        } else if(slots.getFruta1() == Frutas.LIMON){
            premio = 10;
        } else if(slots.getFruta1() == Frutas.NARANJA){
            premio = 2;
        } else if(slots.getFruta1() == Frutas.UVA){
            premio = 1;
        } else if(slots.getFruta1() == Frutas.CEREZA){
            premio = 0.5;
        }
        
        return premio;
    }
  
    /**
     * Metodo principal de juego de los Slots
     * @return una Jugada_Slots, previamente es almacenada en la base de datos
     * @throws ImposibleJugar si no hay fondos disponibles
     */
    @Override
    public Jugada_Slots jugar() throws ImposibleJugar, RentaException {
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
                jugada = new Jugada_Slots(slots.getFruta1().toInt(), slots.getFruta2().toInt(), slots.getFruta3().toInt(), ganancia);
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
    
