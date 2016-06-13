

package Usuarios;

import ConexionBD.OperacionesSQL;
import Exceptions.*;
import java.util.*;
import java.io.Serializable;


public class Monedero implements Serializable{
    
    private double fondos;
    
   
    public Monedero(){
        fondos=0;
        
    }

    public Monedero(double fondos) {
        this.fondos = fondos;
        
    }
    
    public double getFondos() {
        return fondos;
    }

    public void setFondos(double fondos) {
        this.fondos = fondos;
    }

    
    /**
     *
     * Metodo que recibe una cantidad de dinero y la suma al monedero
     * @param cantidad
     * @throws Exceptions.TransaccionIncorrecta
     */
    public void añadirFondos(double cantidad) throws TransaccionIncorrecta {
        if (cantidad >0){
            fondos=fondos+cantidad;            
        } else {
            throw new TransaccionIncorrecta("Cantidad Incorrecta");
        }
       
    }
   
    /**
     * Metodo que retira una cantidad de dinero del monedero.esta cantidad la recibe el metodo por parametro
     * @param cantidad 
     * @throws Exceptions.TransaccionIncorrecta 
     */
    public void retirarFondos(double cantidad) throws TransaccionIncorrecta{    
        if (cantidad < fondos){
            fondos=fondos-cantidad;
        } else {
            throw new TransaccionIncorrecta("Cantidad superior a los fondos");
        }
    }
    
}
