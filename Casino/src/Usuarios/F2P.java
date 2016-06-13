
package Usuarios;


import Exceptions.TransaccionIncorrecta;

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
    public void retirarFondos(double cantidad) throws TransaccionIncorrecta {        
    }

    @Override
    public void añadirFondos(double cantidad) throws TransaccionIncorrecta {        
    }
    
    
    
    

    
}
