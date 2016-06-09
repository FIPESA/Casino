package ConexionBD;

import java.sql.*;
import Exceptions.*;


public class ConexionBD {
    
    private final static String ip ="jdbc:mysql://10.1.3.201:3306/casino";
    private final static String user = "casino";
    private final static String pass = "casino";
    private final static String iplocal = "jdbc:mysql://localhost:3306/casino,root,mysql";
    
    
    private Connection conn;
    private Statement stmt;
    
    private static ConexionBD instancia = null;
    
    private ConexionBD() throws BDException {
        try {
            conn = DriverManager.getConnection(ip,user,pass);
            stmt = conn.createStatement();
            if ( conn != null ){
                System.out.println("Conexion establecida");
            }
            
        }
        catch(SQLException e) {
            throw new BDException("Error de conexion");
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public Statement getStatement() {
        return stmt;
    }
    
    public static void crearConexion() throws BDException {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        
    }
    
    public static ConexionBD instancia() {
        return instancia;
    }
    
    public static void desconectar() {
        if (instancia != null) {
            try {
                instancia.stmt.execute("shutdown");
                instancia.stmt.close();
                instancia.conn.close();
                instancia = null;
            }
            catch(SQLException e) {
            }
        }
    }
    
}
