/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import static DB.DBLeague.askForLeague;
import static DB.DBLeague.insertLeague;
import static DB.DBMatchSet.insertMatchSet;
import static DB.DBPlayer.getPlayers;
import static DB.DBTeam.getTeams;
import static DB.DBTeamOwner.getTeamOwner;
import ModelUML.Player;
import ModelUML.Team;
import ModelUML.TeamOwner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

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
     * Pide a la clase DBTeam el numero de equipos que hay, y los
     * devuelve.
     * @param con la conexion
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return el numero de equipos
     * @see DBTeam#getTeams(java.sql.Connection) 
     */
    public static ArrayList<Team> teams(Connection con) throws ClassNotFoundException, SQLException{
        ArrayList<Team> teams= getTeams(con);
        return teams;
    }
    /**
     * Pide a la clase TeamOwner el TeamOwner correspondiente 
     * a la id que le pasamos
     * @param teamownerid la id del TeamOwner 
     * @param con la conexion
     * @throws SQLException
     * @return el TeamOwner solicitado
     * @see DBTeamOwner#getTeamOwner(int, java.sql.Connection) 
     */
    public static TeamOwner obtainTeamOwner(int teamownerid,Connection con) throws SQLException{
        TeamOwner own= getTeamOwner(teamownerid,con);
        return own;
    }
    
    /**
     * Pide a la clase DBPlayer un ArrayList de Player
     * @param teamid la id del equipo del cual queremos los jugadores
     * @param con   la conexion
     * @return ArrayList de los jugadores
     * @throws SQLException 
     * @see DBPlayer#getPlayers(int, java.sql.Connection) 
     */
    public static ArrayList<Player> obtainPlayers(int teamid,Connection con) throws SQLException{
        ArrayList<Player> players =getPlayers(teamid,con);
        return players;
    }
    /**
     * Pide a la Clase DBLeague que cree la liga
     * @param leaguename el nombre de la liga
     * @param con la conexion
     */
    public static void createLeague(String leaguename,Connection con) throws SQLException{
        insertLeague(leaguename,con);
    }
    public static void createMatchSet(String leaguename,Connection con) throws SQLException{
        int temp=askForLeague(leaguename,con);
        insertMatchSet(temp,con);
    }
}
