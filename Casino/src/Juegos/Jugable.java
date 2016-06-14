/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos;
import Casino.*;
import Exceptions.*;

/**
 *
 * @author alumno
 */
public interface Jugable {
    public Jugada jugar() throws ImposibleJugar, RentaException;
}
