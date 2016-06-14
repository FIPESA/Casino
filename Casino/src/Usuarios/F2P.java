
package Usuarios;


import Exceptions.*;


public class F2P extends User {

    public F2P(String Username) {
        super(Username);
        
    }

    /**
     * Sin uso
     */
    @Override
    public void guardar() {}

    @Override
    public void retirarFondos(double cantidad) throws TransaccionIncorrecta, RentaException {        
    }

    @Override
    public void añadirFondos(double cantidad) throws TransaccionIncorrecta {        
    }
    
    
    
    

    
}
