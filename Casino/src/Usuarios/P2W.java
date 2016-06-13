package Usuarios;

import ConexionBD.OperacionesSQL;
import java.util.*;
import Exceptions.*;

public class P2W extends User{

    private String Pass;
    private String Name;
    private String LName1;
    private String LName2;
    private String Email;
    private Date Reg_Date;
    private double renta_num;
    private boolean aceptado = false;
    private Date Ban_Date;

    /**
     * Constructor para creacion de usuario nuevo
     * @param Username
     * @param Pass
     * @param Name
     * @param LName1
     * @param LName2
     * @param Email
     * @param renta_num 
     */
    public P2W(String Username, String Pass,String Name, String LName1, String LName2,String Email, double renta_num) {
        super(Username);
        this.Pass = Pass;
        this.Name = Name;
        this.LName1 = LName1;
        this.LName2 = LName2;
        this.Email = Email;
        this.Reg_Date = new Date();
        this.renta_num = renta_num;
        this.aceptado = true;
        this.Ban_Date = new Date(0);
    }

    public P2W(String Username) {
        super(Username);
    }

    /**
     * Constructor para carga de archivos
     * @param Username
     * @param Pass
     * @param monedero
     * @param Name
     * @param LName1
     * @param LName2
     * @param Email
     * @param Reg_Date
     * @param renta_num
     * @param Ban_Date
     * @param aceptado 
     */
    public P2W(String Username,String Pass,Monedero monedero, String Name, String LName1, String LName2, String Email, Date Reg_Date, double renta_num, Date Ban_Date,boolean aceptado) {
        super(Username, monedero);
        this.Pass = Pass;
        this.Name = Name;
        this.LName1 = LName1;
        this.LName2 = LName2;
        this.Email = Email;
        this.Reg_Date = Reg_Date;
        this.renta_num = renta_num;
        this.Ban_Date = Ban_Date;
        this.aceptado=aceptado;
    }
    
    /**
     * Devuelve un user mejorado con los dias que se pasan como parametro
     * @param days
     * @return
     * @throws PremiumUpdateException 
     * @throws Exceptions.TransaccionIncorrecta 
     */
    public P2WSS UpgradeSubscription(int days) throws PremiumUpdateException, TransaccionIncorrecta{
        P2WSS user = new P2WSS(getUsername(), getPass(), getMonedero(), getName(), getLName1(), getLName2(), getEmail(), getReg_Date(), getRenta_num(), getBan_Date(), null, isAceptado());
        user.retirarFondos(days);
        user.UpdatePremium(days);
        return user;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public Date getBan_Date() {
        return Ban_Date;
    }

    public void setBan_Date(Date Ban_Date) {
        this.Ban_Date = Ban_Date;
    }

    public String getPass() {
        return Pass;
    }

    public String getName() {
        return Name;
    }

    public String getLName1() {
        return LName1;
    }

    public String getLName2() {
        return LName2;
    }

    public String getEmail() {
        return Email;
    }

    public Date getReg_Date() {
        return Reg_Date;
    }

    public double getRenta_num() {
        return renta_num;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setLName1(String LName1) {
        this.LName1 = LName1;
    }

    public void setLName2(String LName2) {
        this.LName2 = LName2;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setReg_Date(Date Reg_Date) {
        this.Reg_Date = Reg_Date;
    }

    public void setRenta_num(double renta_num) {
        this.renta_num = renta_num;
    }
    
    
    /**
     * Actualiza el usuario en la bd
     */
    @Override
    public void guardar() {
        OperacionesSQL.instancia().actualizarUsuario(this);
    }
    
    
    /**
     * añade fondos al monedero actualizando la bd
     * @param cantidad 
     * @throws Exceptions.TransaccionIncorrecta 
     */
    @Override
    public void añadirFondos(double cantidad) throws TransaccionIncorrecta{
        getMonedero().añadirFondos(cantidad);
        OperacionesSQL.instancia().actualizarFondos(this);
    }

    /**
     * Retira fondos del monedero actulizando la bd
     * @param cantidad
     * @throws TransaccionIncorrecta 
     */
    @Override
    public void retirarFondos(double cantidad) throws TransaccionIncorrecta {
        this.getMonedero().retirarFondos(cantidad);
        OperacionesSQL.instancia().actualizarFondos(this);
    }
    
    /**
     * Añade fondos al monedero creando una transaccion en la bd
     * @param cantidad
     * @throws TransaccionIncorrecta 
     */
    public void añadirFondosTransaccion (double cantidad) throws TransaccionIncorrecta{
        this.añadirFondos(cantidad);
        Transaccion trans = new Transaccion (cantidad);
        OperacionesSQL.instancia().AddTransaccion(trans, this);
    }
    
    /**
     * Retira fondos del monedero y crea una transaccion en la bd
     * @param cantidad
     * @throws TransaccionIncorrecta 
     */
    public void retirarFondosTransaccion (double cantidad) throws TransaccionIncorrecta{
        this.retirarFondos(cantidad);
        Transaccion trans = new Transaccion (-cantidad);
        OperacionesSQL.instancia().AddTransaccion(trans, this);
    }
    
    
    
}

    
