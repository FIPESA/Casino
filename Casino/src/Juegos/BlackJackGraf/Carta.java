/*
 * 
 * 
 * 
 */

package Juegos.BlackJackGraf;

import static com.sun.webkit.graphics.WCImage.getImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;


 
//@author Mazhe <your.name at your.org>
// 
public class Carta extends JButton {
    private char simbolo;//2,3,4,5,6,7,8,9,10,J,Q,K,A
    private char tipo;//P C T R (Picas, Corazones, Treboles, Rombos)
    private int indice;   
    ImageIcon imagen;
    private int unico;
    
    
    public Carta (char sim, char tipo, int i){
        this.simbolo = sim;
        this.tipo = tipo;
        this.indice = i;
        URL img =  getClass().getResource("/resources/blackjack/"+indice+".png");
        imagen = new ImageIcon(img);
         if (tipo == 'P' || tipo == 'T'){
            this.setBackground(Color.BLACK);
            this.setForeground(Color.WHITE);
         } else {
             this.setBackground(Color.RED);
         }
            
        //this.setContentAreaFilled(false);
        
        /*this.setVisible(true);
        this.setFocusable(false);
        //this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusable(false);
        this.setRequestFocusEnabled(false);*/
        //imagen = new ImageIcon(icon);
        
        
    }
    /**
     * Crea una coleccion de objetos carta con sus imagenes.
     * @return una coleccion de cartas.
     */
    public static ArrayList<Carta> crearBaraja () {
        String simbolos = "A23456789DJQK";
        String tipo = "PCTR";
        int indice = 1;
        ArrayList<Carta> baraja = new ArrayList<> ();
        for (int i=0; i < tipo.length(); i++) {
            for (int j=0; j < simbolos.length(); j++){
                baraja.add(new Carta (simbolos.charAt(j), tipo.charAt(i), indice));
                indice ++;
            }
        }
        return baraja;
    }
    /**
     * Devuelve la carta con su simbolo y su palo.
     * @return 
     */
    public String toString (){
        String retorno;
        if (this.simbolo == 'D'){
            retorno = "10" + "" + this.tipo;
        } else {
            retorno = this.simbolo + "" + this.tipo;
        }
        return retorno;
    }
    /**
     * Extrae el valor numerico de la carta.
     * @return 
     */
    public int getValor(){
        int valor;
        if (this.simbolo == 'J'){
            valor = 11;
        } else if (this.simbolo == 'Q'){
            valor = 12;
        } else if (this.simbolo == 'K'){
            valor = 13;
        } else if (this.simbolo == 'D'){
            valor = 10;
        } else if (this.simbolo == 'A'){
            valor = 1;
        }else {
            valor = Integer.parseInt(this.simbolo+"");
        }
        return valor;
    }

    /**Devuelve el numero
     * @return the simbolo
     */
    public char getSimbolo() {
        return simbolo;
    }

    /**
     * @param simbolo the simbolo to set
     */
    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    /**Devuelve el palo
     * @return the tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    /**Devuelve un entero que representa a una unica carta.
     * @return the indice
     */
    public int getIndice() {
        return indice;
    }

    /**
     * @param indice the indice to set
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    /**Devuelve la imagen de la carta.
     * @return the imagen
     */
    public ImageIcon getImagen() {
        return imagen;
    }

    /**
     * @return the unico
     */
    public int getUnico() {
        return unico;
    }

    /**
     * @param unico the unico to set
     */
    public void setUnico(int unico) {
        this.unico = unico;
    }
     
    
}
