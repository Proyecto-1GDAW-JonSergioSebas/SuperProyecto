/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.ResultSet;
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
public class DBLeague {
    /**
     * Crea la liga en la base de datos
     * @param leaguename el nombre de la liga    
     * @param con la conexion
     * @throws SQLException 
     */
    public static void insertLeague(String leaguename,Connection con) throws SQLException{
        Statement est = con.createStatement();
        est.executeUpdate("INSERT INTO LEAGUE(LEAGUE_NAME) VALUES('"+leaguename+"');");
        est.close();
    }
    /**
     * Busca la liga y devuelve su id
     * @param leaguename el nombre de la liga
     * @param con la conexion
     * @return devuelve el id de la liga
     * @throws SQLException 
     */
    public static int askForLeague(String leaguename,Connection con) throws SQLException{
        Statement est = con.createStatement();
        ResultSet resul= est.executeQuery("SELECT * AS LEAGUENUM FROM LEAGUE WHERE LEAGUE_NAME='"+leaguename+"';");
        resul.next();
        int temp = resul.getInt("LEAGUENUM");
        resul.close();
        est.close();
        return temp;
    }
}
