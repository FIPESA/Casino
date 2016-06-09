
package Usuarios;


import Exceptions.TransaccionIncorrecta;
import java.util.Date;

public class F2P extends User {

    public F2P(String Username) {
        super(Username);
        
    }
    
    /**
     * Necesita base de datos
     * @return 
     */
    @Override
    public boolean VerStats() {
        return true;
    }
    
    /**
     * Sin uso
     */
    @Override
    public void guardar() {}

    @Override
    public void retirarFondos(double cantidad) throws TransaccionIncorrecta {        
    }

    @Override
    public void añadirFondos(double cantidad) throws TransaccionIncorrecta {        
    }
    
    
    
    

    
}
