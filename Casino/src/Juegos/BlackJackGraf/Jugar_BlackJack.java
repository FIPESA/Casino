/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos.BlackJackGraf;

import Casino.Jugada;
import Casino.Jugada_BlackJack;
import Exceptions.*;
import Usuarios.*;
import java.util.Scanner;
import Grafica.BlackJackGrafica;
import Juegos.Jugable;

/**
 *
 * @author alumno
 */
public class Jugar_BlackJack implements Jugable {
    BlackJackGrafica grafica;
    private BlackJack bj;
    User user;
    private double apu;
    double ganancias;
    

    public Jugar_BlackJack(User user) {
        
        try {
            bj = new BlackJack();
            this.user = user;
            this.hacerJuego();
        } catch (ErrorException ex) {
            
        }
        
    }
    public boolean pedirCartasC (){
        boolean banderaC = true;
        
            //SE COMPRUEBA QUE LA MANO DEL CRUPIER NO SE PASA DE 21
            if (getBj().comprobar21(getBj().getManoC()) == 0) {
                
                banderaC = false;
                //SI TIENE 17 O MENOS PIDE CARTA
            } else if (getBj().contarCartas(getBj().getManoC()) <= 17) {
                
                System.out.println("La mano a pedido una carta y ahora tiene:");
                System.out.println(getBj().mostrarCartasP(getBj().getManoC()));
                System.out.println("Y suman: " + getBj().contarCartas(getBj().getManoC()));
                System.out.println("**********************************************************");
            } else {
                banderaC = false;
            }
            return banderaC;
        
    }
    /**
     * Engloba la logica de como decide el crupier si pide carta o no y devuelve un int que representa
     * quien gana, si la banca o el jugador.
     * @return 100 si gana el jugador, 0 si gana la banca.
     */
    public int logicaCrupier (){
        int ganador = 100;
        
        
        //SE VUELVE A COMPROBAR SI LA MANO SE HA PASADO DE 21
        if (getBj().comprobar21(getBj().getManoC()) == 0) {
            ganador = 100;
        }
        //LOGICA PARA ASIGNAR EL GANADOR
        if (getBj().comprobar21(getBj().getManoC()) == 1 && getBj().comprobar21(getBj().getManoP()) == 0) {
            ganador = 0;
        } else if (getBj().contarCartas(getBj().getManoP()) <= 21 && getBj().contarCartas(getBj().getManoC()) < getBj().contarCartas(getBj().getManoP()) && getBj().getManoP().size() > 2) {
            ganador = 100;
        } else if (getBj().contarCartas(getBj().getManoC()) <= 21 && getBj().contarCartas(getBj().getManoP()) < getBj().contarCartas(getBj().getManoC())) {
            ganador = 0;
        }
        return ganador;
    }
    public double ganador(int ganador)throws TransaccionIncorrecta{
        //SI EL GANADOR ES EL USUARIO
        if (ganador != 0) {
            //SI SE PRODUCE UN EMPATE
            if (getBj().contarCartas(getBj().getManoP()) == getBj().contarCartas(getBj().getManoC())) {
                ganador = 0;
            }
            //SI GANA DE FORMA NORMAL
            System.out.println("Has GANADO!!!. \nCon esta mano: \n" + getBj().mostrarCartasP(getBj().getManoP()) + " y sumaban\n " + getBj().contarCartas(getBj().getManoP()) + "\nLa del crupier era esta: \n" + getBj().mostrarCartasP(getBj().getManoC())
                    + " \ny sumaban:\n" + getBj().contarCartas(getBj().getManoC()) + "\nHas ganado : " + apu * ganador / 100);
            System.out.println("**********************************************************");
            
                user.añadirFondos(apu);
                ganancias = apu * ganador / 100;
                if (ganador != 0 && apu > 0) {
                    
                    user.añadirFondos(apu * ganador / 100);
                    
                }
            
            //SI GANA LA BANCA
            //SI GANA LA BANCA
        } else {
            System.out.println("Gana la banca. Con esta mano: \n" + getBj().mostrarCartasP(getBj().getManoC()) + " que suma: \n " + getBj().contarCartas(getBj().getManoC()) + "\nLa tuya era esta: \n" + getBj().mostrarCartasP(getBj().getManoP()) + "Y suman:\n" + getBj().contarCartas(getBj().getManoP()) + "\nSorry :)");
            System.out.println("**********************************************************");
        }
        return ganancias;
    }

    @Override
    public Jugada jugar() throws ImposibleJugar {
        
        Jugada cosa = new Jugada_BlackJack (apu,ganancias,bj.contarCartas(bj.getManoP()),bj.contarCartas(bj.getManoC()));
        return cosa;
         
   }
    
    

    public void hacerJuego() throws ErrorException {
        Scanner entrada = new Scanner(System.in);
        String sn;
        int ganador = 100;
        boolean pedir = false;
        boolean banderaP = true;
        boolean banderaC = true;

        
    }        
   
    public void repartirPrimera (){
        getBj().repartirCartasP();
        getBj().repartirCartasC();
        
        
    }
        /*bj.repartirCartasP();
        bj.repartirCartasC();
        System.out.println("Tus cartas son estas:");
        System.out.println(bj.mostrarCartasP(bj.manoP));
        System.out.println("Y suman: " + bj.contarCartas(bj.manoP));
        System.out.println("La mano tiene:");
        System.out.println(bj.mostrarCartasC(bj.manoC));
        System.out.println("Y suman: " + bj.contarCartas(bj.manoC));
        //SE COMPRUEBA SI LA MANO TIENE BLACKJACK
        if (bj.comprobar21(bj.manoP) == 1) {//compruebo si la mano del jugador es 21.
            ganador = 150;
            banderaC = false;
        } else {//SI NO TIENE BLACKJACK SE LE DA LA OPCION DE PEDIR CARTAS
            while (banderaP && bj.contarCartas(bj.manoP) < 21) {
                System.out.println("Quieres pedir carta? SI/NO.");
                sn = entrada.next();
                if (sn.equalsIgnoreCase("s") || sn.equalsIgnoreCase("si")) {
                    pedir = true;
                    System.out.println("Vas a pedir carta");
                    System.out.println("***************************************");
                } else if (sn.equalsIgnoreCase("n") || sn.equalsIgnoreCase("no")) {
                    pedir = false;
                    System.out.println("No vas a pedir carta.");
                    System.out.println("******************************************");
                    banderaP = false;
                } else {
                    throw new ErrorException("No has introducido la opción correcta.");
                }
                //SI HA PEDIDO CARTAS SE LE DAN.
                if (pedir) {
                    bj.pedirCartaPlayer();

                    System.out.println("Tus cartas son estas:");
                    System.out.println(bj.mostrarCartasP(bj.manoP));
                    System.out.println("Y suman: " + bj.contarCartas(bj.manoP));
                    System.out.println("La mano tiene:");
                    System.out.println(bj.mostrarCartasC(bj.manoC));
                    System.out.println("Y suman: " + bj.contarCartas(bj.manoC));
                    System.out.println("**********************************************************");
                    //DESPUES DE PEDIR SE COMPRUEBA SI TIENE 21
                    if (bj.comprobar21(bj.manoP) == 1) {
                        ganador = 100;
                        banderaP = false;
                        banderaC = false;
                    }

                }
                //SI TODAVIA PUEDE CONTINUAR SE LE DA LA OPCION DE PLANTARSE O VOLVER A PEDIR CARTA
                if (banderaP && bj.contarCartas(bj.manoP) <= 21) {
                    System.out.println("Quieres [P]lantarte o [C]ontinuar?");
                    sn = entrada.next();
                    if (sn.equalsIgnoreCase("p") || sn.equalsIgnoreCase("plantarte") || sn.equalsIgnoreCase("plantarse")) {
                        banderaP = false;
                        System.out.println("Te has plantado");
                        System.out.println("*********************************************");
                    } else if (sn.equalsIgnoreCase("c") || sn.equalsIgnoreCase("continuar")) {
                        banderaP = true;
                        System.out.println("Vas a continuar");
                        System.out.println("**********************************************");
                    }
                    //SE VUELVEN A MOSTRAR LAS CARTAS Y SE COMPRUEBA SI TODAVIA SON MENORES O IGUALES QUE 21
                    System.out.println("Tus cartas son estas:");
                    System.out.println(bj.mostrarCartasP(bj.manoP));
                    System.out.println("Y suman: " + bj.contarCartas(bj.manoP));
                    System.out.println("La mano tiene:");
                    System.out.println(bj.mostrarCartasC(bj.manoC));
                    System.out.println("Y suman: " + bj.contarCartas(bj.manoC));
                    System.out.println("**********************************************************");
                } else if (bj.contarCartas(bj.manoP) > 21) {
                    System.out.println("Te has pasado.");
                }
            }
            //SI SE PASA DE 21 HA PERDIDO.
            if (bj.contarCartas(bj.manoP) > 21) {
                ganador = 0;
                banderaC = false;
            }
        }//EMPIEZA LA PARTE DEL CRUPIER.
        while (banderaC) {
            //SE COMPRUEBA QUE LA MANO DEL CRUPIER NO SE PASA DE 21
            if (bj.comprobar21(bj.manoC) == 0) {
                ganador = 100;
                banderaC = false;
                //SI TIENE 17 O MENOS PIDE CARTA
            } else if (bj.contarCartas(bj.manoC) <= 17) {
                bj.pedirCartaCrupier();
                System.out.println("La mano a pedido una carta y ahora tiene:");
                System.out.println(bj.mostrarCartasP(bj.manoC));
                System.out.println("Y suman: " + bj.contarCartas(bj.manoC));
                System.out.println("**********************************************************");
            } else {
                banderaC = false;
            }
        }
        //SE VUELVE A COMPROBAR SI LA MANO SE HA PASADO DE 21
        if (bj.comprobar21(bj.manoC) == 0) {
            ganador = 100;
        }
        //LOGICA PARA ASIGNAR EL GANADOR
        if (bj.comprobar21(bj.manoC) == 1 && bj.comprobar21(bj.manoP) == 0) {
            ganador = 0;
        } else if (bj.contarCartas(bj.manoP) <= 21 && bj.contarCartas(bj.manoC) < bj.contarCartas(bj.manoP) && bj.manoP.size() > 2) {
            ganador = 100;
        } else if (bj.contarCartas(bj.manoC) <= 21 && bj.contarCartas(bj.manoP) < bj.contarCartas(bj.manoC)) {
            ganador = 0;
        }
        //SI EL GANADOR ES EL USUARIO
        if (ganador != 0) {
            //SI SE PRODUCE UN EMPATE
            if (bj.contarCartas(bj.manoP) == bj.contarCartas(bj.manoC)) {
                ganador = 0;
            }
            //SI GANA DE FORMA NORMAL
            System.out.println("Has GANADO!!!. \nCon esta mano: \n" + bj.mostrarCartasP(bj.manoP) + " y sumaban\n " + bj.contarCartas(bj.manoP) + "\nLa del crupier era esta: \n" + bj.mostrarCartasP(bj.manoC)
                    + " \ny sumaban:\n" + bj.contarCartas(bj.manoC) + "\nHas ganado : " + apu * ganador / 100);
            System.out.println("**********************************************************");
            try {
                user.añadirFondos(apu);
                if (ganador != 0) {
                    user.añadirFondos(apu * ganador / 100);
                }
            } catch (TransaccionIncorrecta ex) {
                Logger.getLogger(Jugar_BlackJack.class.getName()).log(Level.SEVERE, null, ex);
            }
            //SI GANA LA BANCA
        } else {
            System.out.println("Gana la banca. Con esta mano: \n" + bj.mostrarCartasP(bj.manoC) + " que suma: \n " + bj.contarCartas(bj.manoC) + "\nLa tuya era esta: \n" + bj.mostrarCartasP(bj.manoP) + "Y suman:\n" + bj.contarCartas(bj.manoP) + "\nSorry :)");
            System.out.println("**********************************************************");
        }
    }*/

    /**
     * @return the bj
     */
    public BlackJack getBj() {
        return bj;
    }

    /**
     * @param bj the bj to set
     */
    public void setBj(BlackJack bj) {
        this.bj = bj;
    }

    /**
     * @param apu the apu to set
     */
    public void setApu(double apu) {
        this.apu = apu;
    }

   
}
