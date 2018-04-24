/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sebastián Zawisza
 * @author Sergio Zulueta
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class DBController {
    /**
     * Este metodo crea la conexión a la base de datos
     * @throws ClassNotFoundException no encuentra la clase
     * @throws SQLException hay una excepcion SQL
     * @return la conexion a la base de datos
     */
    public static Connection createConnection() throws ClassNotFoundException, SQLException{
        
        Class.forName("oracle.jdbc.OracleDriver");//Indicar el driver
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@SrvOracle:1521:orcl","eqdaw01","eqdaw01");//Crear la conexion
        
        return con;
    }
    
    /**
     * Pide a la clase DBTeam el numero de equipos que hay.
     * 
     * @return el numero de equipos
     * @see DBTeam.getTeamQuantity()
     */
    public static int teamQuantity(){
        
    }
}
