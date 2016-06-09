
import Juegos.BlackJackGraf.JugarBlackJack;
import Casino.Casino;
import Exceptions.LoginException;
import Exceptions.*;
import Juegos.*;
import Usuarios.*;
import java.util.*;

public class MainFinal {
    
    
    public static void main(String[] args){
        Casino fipesa = new Casino();
        boolean salida = true;
        while(salida){          
            if (fipesa.getUsuario() instanceof F2P){
                salida = menuF2P(fipesa);
            } else if (fipesa.getUsuario() instanceof P2W){
                salida = menuP2W(fipesa);
            } else if (fipesa.getUsuario() instanceof P2WSS){
                salida = menuP2WSS(fipesa);
            }
        }
        System.out.println("Gracias por jugar vuelva pronto");  
    }

    
    
    /**
     * 
     * @param fipesa
     * @return 
     */
    public static boolean menuF2P (Casino fipesa){
        boolean salida = true;
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Que desea hacer?");
        System.out.println("1.Login");
        System.out.println("2.Registrarse");
        System.out.println("3.jugar");
        System.out.println("0.Salir");
        opcion=sc.nextInt();
        sc.nextLine();
        switch(opcion){
            case 1:
                System.out.println("Escriba su nombre de usuario");
                String user = sc.next();
                System.out.println("Escriba su contraseña");
                String pass = sc.next();
                try {
                    User lectura = User.Login(user, pass);                    
                    if (lectura instanceof P2W) {
                        fipesa.setUsuario((P2W)lectura);                        
                    } else if (lectura instanceof P2WSS){
                        fipesa.setUsuario((P2WSS)lectura);
                    } else if (lectura instanceof Admin){
                        fipesa.setUsuario((Admin)lectura);
                    }
                } catch (LoginException e) {
                    System.out.println("Password no valido");
                } catch (UserException e) {
                    System.out.println("Error en la lectura, contacte con un administrador");
                }
                salida = true;
                break;
            case 2:
                System.out.println("Introduzca sus datos porfavor");
                System.out.println("Introduzca su Username");
                String username = sc.next();
                System.out.println("Introduzca su pass");
                String password = sc.next();
                System.out.println("Introduzca su nombre");
                String name = sc.next();
                System.out.println("Introduzca su primer apellido");
                String lname1 = sc.next();
                System.out.println("Introduzca su segundo apellido");
                String lname2 = sc.next();
                System.out.println("Introduzca su email");
                String email = sc.next();
                System.out.println("Intruzca su renta");
                double renta = sc.nextDouble();
                fipesa.setUsuario(new P2W(username, password, name, lname1, lname2, email, renta));
                salida = true;
                break;
            case 3:
                menuJuegos(fipesa);
                break;
            case 0:
                salida = false;
                break;
        }
        return salida;
    }
    
    
    /**
     * 
     * @param fipesa 
     */
    public static void menuJuegos(Casino fipesa){
        boolean salir = true;
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        while(salir){
            System.out.println("¿Que Juego desea acceder?");
            System.out.println("1.Ruleta");
            System.out.println("2.Slots");
            System.out.println("3.Blackjack");
            System.out.println("0.salir");
            opcion = sc.nextInt();
            sc.nextLine();
            /*switch(opcion){
                case 1:
                    System.out.println("Introduce apuesta");
                    double cantidad = sc.nextDouble();
                    System.out.println("Introduce el numero");
                    int num = sc.nextInt();
                    int[] apuestas = {num};
            {
                try {
                    double ganancia = fipesa.jugarRuleta(cantidad, apuestas);
                    System.out.println("ha salido el numero: "+fipesa.getRuleta().resultado());
                    System.out.println("has ganado "+ ganancia);
                } catch (ImposibleJugar ex) {
                    System.out.println("Error al jugar");;
                }
            }

                    break;
                case 2:
                    try{
                        double ganancia = fipesa.JugarSlots();
                        System.out.println("ha salido: "+fipesa.getSlots().getSlots().getResultado1()+","+fipesa.getSlots().getSlots().getResultado2()+","+fipesa.getSlots().getSlots().getResultado3());
                        System.out.println("Has ganado: "+ganancia);
                    }catch(ImposibleJugar e){
                        System.out.println("No tienes fondos para jugar");
                    }                   
                    break;
                case 3:
                    JugarBlackJack a = new JugarBlackJack(fipesa.getUsuario());
                    break;
                case 0:
                    salir = false;
                    break;
            }*/
        }
    }
    
    
    
    /**
     * 
     * @param fipesa
     * @return 
     */
    public static boolean menuP2W(Casino fipesa){
        boolean salida = true;
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Que desea hacer?");
        System.out.println("1.Mejorar subscripcion");
        System.out.println("2.Jugar");
        System.out.println("3.Logout");
        System.out.println("4.Administrar monedero");
        System.out.println("0.Salir");
        opcion=sc.nextInt();
        sc.nextLine();
        switch(opcion){
            case 1:
                System.out.println("Introduzca el numero de dias que quiere tener de premium");
                int dias = sc.nextInt();
                P2W p2w = (P2W)fipesa.getUsuario();
                try{
                    fipesa.setUsuario(p2w.UpgradeSubscription(dias));
                }catch(PremiumUpdateException e){
                    System.out.println("Error de subcripcion");
                } catch (TransaccionIncorrecta ex) {
                    System.out.println("No hay suficiente dinero en el monedero");
                }
                salida = true;
                break;
            case 2:
                menuJuegos(fipesa);
                break;
            case 3:
                fipesa.logout();
                salida = true;
                break;
            case 4:
                menuMonedero(fipesa);
                break;
            case 0:
                salida = false;
                break;
        }
        return salida;
    }
    
    /**
     * 
     * @param fipesa
     * @return 
     */
    public static boolean menuP2WSS(Casino fipesa){
        boolean salida = true;
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Que desea hacer?");
        System.out.println("1.Aumentar subscripcion");
        System.out.println("2.Jugar");
        System.out.println("3.Logout");
        System.out.println("4.Administrar monedero");
        System.out.println("0.Salir");
        opcion=sc.nextInt();
        sc.nextLine();
        switch(opcion){
            case 1:
                System.out.println("Introduzca el numero de dias que quiere aumentar de premium");
                int dias = sc.nextInt();
                P2WSS p2wss = (P2WSS)fipesa.getUsuario();
                try{
                    fipesa.setUsuario(p2wss.UpgradeSubscription(dias));
                }catch(PremiumUpdateException e){
                    System.out.println("Error de subcripcion");
                } catch(TransaccionIncorrecta ex){
                    System.out.println("No hay suficiente dinero en el monedero");
                }
                break;
            case 2:
                menuJuegos(fipesa);
                break;
            case 3:
                fipesa.logout();
                salida = true;
                break;
            case 4:
                menuMonedero(fipesa);
                break;
            case 0:
                salida = false;
                break;
        }
        return salida;
    }
    
    /**
     * 
     * @param fipesa
     * @return 
     */
    public static boolean menuAdmin(Casino fipesa){//no funciona los metodos no estan adaptados al modo texto
        boolean salida = true;
        int opcion = 0;
        Admin admin = (Admin)fipesa.getUsuario();
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Que desea hacer?");
        System.out.println("1.Banear usuario");
        System.out.println("2.Editar datos");
        System.out.println("0.Salir");
        opcion=sc.nextInt();
        sc.nextLine();
        switch(opcion){
            case 1:
                System.out.println("Que usuario quiere banear");
                String nombre = sc.next();
                
                break;
            case 2:
                break;
            case 3:
                fipesa.logout();
                break;
            case 0:
                salida = false;
                break;
        }
        return salida;
    }
    
    /**
     * 
     * @param fipesa 
     */
    public static void menuMonedero(Casino fipesa){
        
        boolean salir = true;
        int opcion = 0;
        double cantidad;
        Scanner sc = new Scanner(System.in);
        while(salir){
            System.out.println("¿Que desea hacer?");
            System.out.println("1.Añadir fondos");
            System.out.println("2.Retirar fondos");
            System.out.println("3.Mostrar fondos");
            System.out.println("0.salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch(opcion){
                case 1:
                    try{
                    System.out.println("Cuanto dinero quiere añadir");
                    cantidad = sc.nextDouble();
                    fipesa.getUsuario().añadirFondos(cantidad);
                    } catch(TransaccionIncorrecta ex){
                        System.out.println("Transaccion Incorrecta");
                    }
                    break;
                case 2:
                    try{
                    System.out.println("Cuanto dinero quiere retirar");
                    cantidad = sc.nextDouble();
                    fipesa.getUsuario().retirarFondos(cantidad);
                    } catch(TransaccionIncorrecta e){
                        System.out.println("Transaccion incorrecta");
                    }
                    break;
                case 3:
                    System.out.println("Dispone de: "+fipesa.getUsuario().getMonedero().getFondos());
                    break;
                case 0:
                    salir = false;
                    break;
            }
        }
        
    }
}
