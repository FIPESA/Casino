/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casino;

import java.util.*;

/**
 *
 * @author alumno
 */
public abstract class Jugada {
    
    private Date fecha;

    public Jugada() {
        this.fecha = new Date();
    }
    
    public Jugada(Date fecha){
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }    
    
    public abstract void guardar(String usuario);
}


    