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
import java.util.ArrayList;

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
     * @throws SQLException hay una excepcion SQL
     */
    public static void insertMatchSet(int leaguenum,Connection con) throws SQLException{
        Statement sta = con.createStatement();
        sta.executeUpdate("INSERT INTO MATCHSET(LEAGUE) VALUES('"+leaguenum+"')");
        sta.close();
    }
    /**
     * Coge los id de los MatchSets correspondientes a la liga que se le pasa 
     * y los devuelve en un ArrayList de Integers
     * @param idLeague el id de la liga
     * @param con la conexion
     * @return un ArrayList con los id de los MatchSets
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> getMatchSetsID(int idLeague,Connection con) throws SQLException{
        ArrayList<Integer> matchSetsID = new ArrayList();
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT * FROM MATCHSET WHERE LEAGUE="+idLeague+"");
        while(resul.next()){
            matchSetsID.add(resul.getInt(1));
        }
        resul.close();
        sta.close();
        return matchSetsID;
    }
}
