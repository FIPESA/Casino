
package Usuarios;


import java.util.Date;
import Exceptions.*;



public class P2WSS extends P2W{
    
    private Date Premium_ending_date;

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
     * @param Premium_ending_date
     * @param aceptado 
     */
    public P2WSS(String Username, String Pass, Monedero monedero, String Name, String LName1, String LName2, String Email, Date Reg_Date, double renta_num, Date Ban_Date,Date Premium_ending_date,boolean aceptado) {
        super(Username, Pass, monedero, Name, LName1, LName2, Email, Reg_Date, renta_num, Ban_Date, aceptado);
        this.Premium_ending_date = Premium_ending_date;
    }

    public P2WSS(String Username) {
        super(Username);
    }
    
    /**
     * Fija la fecha de fin de premium
     * @param days
     * @throws PremiumUpdateException 
     */
    public void UpdatePremium(int days) throws PremiumUpdateException{
        Date fecha = new Date();
        final long MILIS = 86400000; 
        
        if(this.Premium_ending_date==null || this.Premium_ending_date.compareTo(fecha)<0){
            this.Premium_ending_date = fecha;
            this.Premium_ending_date.setTime(this.Premium_ending_date.getTime()+MILIS*days);
        }else if(this.Premium_ending_date.compareTo(fecha)>0||this.Premium_ending_date.compareTo(fecha)==0){
            this.Premium_ending_date.setTime(this.Premium_ending_date.getTime()+MILIS*days);    
        }else {
            throw new PremiumUpdateException("Fallo al actualizar la subscripcion");
        }
    }
    
    public Date getPremium_ending_date() {
        return Premium_ending_date;
    }

    public void setPremium_ending_date(Date Premium_ending_date) {
        this.Premium_ending_date = Premium_ending_date;
    }
    
 
}
    
    

