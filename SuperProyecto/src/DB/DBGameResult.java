/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import ModelUML.Team;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Esta clase gestiona las acciones necesarias en la base de datos sobre los objetos GameResult
 * @author Jon Maneiro
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @version %I% %G%
 * @since 1.0
 */
public class DBGameResult {
    /**
     * Introduce el Game_Result con los datos proporcionados
     * @param gamenum el id del juego
     * @param teamnum el id del equipo
     * @param con la conexion
     * @throws SQLException hay una excepcion SQL
     */
    public static void insertGameResult(int gamenum,int teamnum,Connection con) throws SQLException{
        Statement esta = con.createStatement();
        esta.executeUpdate("INSERT INTO GAME_RESULT(TEAM,GAME) VALUES("+teamnum+","+gamenum+")");    
        esta.close();
    }
    /**
     * Pide el id de los Equipos que participan en el partido del cual se facilita el id
     * @param gameID el id del partido
     * @param con la conexion
     * @return devuelve un ArrayList con los equipos que participan en el partido
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> getGameTeamID(int gameID,Connection con) throws SQLException{
        ArrayList<Integer> teamID= new ArrayList();
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT TEAM FROM GAME_RESULT WHERE GAME="+gameID+"");
        while(resul.next()){
            teamID.add(resul.getInt(1));
        }
        resul.close();
        sta.close();
        return teamID;
    }
    /**
     * Pide los resultados de los equipos que participan en el partido del cual se facilita el id
     * @param gameID el id del partido
     * @param con la conexion
     * @return un ArrayList con los resultados de los equipos que participan en el partido
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> getScores(int gameID,Connection con) throws SQLException{
        ArrayList<Integer> scores= new ArrayList();
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT SCORE FROM GAME_RESULT WHERE GAME="+gameID+"");
        while(resul.next()){
            scores.add(resul.getInt(1));
        }
        resul.close();
        sta.close();
        return scores;
    }
    /**
     * Obtiene la puntuacion de un Team en un Game
     * @param id la id del Game
     * @param teamID la id del Team
     * @param con la conexion
     * @return un int con la puntuacion que ha obtenido un Team en un Game
     * @throws SQLException si se da alguna excepcion SQL
     */
    static int getTeamScore(Integer id, Integer teamID, Connection con) throws SQLException {
        int x =-1;
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT SCORE FROM GAME_RESULT WHERE GAME="+id+" AND TEAM="+teamID+"");
        while(resul.next()){
            x=resul.getInt("SCORE");
        }
        resul.close();
        sta.close();
        return x;
    }
    
}
