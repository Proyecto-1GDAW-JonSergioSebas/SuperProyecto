/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import ModelUML.Game;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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
     * Introduce el juego con la jornada a la que pertenece y la fecha de ese partido
     * @param gm El juego 
     * @param matchsetnum el numero de jornada
     * @param con la conexion
     * @throws SQLException hay una excepcion SQL
     */
    public static void insertGame(Game gm,int matchsetnum,Connection con) throws SQLException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date gameday = new Date(gm.getDateTime().getTime());
        Statement sta=con.createStatement();
        sta.executeUpdate("INSERT INTO GAME(MATCHSET,DATE_TIME) VALUES("+matchsetnum+",TO_DATE('"+gameday+"','YYYY-MM-DD'))");
        sta.close();
    }
}
