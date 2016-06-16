/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos.Ruleta;
import Casino.*;
import Exceptions.*;
import Juegos.Jugable;
import Usuarios.*;


/**
 *
 * @author alumno
 */
public class Jugar_Ruleta implements Jugable{
    public static int[] ROJO = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
    public static int[] NEGRO = {2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};
    public static int[] PAR = {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36};
    public static int[] IMPAR = {1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35};
    public static int[] MITAD_BAJA = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
    public static int[] MITAD_ALTA = {19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36};
    
    public static int[] DOCENA_1 = {1,2,3,4,5,6,7,8,9,10,11,12};
    public static int[] DOCENA_2 = {13,14,15,16,17,18,19,20,21,22,23,24};
    public static int[] DOCENA_3 = {25,26,27,28,29,30,31,32,33,34,35,36};
    public static int[] COLUMNA_1 = {1,4,7,10,13,16,19,22,25,28,31,34};
    public static int[] COLUMNA_2 = {2,5,8,11,14,17,20,23,26,29,32,35};
    public static int[] COLUMNA_3 = {3,6,9,12,15,18,21,24,27,30,33,36};
    
    private User jugador;
    private Ruleta ruleta;
    private double dinero;
    private int[] apuesta;

    /**
     * Constructor para jugar a la ruleta
     * @param jugador
     * @param dinero
     * @param apuesta 
     */
    public Jugar_Ruleta(User jugador) {
        this.jugador = jugador;        
        this.ruleta = new Ruleta();       
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public void setApuesta(int[] apuesta) {
        this.apuesta = apuesta;
    }
    
    
    
    /**
     * metodo que comprueba que el numero apostado es ganador
     * @return 
     */
    public boolean comprobar(){
        boolean ganador = false;
        for(int i=0;i<apuesta.length;i++){
            if(ruleta.getResultado()==apuesta[i]){
                ganador = true;
            }
        }
        return ganador;        
    }
    
    /**
     * comprueba la ganancia segun el tipo de apuesta
     * @return entero por el que se ha de multiplicar la apuesta
     */
    public int multiplicador(){
        int multiplicador = 1;
        if(apuesta.length == 1){
            multiplicador = 36;
        } else if (apuesta.length == 2){
            multiplicador = 18;
        } else if (apuesta.length == 3){
            multiplicador = 12;
        } else if (apuesta.length == 4){
            multiplicador = 9;
        } else if (apuesta.length == 6){
            multiplicador = 6;
        } else if(apuesta.length == 12){
            multiplicador = 3;
        } else if(apuesta.length == 18){
            multiplicador = 2;
        }
        return multiplicador;
    }
    
    /**
     * metodo principal del juego de la ruleta
     * @return la ganancia
     * @throws Exceptions.ImposibleJugar en caso de que no existan fondos
     * @throws Exceptions.RentaException
     */
    @Override
    public Jugada_Ruleta jugar() throws ImposibleJugar, RentaException{
        Jugada_Ruleta jugada = null;
        double ganancia = 0;
        try {
            jugador.retirarFondos(this.dinero);
            ruleta.girarNumero();

            if(comprobar()){
                ganancia = this.dinero*this.multiplicador();
                jugador.añadirFondos(ganancia);
            }
            
            jugada = new Jugada_Ruleta(apuesta, resultado(), dinero, ganancia);
            jugada.guardar(this.jugador.getUsername());
            
        } catch (TransaccionIncorrecta ex) {
            throw new ImposibleJugar("");
        }
        return jugada;
    }
    
    /**
     * Metodo que devuelve el numero ganador
     * @return el numero ganador
     */
    public int resultado(){
        return this.ruleta.getResultado();
    }


    
    
    
    
    
    
    
}
