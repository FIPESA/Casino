/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import Exceptions.*;
import java.io.*;
import java.util.Date;
import ConexionBD.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class User implements Comparable<User>,Serializable{
    
    private Monedero Monedero;
    private String Username;
    private String codigo;
    

    public User(String Username) {
        this.Username = Username;
        this.Monedero = new Monedero();
    }
    
    public User(String Username, Monedero monedero){
        this.Username = Username;
        this.Monedero = monedero;
    }
        
    public abstract boolean VerStats();

    public Monedero getMonedero() {
        return Monedero;
    }

    public String getUsername() {
        return Username;
    }

    public void setMonedero(Monedero Monedero) {
        this.Monedero = Monedero;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    @Override
    public int compareTo(User o) {
       return this.Username.compareToIgnoreCase(o.Username);
    }
    
    public static User Logindb(String username,String password) throws BanException, UserReadingException{
        User usuario = null;
        OperacionesSQL instancia = OperacionesSQL.instancia();
        boolean aceptado = false;
        
        usuario = instancia.leerUsuario(username, password);
        
        if(usuario instanceof P2W){
            P2W p2w = (P2W)usuario;
            if(p2w.isAceptado()){
                aceptado = true;
            }
        }else if(usuario instanceof P2WSS){
            P2WSS p2w = (P2WSS)usuario;
            if(p2w.isAceptado()){
                aceptado = true;
            }
        }else if(usuario instanceof Admin){
            aceptado = true;
        }
        if(!aceptado){
            throw new BanException("Usuario Banneado");
        }
        return usuario;
    }
    
    public static P2W Registerdb(String Username,String Pass, String Name,String LName,String Lname2,String Email, double renta_Num) throws LoginException{
        P2W usuario = null;
        OperacionesSQL instancia = OperacionesSQL.instancia();
        boolean existe = false;
        
        try {
            instancia.leerUsuario(Username, Pass);
            existe = true;
        } catch (UserReadingException ex) {
            existe = false;
        }
        
        if(!existe){
            usuario = new P2W(Username, Pass, Name, LName, Lname2, Email, renta_Num);
            instancia.addUser(usuario);
        } else {
            throw new LoginException("el usuario ya existe");
        }
        
        return usuario;
    }
    
    /**
     * Lee archivo de texto 
     * @param username
     * @param password
     * @return
     * @throws LoginException
     * @throws UserException 
     */
    public static User Login(String username,String password) throws LoginException, UserException{
        User usuario = null;
        
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(username+".us"));
            int tipo = ois.readInt();
            if(tipo == 1){
                String Pass = ois.readUTF();
                if(password.equals(Pass)){
                    String name = ois.readUTF();
                    String LName1 = ois.readUTF();
                    String LName2 = ois.readUTF();
                    String Email = ois.readUTF();
                    Date Reg_Date = (Date)ois.readObject();
                    double renta_num = ois.readDouble();
                    boolean aceptado = ois.readBoolean();
                    Date Ban_Date = (Date)ois.readObject();
                    Monedero monedero = (Monedero)ois.readObject();
                    usuario = new P2W(username, Pass, monedero, name, LName1, LName2, Email, Reg_Date, renta_num, Ban_Date, aceptado);
                } else {
                    throw new LoginException("Fallo en el Login - Contraseña incorrecta");
                }
            }
            if(tipo == 2){
                String Pass = ois.readUTF();
                if(password.equals(Pass)){
                    String name = ois.readUTF();
                    String LName1 = ois.readUTF();
                    String LName2 = ois.readUTF();
                    String Email = ois.readUTF();
                    Date Reg_Date = (Date)ois.readObject();
                    double renta_num = ois.readDouble();
                    boolean aceptado = ois.readBoolean();
                    Date Ban_Date = (Date)ois.readObject();
                    Monedero monedero = (Monedero)ois.readObject();
                    Date Premium_ending_date = (Date)ois.readObject();
                    usuario = new P2WSS(username, Pass, monedero, name, LName1, LName2, Email, Reg_Date, renta_num, Ban_Date, Premium_ending_date, aceptado);
                }else {
                    throw new LoginException("Fallo en el Login - Contraseña incorrecta");
                }
            }
            if (tipo == 3){
                String pass = ois.readUTF();
                if(password.equals(pass)){
                    usuario = new Admin(pass, username);
                }else {
                    throw new LoginException("Fallo en el Login - Contraseña incorrecta");
                }
            }
            ois.close();
        }catch(IOException | ClassNotFoundException x){
            throw new UserException("Usuario no existe");
        }
        return usuario;
    }
    
    
    /**
     * 
     * @param Username
     * @param Pass
     * @param Name
     * @param LName
     * @param Lname2
     * @param Email
     * @param renta_Num
     * @return
     * @throws RegisterException 
     */
    public static P2W register(String Username,String Pass, String Name,String
            LName,String Lname2,String Email, double renta_Num) throws RegisterException{
        P2W user;
        File fichero = new File(Username+".us");
        if(!fichero.exists()){
            user = new P2W(Username, Pass, Name, LName, Lname2, Email, renta_Num);
            user.guardar();
        } else {
            throw new RegisterException("Error en el registro");
        }
        return user;
    }
    
    public abstract void guardar();
    
    
    public abstract void añadirFondos(double cantidad) throws TransaccionIncorrecta;
    
    public abstract void retirarFondos(double cantidad) throws TransaccionIncorrecta;
    
}
