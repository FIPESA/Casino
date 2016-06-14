/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos.Ruleta;
import java.util.Random;



/**
 *
 * @author alumno
 */
public class Ruleta{
    
    
    
    private int resultado;

    public Ruleta() {
    }    
    
    /**
     * Metodo estatico que genera un numero entre 0 y 26
     * @return un aleatorio entre 0 y 26
     */
    public static int aleatorio(){
        Random aleatorio = new Random();
        
        return aleatorio.nextInt(27);
    }
    
    /**
     * guarda en el atributo resultado un numero generado aleatoriamente
     */
    public void girarNumero(){
        resultado = aleatorio();
    }
    
    public int getResultado() {
        return resultado;
    }
}
