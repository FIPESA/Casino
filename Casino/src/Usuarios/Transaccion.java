
package Usuarios;

import java.util.*;

public class Transaccion implements Comparable<Transaccion>,java.io.Serializable{
    
    private Date fecha;
    private double fondos;

    public Transaccion(double fondos) {
        this.fecha = new Date();
        this.fondos = fondos;
        
    }

    public Transaccion(Date fecha, double fondos) {
        this.fecha = fecha;
        this.fondos = fondos;
    }
    
    

    public Date getFechaActual() {
        return fecha;
    }

    public double getFondos() {
        return fondos;
    }

    public void setFechaActual(Date fechaActual) {
        this.fecha = fechaActual;
    }

    public void setFondos(double fondos) {
        this.fondos = fondos;
    }
    
    //Comparamos la fecha actual con la fecha que la pasamos por parametro
    @Override
    public int compareTo(Transaccion o) {
        return fecha.compareTo(o.fecha);
    }

     
    
}
