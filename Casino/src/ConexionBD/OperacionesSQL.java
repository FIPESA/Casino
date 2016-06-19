package ConexionBD;

import java.sql.*;
import Exceptions.*;
import java.util.*;
import Casino.*;

import Usuarios.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OperacionesSQL {
    
    static OperacionesSQL operacion = null;

    private OperacionesSQL() {
    }
    
    public static OperacionesSQL instancia() {
        if (operacion == null) {
            operacion = new OperacionesSQL();
        }
        
        return operacion;
    }
    
    /**
     * Devulve un arraylist con las Transacciones de un jugador en el dia especificado working
     * @param username
     * @param fecha
     * @return 
     */
    public ArrayList<Transaccion> leerTransaccion (String username, java.util.Date fecha){
        ArrayList<Transaccion> cosa = new ArrayList<>();
        java.util.Date fecha_Transaccion;
        double fondos_Transaccion;
        
        try {            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "Select * "
                       + "from Transacciones "
                       + "where Username ='"+username+"' and Date(Fecha) ='"+sdf.format(fecha)+"';"; 
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(sql);
            
            while (rs.next()){
                fecha_Transaccion = rs.getTimestamp(3);
                fondos_Transaccion = rs.getDouble(2);
                cosa.add(new Transaccion(fecha, fondos_Transaccion));
            }
            
        } catch (SQLException ex) {
            System.out.println("error");
        }
        return cosa;
    }
    
    /**
     * Recupera de la base de datos las transacciones del dia especificado working
     * @param fecha
     * @return un arraylist de transacciones
     */
    public ArrayList<Transaccion> leerTransaccion (java.util.Date fecha){
        ArrayList<Transaccion> cosa = new ArrayList<>();
        try {            
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "Select * "
                       + "from Transacciones "
                       + "where Date(Fecha) ='"+sdf.format(fecha)+"';";            
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(sql);
            
            while (rs.next()){
                cosa.add(new Transaccion(rs.getTimestamp(3), rs.getDouble(2)));                
            }            
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        
        return cosa;
    }
    
    /**
     * Agrega una transaccion a la base de datos working
     * @param t Transaccion
     * @param uss Usuario de la transaccion
     */
    public void AddTransaccion (Transaccion t, User uss){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = 
                    "insert into Transacciones (Fondos, Fecha, Username) "
                  + "values('"+Double.toString(t.getFondos())+"','" +sdf.format(t.getFechaActual())+"','"+uss.getUsername()+"');";
            ConexionBD.instancia().getStatement().execute(sql);
        } catch (SQLException e){
            System.out.println("patata");
        }        
    }
    
    /**
     * Agrega un usuario a la base de datos working
     * @param uss Usuario
     */
    public void addUser (User uss){
        
        if(uss instanceof P2WSS){
            try{
                int tipo = 2;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sql = "INSERT INTO Usuarios (Username,Pass,Nombre,Apellido1,Apellido2,Fecha_Registro,Email,Renta_num,Aceptado,Tipo,Fondos,Fecha_fin_premium)VALUES('"+uss.getUsername()+"'.'"+((P2WSS) uss).getPass()+"'.'"+((P2WSS) uss).getName()+"','"+((P2WSS) uss).getLName1()+"','"+((P2WSS) uss).getLName2()+"','"+sdf.format(((P2W) uss).getReg_Date())+"','"+((P2WSS) uss).getEmail()+"','"+Double.toString(((P2WSS) uss).getRenta_num())+"',"+Boolean.toString(((P2WSS) uss).isAceptado())+",'"+Integer.toString(tipo)+"','"+Double.toString(uss.getMonedero().getFondos())+"','"+sdf.format(((P2WSS) uss).getPremium_ending_date())+"')";
                ConexionBD.instancia().getStatement().execute(sql);
            } catch (SQLException e){
                System.out.println("patata");
            }
        } else if(uss instanceof P2W){
            try{
                int tipo = 1;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sql = "INSERT INTO Usuarios (Username,Pass,Nombre,Apellido1,Apellido2,Fecha_Registro,Email,Renta_num,Aceptado,Tipo,Fondos,Ban_Date)VALUES('"+uss.getUsername()+"','"+((P2W) uss).getPass()+"','"+((P2W) uss).getName()+"','"+((P2W) uss).getLName1()+"','"+((P2W) uss).getLName2()+"','"+sdf.format(((P2W) uss).getReg_Date())+"','"+((P2W) uss).getEmail()+"','"+Double.toString(((P2W) uss).getRenta_num())+"',"+Boolean.toString(((P2W) uss).isAceptado())+",'"+Integer.toString(tipo)+"','"+Double.toString(uss.getMonedero().getFondos())+"','"+sdf.format(((P2W) uss).getBan_Date()) +"')"; 
                ConexionBD.instancia().getStatement().execute(sql);
            } catch (SQLException e){
                System.out.println("patata");
            } 
        } else if(uss instanceof Admin){
            try {
                int tipo = 3;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sql = "INSERT INTO Usuarios (Username,Pass,Tipo) VALUES ('"+uss.getUsername()+"','"+((Admin) uss).getPass()+"','"+Integer.toString(tipo)+"';";
                ConexionBD.instancia().getStatement().execute(sql);
            } catch (SQLException e) {
                System.out.println("patata");
            }
        }
    }
    
    /**
     * Recupera de la base de datos un usuario mediante su username y contraseña
     * @param usrname username
     * @param pass contraseña
     * @return el usuario ya instanciado
     * @throws UserReadingException si el usuario no existe
     */
    public User leerUsuario (String usrname, String pass) throws UserReadingException{
        User a = null;
        try{
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery("Select * from Usuarios where Username='"+usrname+"' and Pass='"+pass+"'");
            if(rs.next()){
                if(rs.getInt(10) == 1){
                    a = new P2W(rs.getString(1), rs.getString(2), new Monedero(rs.getDouble(11)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getTimestamp(6), rs.getDouble(8), rs.getDate(12), rs.getBoolean(9));
                }else if(rs.getInt(10) == 2){
                    a = new P2WSS (rs.getString(1), rs.getString(2), new Monedero(rs.getDouble(11)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getTimestamp(6), rs.getDouble(8), rs.getDate(12), rs.getDate(13), rs.getBoolean(9));
                }else if(rs.getInt(10) == 3){
                    a = new Admin(rs.getString(1), rs.getString(2));
                }
            } else{
                throw new UserReadingException("El usuario no existe");
            }
                    
        }catch(SQLException e){
            System.out.println("Patata");
        }       
        return a;
    }
    
    /**
     * Recupera de la base de datos un usuario mediante su username 
     * @param usrname username
     * @return el usuario ya instanciado
     * @throws UserReadingException si el usuario no existe
     */
    public User leerUsuario (String usrname) throws UserReadingException{
        User a = null;
        try{
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery("Select * from Usuarios where Username='"+usrname+"'");
            if(rs.next()){
                if(rs.getInt(10) == 1){
                    a = new P2W(rs.getString(1), rs.getString(2), new Monedero(rs.getDouble(11)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getTimestamp(6), rs.getDouble(8), rs.getDate(12), rs.getBoolean(9));
                }else if(rs.getInt(10) == 2){
                    a = new P2WSS (rs.getString(1), rs.getString(2), new Monedero(rs.getDouble(11)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getTimestamp(6), rs.getDouble(8), rs.getDate(12), rs.getDate(13), rs.getBoolean(9));
                }else if(rs.getInt(10) == 3){
                    a = new Admin(rs.getString(1), rs.getString(2));
                }
            } else{
                throw new UserReadingException("El usuario no existe");
            }
                    
        }catch(SQLException e){
            System.out.println("Patata");
        }       
        return a;
    }
    
    /**
     * Metodo que actualiza la informacion de un user en la base de datos
     * @param usuario 
     */
    public void actualizarUsuario(User usuario){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int tipo;
            String sql = "";
            if(usuario instanceof P2WSS){
                P2WSS p2w = (P2WSS)usuario;
                sql = "UPDATE Usuarios "
                        + "SET Pass='" + p2w.getPass() + "', Nombre='" + p2w.getName() + "', Apellido1='" + p2w.getLName1() + "', Apellido2='" + p2w.getLName2() + "', Email='" + p2w.getEmail() + "', Renta_num='" + Double.toString(p2w.getRenta_num()) + "', Aceptado=" + Boolean.toString(p2w.isAceptado()) + ", Tipo='2', Fondos='" + Double.toString(p2w.getMonedero().getFondos()) + "', Ban_Date='" + sdf.format(p2w.getBan_Date()) + "', Fecha_fin_premium='" + sdf.format(p2w.getPremium_ending_date()) + "' "
                        + "WHERE Username='" + usuario.getUsername() + "';";
            } else if(usuario instanceof P2W){
                P2W p2w = (P2W)usuario;
                sql = "UPDATE Usuarios "
                        + "SET Pass='" + p2w.getPass() + "', Nombre='" + p2w.getName() + "', Apellido1='" + p2w.getLName1() + "', Apellido2='" + p2w.getLName2() + "', Email='" + p2w.getEmail() + "', Renta_num='" + Double.toString(p2w.getRenta_num()) + "', Aceptado=" + Boolean.toString(p2w.isAceptado()) + ", Tipo='1', Fondos='" + Double.toString(p2w.getMonedero().getFondos()) + "', Ban_Date='"+sdf.format(p2w.getBan_Date())+"' "
                        + "WHERE Username='" + usuario.getUsername() + "';";
                
                
            }
            
            ConexionBD.instancia().getStatement().execute(sql);
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
    }
    
    public void bannearUsuario(String username, java.util.Date ban_Date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql="UPDATE Usuarios "
                 + "SET Aceptado=false, Ban_Date='" + sdf.format(ban_Date) + "' "
                 + "WHERE Username='" + username + "';";
        
        try {
            ConexionBD.instancia().getStatement().execute(sql);
        } catch (SQLException ex) {
            System.out.println("Patata en la base de datos");
        }
    }
    
    public void actualizarFondos(User usuario){
        String sql;
        sql= "UPDATE Usuarios "
            +"SET Fondos='" + Double.toString(usuario.getMonedero().getFondos()) + "' "
            +"WHERE Username='" + usuario.getUsername() + "';";
        
        try {
            ConexionBD.instancia().getStatement().execute(sql);
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        
    }
        
    /**
     * Devuelve una tabla bidimensional con la lista de usuarios registrados en el casino
     * @return String[][] 
     */
    public String[][] Listado (){
        String[][] usuarios = null;
        String sql = "Select count(Username) from Usuarios";
        String sql_usuarios="Select Username,Nombre,Apellido1,Apellido2,Fecha_Registro,Email,Renta_num,Aceptado,Tipo,Fondos,Ban_Date,Fecha_fin_premium "
                 + "from Usuarios ";       
        
        ResultSet rs,rsi;
        try {
            rsi = ConexionBD.instancia().getStatement().executeQuery(sql);
            int r = 0;
            if(rsi.next()){
                r = rsi.getInt(1);
            }
            usuarios = new String[r][12];
            rsi.close();
            rs = ConexionBD.instancia().getStatement().executeQuery(sql_usuarios);
            int j = 0;
            while (rs.next()){
                for (int i=0;i<12;i++){
                     usuarios[j][i]=rs.getString(i+1);
                }
                j++; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
        
    }
    
    /**
     * Metodo que devuelve una tabla bidimensional de datos de usuario
     * @param filtro Username
     * @return String[][] 
     */
    public String[][] Listado_filtrado_user (String filtro){
        String[][] usuarios = null;
        String sql = "Select count(Username) from Usuarios";
        String sql_usuarios="Select Username,Nombre,Apellido1,Apellido2,Fecha_Registro,Email,Renta_num,Aceptado,Tipo,Fondos,Ban_Date,Fecha_fin_premium "
                + "from Usuarios "
                + "where Username='"+filtro+"';";
        
        ResultSet rs,rsi;
        try {
            rsi = ConexionBD.instancia().getStatement().executeQuery(sql);
            int r = 0;
            if(rsi.next()){
                r = rsi.getInt(1);
            }
            usuarios = new String[r][12];
            rsi.close();
            rs = ConexionBD.instancia().getStatement().executeQuery(sql_usuarios);
            int j = 0;
            while (rs.next()){
            for (int i=0;i<12;i++){
                 usuarios[j][i]=rs.getString(i+1);
            }
            j++; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
        
    }
    public String[][] Listado_filtrado_tipo (String filtro){
        String[][] usuarios = null;
        String sql = "Select count(Username) from Usuarios";
        String sql_usuarios="Select Username,Nombre,Apellido1,Apellido2,Fecha_Registro,Email,Renta_num,Aceptado,Tipo,Fondos,Ban_Date,Fecha_fin_premium "
                + "from Usuarios "
                + "where Tipo='"+filtro+"';";
        
        ResultSet rs,rsi;
        try {
            rsi = ConexionBD.instancia().getStatement().executeQuery(sql);
            int r = 0;
            if(rsi.next()){
                r = rsi.getInt(1);
            }
            usuarios = new String[r][12];
            rsi.close();
            rs = ConexionBD.instancia().getStatement().executeQuery(sql_usuarios);
            int j = 0;
            while (rs.next()){
            for (int i=0;i<12;i++){
                 usuarios[j][i]=rs.getString(i+1);
            }
            j++; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
        
    }
    
    /**
     * Agrega una jugada de los slots a la base de datos
     * @param jugada
     * @param username del usuario al que pertenece la jugada
     */
    public void addJugada_Slots (Jugada_Slots jugada, String username){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ConexionBD.instancia().getStatement().execute(
                    "insert INTO Jugadas_Slots (Username, Resultado1, Resultado2, Resultado3, CantidadGanada, Fecha) VALUES ('"
                            + username + "','" + jugada.getResultado1() + "','" + jugada.getResultado2() + "','" + jugada.getResultado3() + "','" + jugada.getCantidaGanada() + "','" + sdf.format(jugada.getFecha()) + "')"
            );
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        
    }
    
    /**
     * recupera una lista de jugadas de slots de la base de datos de un usuario especificado
     * @param username
     * @return Arraylist de Jugada_Slots
     */
    public ArrayList<Jugada_Slots> leerJugada_Slots(String username){
        ArrayList<Jugada_Slots> jugadas = new ArrayList<>();
        try {
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
                    "SELECT * from Jugadas_Slots WHERE Username= '" + username + "'"
            );
            
            while (rs.next()){
                jugadas.add(new Jugada_Slots(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getTimestamp(7)));
            }
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        return jugadas;
        
    }
    
    /**
     * Recupera una coleccion de jugadas de slots de la base de datos perteneciente a un usuario en una fecha determinada
     * @param username
     * @param fecha
     * @return Un Arraylist de Jugada_Slots
     */
    public ArrayList<Jugada_Slots> leerJugada_Slots(String username, java.util.Date fecha){
        ArrayList<Jugada_Slots> jugadas = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
                    "SELECT * "
                  + "from Jugadas_Slots "
                  + "WHERE Username= '" + username + "' AND Date(fecha)= '" + sdf.format(fecha) + "'"
            );
            
            while (rs.next()){
                jugadas.add(new Jugada_Slots(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getTimestamp(7)));
            }
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        return jugadas;
        
    }
    
    /**
     * recupera de la base de datos una coleccion de jugadas de slots de una fecha especificada
     * @param fecha
     * @return un ArrayList de Jugada_Slots
     */
    public ArrayList<Jugada_Slots> leerJugada_Slots(java.util.Date fecha){
        ArrayList<Jugada_Slots> jugadas = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
                    "SELECT * from Jugadas_Slots WHERE Date(fecha)= '" + sdf.format(fecha) + "'"
            );
            
            while (rs.next()){
                jugadas.add(new Jugada_Slots(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getTimestamp(7)));
            }
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        return jugadas;
    }
    
    /**
     * Agrega a la base de datos una Jugada_ruleta 
     * @param jugada
     * @param username 
     */
    public void addJugada_Ruleta(Jugada_Ruleta jugada, String username){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{    
            ConexionBD.instancia().getStatement().execute(
                    "insert into Jugadas_Ruleta (NumerosApostados, Resultado, CantidadApostada, Fecha, CantidadGanada, Username) "
                  + "values('" + jugada.StringNumerosApostados() + "','" + jugada.getResultado() + "','" + jugada.getCantidadApostada() + "','" + sdf.format(jugada.getFecha()) + "','" + jugada.getCantidadGanada() + "','" + username + "');"                    
                   
            );            
        } catch (SQLException e){
            System.out.println("Error en la base de datos");
        }
    }
    
    /**
     * Metodo que recupera una lista de Jugada_Ruleta de la base de datos pasando un usuario por parametro
     * @param username
     * @return ArrayList de Jugada_Ruleta
     */
    public ArrayList<Jugada_Ruleta> leerJugada_Ruleta(String username){
        String stringApostados[];
        int numerosApostados[];        
        ArrayList<Jugada_Ruleta> jugadas = new ArrayList<>();
        
        try {
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
                    "SELECT * "
                  + "FROM Jugadas_Ruleta "
                  + "WHERE Username= '" + username + "';"
            );
            
            while (rs.next()){
                stringApostados = rs.getString(7).split(",");
                numerosApostados = new int[stringApostados.length];
                for(int i=0;i<stringApostados.length;i++){
                    numerosApostados[i] = Integer.parseInt(stringApostados[i]);
                }
                jugadas.add(new Jugada_Ruleta(numerosApostados, rs.getInt(2), rs.getDouble(3), rs.getDouble(5), rs.getTimestamp(4)));
            }
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        return jugadas;
    }
    
    /**
     * Metodo que recupera de la base de datos una lista de Jugada_Ruleta por fecha
     * @param fecha
     * @return ArrayList de Jugada_Ruleta
     */
    public ArrayList<Jugada_Ruleta> leerJugada_Ruleta(java.util.Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringApostados[];
        int numerosApostados[];        
        ArrayList<Jugada_Ruleta> jugadas = new ArrayList<>();
        
        try {
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
                    "SELECT * "
                  + "FROM Jugadas_Ruleta "
                  + "WHERE Date(Fecha)= '" + sdf.format(fecha) + "';"
            );
            
            while (rs.next()){
                stringApostados = rs.getString(7).split(",");
                numerosApostados = new int[stringApostados.length];
                for(int i=0;i<stringApostados.length;i++){
                    numerosApostados[i] = Integer.parseInt(stringApostados[i]);
                }
                jugadas.add(new Jugada_Ruleta(numerosApostados, rs.getInt(2), rs.getDouble(3), rs.getDouble(5), rs.getTimestamp(7)));
            }
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        return jugadas;
    }
    
    /**
     * Metodo que recupera un lista de Jugada_Ruleta mediante el usuario y la fecha
     * @param username
     * @param fecha
     * @return ArrayList de Jugada_Ruleta
     */
    public ArrayList<Jugada_Ruleta> leerJugada_Ruleta(String username, java.util.Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringApostados[];
        int numerosApostados[];        
        ArrayList<Jugada_Ruleta> jugadas = new ArrayList<>();
        
        try {
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
                    "SELECT * "
                  + "FROM Jugadas_Ruleta "
                  + "WHERE Username = '"+ username + "' AND Date(Fecha)= '" + sdf.format(fecha) + "';"
            );
            
            while (rs.next()){
                stringApostados = rs.getString(7).split(",");
                numerosApostados = new int[stringApostados.length];
                for(int i=0;i<stringApostados.length;i++){
                    numerosApostados[i] = Integer.parseInt(stringApostados[i]);
                }
                jugadas.add(new Jugada_Ruleta(numerosApostados, rs.getInt(2), rs.getDouble(3), rs.getDouble(5), rs.getTimestamp(7)));
            }
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        return jugadas;
    }
    
    /**
     * Metodo que agrega una jugada_BlackJack a la base de datos
     * @param jugada
     * @param username 
     */
    public void addJugada_Blackjack(Jugada_BlackJack jugada, String username){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql = "INSERT INTO Jugadas_Blackjack (CantidadApostada, ResCartasUser, ResCartasCrupier, CantidadGanada, Fecha, Username)"
                   + "VALUES('" + jugada.getCantidadApostada() + "','" + jugada.getCartasJugador() + "','" + jugada.getCartasCroupier() + "','" + jugada.getCantidadGanada() + "','" + sdf.format(jugada.getFecha()) + "','" + username + "');";
        try{    
            ConexionBD.instancia().getStatement().execute(sql);            
        } catch (SQLException e){
            System.out.println("Error en la base de datos");
        }
    }
    
    /**
     * metodo que recupera una lista de Jugadas de Blackjack de la base de datos
     * de un usuario y una fecha especificada
     * @param username
     * @param fecha
     * @return ArrayList de Jugada_BlackJack
     */
    public ArrayList<Jugada_BlackJack> leerJugada_BlackJack(String username, java.util.Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");     
        ArrayList<Jugada_BlackJack> jugadas = new ArrayList<>();
        
        try {
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
                    "SELECT * "
                  + "FROM Jugadas_Blackjack "
                  + "WHERE Username = '"+ username + "' AND Date(Fecha)= '" + sdf.format(fecha) + "';"
            );
            
            while (rs.next()){                
                jugadas.add(new Jugada_BlackJack(rs.getDouble("CantidadApostad"), rs.getDouble("CantidadGanada"), rs.getInt("ResCartasUser"), rs.getInt("ResCartasCrupier"), rs.getTimestamp("Fecha")));
            }
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        return jugadas;
    }
    
    /**
     * metodo que recupera una lista de Jugadas de Blackjack de la base de datos
     * de un usuario especificado
     * @param username
     * @return ArrayList de Jugtada_BlackJack
     */
    public ArrayList<Jugada_BlackJack> leerJugada_BlackJack(String username){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");     
        ArrayList<Jugada_BlackJack> jugadas = new ArrayList<>();
        
        try {
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
                    "SELECT * "
                  + "FROM Jugadas_Blackjack "
                  + "WHERE Username = '"+ username + "';"
            );
            
            while (rs.next()){                
                jugadas.add(new Jugada_BlackJack(rs.getDouble("CantidadApostada"), rs.getDouble("CantidadGanada"), rs.getInt("ResCartasUser"), rs.getInt("ResCartasCrupier"), rs.getTimestamp("Fecha")));
            }
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        return jugadas;
    }
    
    /**
     * metodo que recupera una lista de Jugadas de Blackjack de la base de datos
     * de una fecha especificada
     * @param fecha
     * @return ArrayList de Jugada_BlackJack
     */
    public ArrayList<Jugada_BlackJack> leerJugada_BlackJack(java.util.Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");     
        ArrayList<Jugada_BlackJack> jugadas = new ArrayList<>();
        
        try {
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
                    "SELECT * "
                  + "FROM Jugadas_Blackjack "
                  + "WHERE Date(Fecha)= '" + sdf.format(fecha) + "';"
            );
            
            while (rs.next()){                
                jugadas.add(new Jugada_BlackJack(rs.getDouble("CantidadApostada"), rs.getDouble("CantidadGanada"), rs.getInt("ResCartasUser"), rs.getInt("ResCartasCrupier"), rs.getTimestamp("Fecha")));
            }
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos");
        }
        return jugadas;
    }
    
    
    public String[][] listadoTransacciones (String Username){
        String[][] transacciones = null;
        String sql = "Select count(Username) from Transacciones";
        String sql_usuarios="Select Id_Transaccion,Fondos,Fecha,Username "
                 + "from Transacciones where Username='"+Username+"';";       
        
        ResultSet rs,rsi;
        try {
            rsi = ConexionBD.instancia().getStatement().executeQuery(sql);
            int r = 0;
            if(rsi.next()){
                r = rsi.getInt(1);
            }
            transacciones = new String[r][4];
            rsi.close();
            rs = ConexionBD.instancia().getStatement().executeQuery(sql_usuarios);
            int j = 0;
            while (rs.next()){
            for (int i=0;i<4;i++){
                 transacciones[j][i]=rs.getString(i+1);
            }
            j++; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transacciones;
        
    }
    
    public boolean comprobarRenta(User uss){
        boolean valido = false;
        try {            
            double suma = 0;
            String sql1 = "Select count(Id_Jugada) from Jugadas_Slots where Username='"+uss.getUsername()+"';";
            String sql2 = "Select sum(CantidadApostada) from Jugadas_Ruleta where Username='"+uss.getUsername()+"';";
            String sql3 = "Select sum(CantidadApostada) from Jugadas_Blackjack where Username='"+uss.getUsername()+"';";
            ResultSet rs,rsi,rse;
            rs = ConexionBD.instancia().getStatement().executeQuery(sql1);
            rs.next();
            suma += rs.getDouble(1);
            rs.close();
            rsi = ConexionBD.instancia().getStatement().executeQuery(sql2);
            rsi.next();
            suma += rsi.getDouble(1);
            rsi.close();
            rse = ConexionBD.instancia().getStatement().executeQuery(sql3);
            rse.next();
            suma += rse.getDouble(1);
            rse.close();
            if(uss instanceof P2W){
                P2W a = (P2W)uss;
                if(suma < (a.getRenta_num()/2)){
                    valido = true;
                }
            }            
        } catch (SQLException ex) {
            System.out.println("patata");
        }
        return valido;
    }
    
    
    
    
    
    
    
    
}
    
    
