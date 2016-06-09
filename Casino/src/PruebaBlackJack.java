/*
 * 
 * 
 * 
 */


import Exceptions.ErrorException;
import Juegos.BlackJackGraf.JugarBlackJack;
import Usuarios.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * por: Mazhe
 */
public class PruebaBlackJack {
    /*

    public static void main(String[] args) throws ErrorException {

        JugarBlackJack blacky;
        User user;
        Scanner entrada = new Scanner (System.in);
        String sn;
        Boolean bandera = true;

        user = new P2W("caca", "caca", "caca", "caca", "caca", "caca", 15);
        try {
            user.añadirFondos(5000);
        } catch (NegativeNumberException | ZeroException ex) {
            Logger.getLogger(PruebaBlackJack.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Empieza el juego.\n=============");
        while (bandera) {
            blacky = new JugarBlackJack(user);
            System.out.println("Tienes: " + user.getMonedero().getFondos());
            System.out.println("Quieres jugar otra vez?\nSI/NO.");
            sn = entrada.next();

            if (sn.equalsIgnoreCase("s") || sn.equalsIgnoreCase("si")) {
                bandera = true;
                System.out.println("************************************************");
                System.out.println("************************************************");
            } else if (sn.equalsIgnoreCase("n") || sn.equalsIgnoreCase("no")) {
                bandera = false;
                System.out.println("**************FIN*******************************");
            } else {
                throw new ErrorException("No has introducido la opción correcta.");
            }
        }

    }*/

}
