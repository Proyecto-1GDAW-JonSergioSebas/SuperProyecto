/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDB;

import java.sql.*;
/**
 *
 * @author Jon Maneiro
 * @author Sebastián Zawisza
 * @author Sergio Zulueta
 * @version %I% %G%
 * @since 1.0
 */
public class DBController {
    /**
     * Este metodo crea la conexión a la base de datos
     */
    public static void createConnection() throws ClassNotFoundException, SQLException{
        
            Class.forName("oracle.jdbc.OracleDriver");//Indicar el driver
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@SrvOracle:1521:orcl","eqdaw01","eqdaw01");//Crear la conexion
            con.close();
            
        
        
    }
}
