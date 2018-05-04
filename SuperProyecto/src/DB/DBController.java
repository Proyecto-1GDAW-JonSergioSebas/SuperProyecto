/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import static DB.DBGame.getGameDate;
import static DB.DBGame.getGamesID;
import static DB.DBGame.insertGame;
import static DB.DBGameResult.getGameTeamID;
import static DB.DBGameResult.getScores;
import static DB.DBGameResult.insertGameResult;
import static DB.DBLeague.askForLeague;
import static DB.DBLeague.getLastLeagueID;
import static DB.DBLeague.insertLeague;
import static DB.DBMatchSet.getMatchSetsID;
import static DB.DBMatchSet.insertMatchSet;
import static DB.DBPlayer.getPlayers;
import static DB.DBTeam.getGameTeam;
import static DB.DBTeam.getTeams;
import static DB.DBTeam.searchTeam;
import static DB.DBTeamOwner.getTeamOwner;
import ModelUML.DBUser;
import ModelUML.Game;
import ModelUML.Player;
import ModelUML.Team;
import ModelUML.TeamOwner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
     *
     * @throws ClassNotFoundException no encuentra la clase
     * @throws SQLException hay una excepcion SQL
     * @return la conexion a la base de datos
     */
    public static Connection createConnection() throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.OracleDriver");//Indicar el driver
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@SrvOracle:1521:orcl", "eqdaw01", "eqdaw01");//Crear la conexion

        return con;
    }

    /**
     * Pide a la clase DBTeam el numero de equipos que hay, y los devuelve.
     *
     * @param con la conexion
     * @throws ClassNotFoundException no se han encontrado las clases
     * @throws SQLException hay una excepcion SQL
     * @return el numero de equipos
     * @see DBTeam#getTeams(java.sql.Connection)
     */
    public static ArrayList<Team> teams(Connection con) throws ClassNotFoundException, SQLException {
        ArrayList<Team> teams = getTeams(con);
        return teams;
    }

    /**
     * Pide a la clase TeamOwner el TeamOwner correspondiente a la id que le
     * pasamos
     *
     * @param teamownerid la id del TeamOwner
     * @param con la conexion
     * @throws SQLException hay una excepcion SQL
     * @return el TeamOwner solicitado
     * @see DBTeamOwner#getTeamOwner(int, java.sql.Connection)
     */
    public static TeamOwner obtainTeamOwner(int teamownerid, Connection con) throws SQLException {
        TeamOwner own = getTeamOwner(teamownerid, con);
        return own;
    }

    /**
     * Pide a la clase DBPlayer un ArrayList de Player
     *
     * @param teamid la id del equipo del cual queremos los jugadores
     * @param con la conexion
     * @return ArrayList de los jugadores
     * @throws SQLException hay una excepcion SQL
     * @see DBPlayer#getPlayers(int, java.sql.Connection)
     */
    public static ArrayList<Player> obtainPlayers(int teamid, Connection con) throws SQLException {
        ArrayList<Player> players = getPlayers(teamid, con);
        return players;
    }

    /**
     * Pide a la Clase DBLeague que cree la liga
     *
     * @param leaguename el nombre de la liga
     * @param con la conexion
     * @throws SQLException hay una excepcion SQL
     */
    public static void createLeague(String leaguename, Connection con) throws SQLException {
        insertLeague(leaguename, con);
    }

    /**
     * Pide el id de la liga correspondiente y envia el id para insertar las
     * jornadas en esa liga
     *
     * @param leaguename en nombre de la liga
     * @param con la conexion
     * @throws SQLException hay una excepcion SQL
     */
    public static void createMatchSet(String leaguename, Connection con) throws SQLException {
        int temp = askForLeague(leaguename, con);
        insertMatchSet(temp, con);
    }

    /**
     * Crea los juegos, primero enviando a DBGame los datos del juego en si,
     * despues pide las id de los equipos que participan en ese partido y se los
     * pasa a otro metodo que introduce cada equipo en el Game_Resul
     * correspondiente
     *
     * @param gm el juego
     * @param gamenum el id del juego
     * @param matchsetnum el id de la jornada
     * @param con la conexion
     * @throws SQLException hay una excepcion SQL
     */
    public static void createGames(Game gm, int gamenum, int matchsetnum, Connection con) throws SQLException {
        insertGame(gm, matchsetnum, con);
        int teamnum = searchTeam(gm.getTeam1().getTeamName(), con);
        insertGameResult(gamenum, teamnum, con);
        teamnum = searchTeam(gm.getTeam2().getTeamName(), con);
        insertGameResult(gamenum, teamnum, con);
    }

    /**
     * Devuelve el tipo de cuenta del usuario cuyo usuario y contraseña son
     * introducidos.
     *
     * @param us usuario
     * @param pw contraseña
     * @param con conexión
     * @return el tipo de cuenta
     * @throws SQLException si ocurre un error
     */
    public static int getAccountType(String us, String pw, Connection con) throws SQLException {
        return DBProcedures.LoginGetType(us, pw, con);
    }


    /**
     * devuelve la lista de todos los usuarios
     *
     * @param con conexion
     * @return la dicha lista
     */
    public static ArrayList<DBUser> selectAllDBUsers(Connection con) throws SQLException {
        return DBDBUser.selectAllUsers(con);
    }

    public static void insertDBDBUser(String username, char[] password, Connection con) throws SQLException {
        DBDBUser.insertDBUser(username, password, con);
    }

    public static void deleteDBDBUser(String username, char[] password, Connection con) throws SQLException {
        DBDBUser.deleteDBUser(username, password, con);
    }

    public static void updateDBDBUser(String username, char[] password, Connection con) throws SQLException {
        DBDBUser.updateDBUserPassword(username, password, con);
    }
    /**
     * Pide a la clase DBLeague que le devuelva el ultimo id de las Ligas
     * @param con la conexion
     * @return el id de la ultima liga como int
     * @throws SQLException si se da alguna excepcion en SQL
     */
    public static int obtainLastLeagueID(Connection con) throws SQLException{
        int idLeague = getLastLeagueID(con);
        return idLeague;
    }
    /**
     * Pide a la clase DBMatchSet que le devuelva los id de los MatchSet
     * correspondientes al id de la liga que se le envia
     * @param idLeague el id de la liga
     * @param con la conexion
     * @return un ArrayList con los id
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> obtainMatchSetsID(int idLeague,Connection con) throws SQLException{
        ArrayList<Integer> matchSetsID = getMatchSetsID(idLeague,con);
        
        return matchSetsID;
    }
    /**
     * Pide a la clase DBGame que le devuelva un ArrayList con los id de los Game
     * correspondientes al MatchSet que se le pasa
     * @param matchSetId el id del MatchSet al que corresponden
     * @param con la conexion
     * @return un ArrayList de int con los id de los Game
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> obtainGamesID(int matchSetId,Connection con) throws SQLException{
        ArrayList<Integer> gamesId= getGamesID(matchSetId,con);
        
        return gamesId;
    }
    /**
     * Pide a la clase DBGameResult los id de los equipos que participan en el partido del cual se facilita el id
     * @param gameID el id del partido
     * @param con la conexion
     * @return un ArrayList con los id de los equipos
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> obtainGameTeamID(int gameID,Connection con) throws SQLException{
        ArrayList<Integer> teamID = getGameTeamID(gameID,con);
        return teamID;
    }
    /**
     * Pide a la clase DBGameResult los resultados de cada equipo que participa en el partido del cual se facilita el id
     * @param gameID el id del partido
     * @param con la conexion
     * @return un ArrayList con los resultados del partido
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> obtainScores(int gameID,Connection con) throws SQLException{
        ArrayList<Integer> scores = getScores(gameID,con);
        return scores;
    }
    /**
     * Pide a la clase DBTeam que le devuelva un objeto Team con la id que se le facilita
     * @param tid el id del equipo
     * @param con la conexion
     * @return un objeto Team con teamName y nationality
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static Team obtainTeam(int tid,Connection con) throws SQLException{
        Team team = getGameTeam(tid,con);
        return team;
    }
    /**
     * Pide a la clase DBGame que le devuelva la fecha en la que se juega el partido
     * @param id la id del Game
     * @param con la conexion
     * @return la fecha del partido
     * @throws SQLException  si se dal alguna excepcion SQL
     */
    public static Date obtainGameDate(int id,Connection con) throws SQLException{
        Date date = getGameDate(id,con);
        return date;

    }
    /**
     * Llama a la clase DBTeamOwner para que inserte un TeamOwner
     * @param username el nombre de usuario
     * @param password la contraseña
     * @param fullName nombre completo
     * @param telephone numero de telefono
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertTeamOwner(String username, char[] password, String fullName, String telephone, Connection con) throws SQLException {
        DBTeamOwner.insertDBTeamOwner(username, password, fullName, telephone, con);
    }
    /**
     * Llama a la clase DBTeamOwner para que elimine un TeamOwner
     * @param username el nombre de usuario
     * @param password la contraseña
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void deleteTeamOwner(String username, char[] password, Connection con) throws SQLException {
        DBTeamOwner.deleteDBTeamOwner(username, password, con);
    }
    /**
     * Llama a la clase DBTeamOwner para que actualice un TeamOwner
     * @param username el nombre de usuario actual
     * @param newUsername el nuevo nombre de usuario
     * @param password la contraseña
     * @param fullName el nombre completo
     * @param telephone el numero de telefono
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updateTeamOwner(String username, String newUsername, char[] password, String fullName, String telephone,Connection con) throws SQLException {
        DBTeamOwner.updateDBTeamOwner(username, newUsername, password, fullName, telephone, con);
    }
}
