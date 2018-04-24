/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import static DB.DBController.createConnection;
import ModelUML.Player;
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
public class DBPlayer {
    /**
     * Realiza una consulta para devolver los jugadores pertenecientes al 
     * equipo del cual le pasamos la id
     * 
     * @param teamid el id del equipo del cual hay que buscar los jugadores
     * @param con la conexion
     * @return ArrayList con los jugadores
     * @throws SQLException 
     */
    public static ArrayList<Player> getPlayers(int teamid,Connection con) throws SQLException{
       Statement  est = con.createStatement();
       ResultSet rest = est.executeQuery("SELECT * FROM PLAYER WHERE TEAM="+teamid+";");
       ArrayList<Player> players= new ArrayList();
       while(rest.next()){
           players.add(new Player(rest.getString(2),rest.getString(3),rest.getBigDecimal(4),rest.getString(5)));
       }
       rest.close();
       est.close();
       return players;
    }
}
