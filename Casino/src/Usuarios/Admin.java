

package Usuarios;

import java.util.Date;
import Exceptions.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Admin extends User{
    
    private String Pass;

    public Admin(String Pass, String Username) {
        super(Username);
        this.Pass = Pass;
        
    }
    
        
    /**
     * Establece el estado del usuario a inactivo 
     * @param usuario
     * @param BanDays
     * @throws BanException 
     */
    public void BanHammer(User usuario, int BanDays) throws BanException{
        //acabar con fecha .
        if (usuario instanceof P2W){
            P2W user = (P2W) usuario;
            user.setAceptado(false);
        } else if (usuario instanceof P2WSS){
            P2WSS user = (P2WSS) usuario;
            user.setAceptado(false);
        } else {
            throw new BanException("Fallo en el baneo");
        }
        
    }
    /**
     * 
     * @param usuario
     * @throws UserException 
     */
    public void editarDatos (User usuario,String username,String pass,String Lname1,String Lname2,String name,String email,Date reg_date,double renta_num,boolean aceptado,Date ban_date,Date ped) throws UserException{
        
        if(usuario instanceof P2W){            
            
            P2W a = (P2W)usuario;
            editarUsername(a, username);
            editarPass(a, pass);
            editarName(a, name);
            editarLName1(a, Lname1);
            editarLName2(a, Lname2);
            editarEmail(a, email);
            editarReg_Date(a, reg_date);
            editarRenta_num(a, renta_num);
            editarAceptado(a, aceptado);
            editarBan_Date(a, reg_date);
            editarBan_Date(a, ban_date);
           
        }else if(usuario instanceof P2WSS){
            
            P2WSS b= (P2WSS)usuario;
            editarUsername(b, username);
            editarPass(b, pass);
            editarName(b, name);
            editarLName1(b, Lname1);
            editarLName2(b, Lname2);
            editarEmail(b, email);
            editarReg_Date(b, reg_date);
            editarRenta_num(b, renta_num);
            editarAceptado(b, aceptado);
            editarBan_Date(b, reg_date);
            editarBan_Date(b, ban_date);
            editarPremium_ending_date(b, ped);         
           
        } else{
            throw new UserException("Usuario erroneo");
        }
        
    } 
    
    public User consultarUsuarios(String filtro){
        
        
        
        
        
        return null;
    }
    
    
    public String verStatsAvanzadas(String filtro){
        
        
        
        
        return null;
    
    }
    
    
    public void editarUsername(User usuario,String username) throws UserException{
       
        if(usuario instanceof P2W){
            P2W a= (P2W)usuario;
            a.setUsername(username);
        }else if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setUsername(username);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
    
    }
    
    /**
     * 
     * @param usuario
     * @param Pass
     * @throws UserException 
     */
    public void editarPass(User usuario,String Pass) throws UserException{
       
        if(usuario instanceof P2W){
            P2W a= (P2W)usuario;
            a.setPass(Pass);
        }else if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setPass(Pass);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
    
    }
    /**
     * 
     * @param usuario
     * @param Name
     * @throws UserException 
     */
    public void editarName(User usuario,String Name) throws UserException{
       
        if(usuario instanceof P2W){
            P2W a= (P2W)usuario;
            a.setName(Name);
        }else if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setName(Name);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
    
    }
    /**
     * 
     * @param usuario
     * @param LName1
     * @throws UserException 
     */
    public void editarLName1(User usuario,String LName1) throws UserException{
    
        if(usuario instanceof P2W){
            P2W a= (P2W)usuario;
            a.setLName1(LName1);
        }else if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setLName1(LName1);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
        
        
    }
    /**
     * 
     * @param usuario
     * @param LName2
     * @throws UserException 
     */
    public void editarLName2(User usuario,String LName2) throws UserException{
        
        if(usuario instanceof P2W){
            P2W a= (P2W)usuario;
            a.setLName2(LName2);
        }else if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setLName2(LName2);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
    
    }
    /**
     * 
     * @param usuario
     * @param email
     * @throws UserException 
     */
    public void editarEmail(User usuario,String email) throws UserException{
    
        if(usuario instanceof P2W){
            P2W a= (P2W)usuario;
            a.setEmail(email);
        }else if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setEmail(email);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
    
    }
    /**
     * 
     * @param usuario
     * @param Reg_Date
     * @throws UserException 
     */
    public void editarReg_Date(User usuario,Date Reg_Date) throws UserException{
    
        if(usuario instanceof P2W){
            P2W a= (P2W)usuario;
            a.setReg_Date(Reg_Date);
        }else if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setReg_Date(Reg_Date);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
    
    }
    /**
     * 
     * @param usuario
     * @param renta_num
     * @throws UserException 
     */
    public void editarRenta_num(User usuario,Double renta_num) throws UserException{
    
        if(usuario instanceof P2W){
            P2W a= (P2W)usuario;
            a.setRenta_num(renta_num);
        }else if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setRenta_num(renta_num);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
    
    }
    /**
     * 
     * @param usuario
     * @param aceptado
     * @throws UserException 
     */
    public void editarAceptado(User usuario,boolean aceptado) throws UserException{
    
        if(usuario instanceof P2W){
            P2W a= (P2W)usuario;
            a.setAceptado(aceptado);
        }else if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setAceptado(aceptado);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
    
    }
    /**
     * 
     * @param usuario
     * @param Ban_Date
     * @throws UserException 
     */
    public void editarBan_Date(User usuario,Date Ban_Date) throws UserException{
    
        if(usuario instanceof P2W){
            P2W a= (P2W)usuario;
            a.setBan_Date(Ban_Date);
        }else if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setBan_Date(Ban_Date);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
        
    }
    /**
     * 
     * @param usuario
     * @param Premium_ending_date
     * @throws UserException 
     */
    public void editarPremium_ending_date(User usuario,Date Premium_ending_date) throws UserException{
    
        
        if(usuario instanceof P2WSS){
            P2WSS b= (P2WSS)usuario;
            b.setPremium_ending_date(Premium_ending_date);
        }else{
            throw new UserException("No has introducido un usuario valido");
        }
    
    }
        
    
    /**
     * actualiza el archivo de guardado del usuario o lo crea si este no existe
     */
    @Override
    public void guardar() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getUsername()+".us"));
            oos.writeInt(3);
            oos.writeUTF(Pass);
            oos.writeObject(getMonedero());
            oos.close();            
        } catch (FileNotFoundException ex) {} catch (IOException ex) {}
    }
    
    /**
     * añade fondos al monedero
     * @param cantidad 
     * @throws Exceptions.TransaccionIncorrecta 
     */
    @Override
    public void añadirFondos(double cantidad) throws TransaccionIncorrecta{
        getMonedero().añadirFondos(cantidad);
        this.guardar();
    }

    @Override
    public void retirarFondos(double cantidad) throws TransaccionIncorrecta {        
    }

    public String getPass() {
        return Pass;
        
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    
        
    
    
    
}
