

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
     * actualiza el archivo de guardado del usuario o lo crea si este no existe
     */
    @Override
    public void guardar() {
    }
    
    /**
     * añade fondos al monedero
     * @param cantidad 
     * @throws Exceptions.TransaccionIncorrecta 
     */
    @Override
    public void añadirFondos(double cantidad) throws TransaccionIncorrecta{
        getMonedero().añadirFondos(cantidad);
        
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
