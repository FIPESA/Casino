/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juegos.BlackJackGraf;

import java.util.*;




public class BlackJack{

    /**
     * INSTANCIACION DE CONSTANTES
     */
    final int PALO = 13;//Controla el tipo de baraja, las cartas por palo.
    final int CARTAS = 52;//Controla el numero de cartas de la baraja.
    final int N_BARAJAS = 7;//Controla el número de barajas que van en la pila.
    final int N_PALO = 4;//Controla el numero de palos de una baraja.
    final int CARTAS_MANO_P = 2;//Controla las cartas de mano del jugador.
    final int CARTAS_MANO_C = 1;//Controla las cartas de mano del crupier.
    /**
     * INSTANCIACION DE VARIABLES
     */
    ArrayList<Carta> baraja = new ArrayList<>();
    ArrayList<Carta> pila = new ArrayList<>();
    private ArrayList<Carta> manoC = new ArrayList<>();
    private ArrayList<Carta> manoP = new ArrayList<>();

    /**
     * Constructor que crea una baraja con las constantes indicadas y crea una
     * pila con con las constantes de la clase
     */
    public BlackJack() {

        /*baraja = new ArrayList<>();
        pila = new ArrayList<>();
        manoC = new ArrayList<>();
        manoP = new ArrayList<>();*/
        this.crearBaraja();
        this.crearPila();
        this.mezclarCartas();
    }

    /**
     * Crea una baraja con los datos indicados en las constantes.
     */
    public void crearBaraja() {
        baraja = Carta.crearBaraja();
    }

    /**
     * Crea una pila de N_BARAJAS.
     */
    public void crearPila() {
        int i = 0;
        while (i < N_BARAJAS) {
            pila.addAll(Carta.crearBaraja());
            i++;
        }
    }

    /**
     * Mezcla la pila de cartas aleatoriamente
     */
    public void mezclarCartas() {
        Collections.shuffle(pila);

    }

    /**
     * Da una carta de la pila a la mano del jugador. La carta es eliminada de
     * la pila.
     */
    public Carta pedirCartaPlayer() {
        Carta carta = pila.remove(0);
        getManoP().add(carta);
        return carta;
    }

    /**
     * Da una carta de la pila a la mano del crupier. La carta es eliminada de
     * la pila.
     */
    public Carta pedirCartaCrupier() {
        Carta carta = pila.remove(0);
        getManoC().add(carta);
        return carta;
    }

    /**
     * Reparte las cartas a el jugador.
     */
    public void repartirCartasP() {
        int i = 0;

        while (i < CARTAS_MANO_P) {
            this.pedirCartaPlayer();
            i++;
        }

    }

    /**
     * Reparte las cartas a el crupier.
     */
    public void repartirCartasC() {
        for (int i = 0; i < CARTAS_MANO_C; i++) {
            this.pedirCartaCrupier();

        }
    }

    /**
     * Comprueba si se tienen 21 o menos.
     *
     * @param mano
     * @return retorna 1 si se tienen 21 y retorna 0 si te has pasado de 21. retorna 2 si se tienen menos de 21.
     */
    public int comprobar21(ArrayList<Carta> mano) {
        int bJ = 2;
        if (this.contarCartas(mano) == 21) {
            bJ = 1;
        } else if (this.contarCartas(mano) > 21) {
            bJ = 0;
        }
        return bJ;
    }

    /**
     * Con este método cuento el valor de las cartas de la mano. Se le pasa como
     * parametro una mano de cartas y va recorriendolas dando el valor adecuado
     * y sumandolas. Si se encuentra un AS (1) le dara el valor de 11 siempre y
     * cuando la cuenta de la mano hasta ahora sea menor o igual que 10, si es
     * mayor, el valor de el AS (1) será de 1.
     *
     * @param mano
     * @return
     */
    public int contarCartas(ArrayList<Carta> mano) {
        Iterator<Carta> it = mano.iterator();
        int valor = 0;
        boolean as = false;
        while (it.hasNext()) {
            Carta i = it.next();
            if (i.getValor() >= 10) {
                valor += 10;
            } else if (i.getValor() == 1) {
                if (valor <= 10) {
                    valor += 11;
                    as = true;
                } else {
                    valor += 1;
                }
            } else {
                valor += i.getValor();
            }
        }
        if (as && valor - 11 > 10) {
            valor = valor - 10;
        }

        return valor;
    }

    /**
     * Este metodo incluye la lógica de un blackJack basico. De momento creo que
     * con esto vamos bien.
     *
     * @return Un entero (100) si gana el jugador y (0) si gana la banca o (150)
     * si el jugador tiene 21 de mano.
     * @throws ErrorException
     */
    /**
     * *******************************************************
     ****************** PRUEBAS ***************************
     * ********************************************************
     */
    /**
     * Muestra Para probar la creacion de la baraja y de la pila.
     *
     * @param mano
     * @return Una String que contiene todas las cartas. De la baraja y de la
     * pila.
     */
    public String mostrarCartasP(ArrayList<Carta> mano) {
        String retorno = "";
        Iterator <Carta> it = mano.iterator();
        while (it.hasNext()) {
            
            retorno += it.next().toString() + ", ";
        }
        //retorno += "-----------------------\n La pila tiene: \n";
        //it = pila.iterator();
        //while (it.hasNext()) {
        //    retorno += it.next() + ", ";
        //}
        return retorno;
    }

    public String mostrarCartasC(ArrayList<Carta> mano) {
        String retorno = "";
        Iterator it = mano.iterator();
        while (it.hasNext()) {
            retorno += it.next().toString() + ", X";
        }
        //retorno += "-----------------------\n La pila tiene: \n";
        //it = pila.iterator();
        //while (it.hasNext()) {
        //    retorno += it.next() + ", ";
        //}
        return retorno;
    }

    /**
     * @return the manoC
     */
    public ArrayList<Carta> getManoC() {
        return manoC;
    }

    /**
     * @return the manoP
     */
    public ArrayList<Carta> getManoP() {
        return manoP;
    }

}
