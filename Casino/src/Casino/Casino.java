/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casino;
import Juegos.Ruleta.Jugar_Ruleta;
import Juegos.Slots.Jugar_Slots;
import java.util.*;
import Exceptions.*;
import Juegos.*;
import Juegos.BlackJackGraf.Jugar_BlackJack;
import Usuarios.*;


/**
 *
 * @author alumno
 */
public class Casino {
    private User usuario;
    private ArrayList<Jugada> jugadas;
    
    private Jugar_Ruleta ruleta;
    private Jugar_Slots slots;
    private Jugar_BlackJack blackjack;
    

    public Casino(){
        usuario = new F2P("Guest");
        jugadas = new ArrayList<Jugada>();
        try {
            usuario.añadirFondos(999999);
        } catch (TransaccionIncorrecta ex) {           
        }
    }
    
    /*public double jugarRuleta(double dinero, int[] apuesta) throws ImposibleJugar{
        ruleta = new Jugar_Ruleta(usuario, dinero, apuesta);
        return ruleta.jugar();
    }

    public Jugar_Ruleta getRuleta() {
        return ruleta;
    }
    
    public double JugarSlots() throws ImposibleJugar{
        slots = new Jugar_Slots(usuario);
        return slots.jugar();
    }

    public Jugar_Slots getSlots() {
        return slots;
    }

    public Jugar_BlackJack getBlackjack() {
        return blackjack;
    }*/
    
    
    
    
    
    /*
    Futura concurrencia
    public void addUser(User usuario){
        usuarios.add(usuario);
        usuarios.sort(null);
        
    }
    
    public int FindUser (String username){
        int inicio = 0,fin=usuarios.size(),medio,posicion = -1;
        while (inicio<=fin){
            medio = (inicio+fin)/2;
            if(usuarios.get(medio).getUsername().compareToIgnoreCase(username)<0)
                inicio = medio + 1;
            else if(usuarios.get(medio).getUsername().compareToIgnoreCase(username)>0)
                fin = medio - 1;
            else{
                posicion = medio;
                inicio = fin + 1;
            }         
        }         
        
        return posicion;
    }
    
    public void DelUser(String username){
        usuarios.remove(FindUser(username));
    }
    
    public void DelUser(int posicion){
        usuarios.remove(posicion);
    }*/
    
    public void addJugada (Jugada j){
        jugadas.add(j);
    }
    
    public void DelJugada(int posicion){
        jugadas.remove(posicion);
    }

    /**
     * Castear el retorno para acceder a sus metodos
     * @return 
     */
    public User getUsuario() {
        return usuario;
    }
    
    public void setUsuario(P2W usuario) {
        this.usuario = usuario;
    }
    
    public void setUsuario(P2WSS usuario) {
        this.usuario = usuario;
    }
    
    public void setUsuario(Admin usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Establece el usuario activo del casino como un nuevo F2P
     */
    public void logout (){
        usuario = null;
        usuario = new F2P("Guest");
    }

    public Jugar_Ruleta getRuleta() {
        return ruleta;
    }

    public Jugar_Slots getSlots() {
        return new Jugar_Slots(usuario);
    }

    public Jugar_BlackJack getBlackjack() {
        return blackjack;
    }
    

    
    
    
    
    


    
    
}
