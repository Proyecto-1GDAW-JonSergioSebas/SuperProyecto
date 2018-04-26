/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class DBMatchSet {
    /**
     * Introduce una jornada con los datos proporcionados
     * @param leaguenum el numero de la liga
     * @param con la conexion
     * @throws SQLException 
     */
    public static void insertMatchSet(int leaguenum,Connection con) throws SQLException{
        Statement sta = con.createStatement();
        sta.executeUpdate("INSERT INTO MATCHSET(LEAGUE) VALUES('"+leaguenum+"')");
        sta.close();
    }
}
