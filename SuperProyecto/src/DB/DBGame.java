/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import ModelUML.Game;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class DBGame {

    /**
     * Introduce el juego con la jornada a la que pertenece y la fecha de ese
     * partido
     *
     * @param gm El juego
     * @param matchsetnum el numero de jornada
     * @param con la conexion
     * @throws SQLException hay una excepcion SQL
     */
    public static void insertGame(Game gm, int matchsetnum, Connection con) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date gameday = new Date(gm.getDateTime().getTime());
        Statement sta = con.createStatement();
        sta.executeUpdate("INSERT INTO GAME(MATCHSET,DATE_TIME) VALUES(" + matchsetnum + ",TO_DATE('" + gameday + "','YYYY-MM-DD'))");
        sta.close();
    }

    /**
     * Coge los ID de los Game que correspondan con el ID de MatchSet que se le
     * pasa
     *
     * @param matchSetId el id del matchSet al que corresponden
     * @param con la conexion
     * @return ArrayList de int con los ID de los Game
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> getGamesID(int matchSetId, Connection con) throws SQLException {
        ArrayList<Integer> gamesID = new ArrayList();
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT * FROM GAME WHERE MATCHSET = " + matchSetId + "");
        while (resul.next()) {
            gamesID.add(resul.getInt(1));
        }
        resul.close();
        sta.close();
        return gamesID;
    }

    /**
     * Coge de la base de datos el Date de cuando se celebra el partido
     *
     * @param id el id del partido
     * @param con la conexion
     * @return devuelve un objeto Date
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static Date getGameDate(int id, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT DATE_TIME FROM GAME WHERE ID_GA=" + id + "");
        while (resul.next()) {
            Date date = resul.getDate(1);
            return date;
        }
        return null; //si esto puede llegar a suceder, ya tenemos un problema
    }

    /**
     * Coge de la base de datos el Date más elevado de la liga correspondiente
     *
     * @param leagueId el ID de la liga
     * @param con la conexion
     * @return devuelve un objeto Date
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static Date getLeagueEndDate(int leagueId, Connection con) throws SQLException {
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("select max(date_time) from game where matchset in (select id_ms from matchset where league = "+leagueId+")");
        while (resul.next()) {
            Date date = resul.getDate(1);
            return date;
        }
        return null;
    }
}
