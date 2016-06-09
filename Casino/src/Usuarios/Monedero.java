

package Usuarios;

import ConexionBD.OperacionesSQL;
import Exceptions.*;
import java.util.*;
import java.io.Serializable;


public class Monedero implements Serializable{
    
    private double fondos;
    private LinkedList<Transaccion> transacciones;
   
    public Monedero(){
        fondos=0;
        transacciones = new LinkedList<>();
    }

    public Monedero(double fondos) {
        this.fondos = fondos;
        transacciones = new LinkedList<>();
    }
    
    

    
     /**
     * Metodo que muestra los fondos del monedero
     * @return la cantidad de dinero que tenemos en el monedero
     */
    public double getFondos() {
        return fondos;
    }

    public void setFondos(double fondos) {
        this.fondos = fondos;
    }

    
    //Metodo que te permite acceder a la LinkedList de transacciones
    public LinkedList<Transaccion> getTransacciones() {
        return transacciones;
    }
    
    
    //Metodo que recibe una cantidad de dinero y la añade a una nueva transaccion que a su
    //vez se añade a la LinkedList de transacciones,con el sort ordenamos dicha LinkedList
    public void añadirTransaccion(double cantidad){
        
        
        
    }
    
    public void añadirTransaccion(ArrayList<Transaccion> a){
        this.transacciones.addAll(a);
        this.transacciones.sort(null);
    }
    
    /**
     * Metodo que maneja una busqueda binaria para encontrar una transaccion en concreto y devolverla
     * por posicion
     * @param fecha
     * @return la posicion de la transaccion
     */
    public int verTransaccion(Date fecha){
        
        int inicio = 0;
        int fin=transacciones.size();
        int medio,posicion = -1;
        
        while (inicio<=fin){
            medio = (inicio+fin)/2;
            if(transacciones.get(medio).getFechaActual().before(fecha))
                inicio = medio + 1;
            else if(transacciones.get(medio).getFechaActual().after(fecha))
                fin = medio - 1;
            else{
                posicion = medio;
                inicio = fin + 1;
            }         
        }         
        
        return posicion;
    
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
            añadirTransaccion(-cantidad);
        } else {
            throw new TransaccionIncorrecta("Cantidad superior a los fondos");
        }
    }
    
}
