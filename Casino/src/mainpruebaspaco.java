
import Exceptions.BDException;
import Exceptions.TransaccionIncorrecta;
import Exceptions.UserReadingException;
import Usuarios.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class mainpruebaspaco {
    
    public static void main(String[] args) {
        try {
            ConexionBD.ConexionBD.crearConexion();
        } catch (BDException ex) {
            Logger.getLogger(mainpruebaspaco.class.getName()).log(Level.SEVERE, null, ex);
        }
        P2W a ;
        try {
            a = (P2W) ConexionBD.OperacionesSQL.instancia().leerUsuario("pepe", "yo");
            System.out.println(a.getUsername()); 
        } catch (UserReadingException ex) {
            Logger.getLogger(mainpruebaspaco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
