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
public class Jugada_Ruleta extends Jugada{
    private int[] numerosApostados;
    private int resultado;
    private double cantidadApostada;
    private double cantidadGanada;

    public Jugada_Ruleta(int[] numerosApostados, int resultado, double cantidadApostada, double cantidadGanada) {
        super();
        this.numerosApostados = numerosApostados;
        this.resultado = resultado;
        this.cantidadApostada = cantidadApostada;
        this.cantidadGanada = cantidadGanada;
    }
    
    public Jugada_Ruleta(int[] numerosApostados, int resultado, double cantidadApostada, double cantidadGanada, Date fecha) {
        super(fecha);
        this.numerosApostados = numerosApostados;
        this.resultado = resultado;
        this.cantidadApostada = cantidadApostada;
        this.cantidadGanada = cantidadGanada;
    }

    public int[] getNumerosApostados() {
        return numerosApostados;
    }

    public int getResultado() {
        return resultado;
    }

    public double getCantidadApostada() {
        return cantidadApostada;
    }

    public double getCantidadGanada() {
        return cantidadGanada;
    }
    
    public String StringNumerosApostados(){
        String numeros = "";
        for(int i=0;i< this.numerosApostados.length; i++){
            numeros = numeros + Integer.toString(this.numerosApostados[i]);
            if(this.numerosApostados.length-1 != i){
                numeros = numeros + ",";
            }
        }
        
        return numeros;
    }

    @Override
    public void guardar(String usuario) {
        OperacionesSQL.instancia().addJugada_Ruleta(this, usuario);
    }
    
}
