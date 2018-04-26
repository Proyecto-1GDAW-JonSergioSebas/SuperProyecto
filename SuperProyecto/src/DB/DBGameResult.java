/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import ModelUML.Team;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
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
}
