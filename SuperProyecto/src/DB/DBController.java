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
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

/**
 * Esta clase se encarga de gestionar las relaciones con las clases que se
 * encuentren fuera del paquete DB y las que se encuentran dentro
 *
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
     * @return la conexion a la base de datos
     *
     * @throws ClassNotFoundException no encuentra la clase
     * @throws SQLException hay una excepcion SQL
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
     * @param team el nombre del equipo del cual queremos los jugadores
     * @param con la conexion
     * @param with si es con, o sin el equipo
     *
     * @return ArrayList de los jugadores
     * @throws SQLException hay una excepcion SQL
     * @see DBPlayer#getPlayers(int, java.sql.Connection)
     */
    public static ArrayList<Player> getPlayers(String team, Connection con, boolean with) throws SQLException {
        ArrayList<Player> players = DBPlayer.getPlayers(team, con, with);
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
     * @return el id de el MatchSet que ha sido creado
     * @throws SQLException hay una excepcion SQL
     */
    public static int createMatchSet(String leaguename, Connection con) throws SQLException {
        int temp = askForLeague(leaguename, con);
        int lastmatchsetid = insertMatchSet(temp, con);
        return lastmatchsetid;
    }

    /**
     * Crea los juegos, primero enviando a DBGame los datos del juego en si,
     * despues pide las id de los equipos que participan en ese partido y se los
     * pasa a otro metodo que introduce cada equipo en el Game_Resul
     * correspondiente
     *
     * @param gm el juego
     * @param matchsetnum el id de la jornada
     * @param con la conexion
     * @throws SQLException hay una excepcion SQL
     */
    public static void createGames(Game gm, int matchsetnum, Connection con) throws SQLException {
        int gamenum = insertGame(gm, matchsetnum, con);
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
     * @throws java.sql.SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<DBUser> selectAllDBUsers(Connection con) throws SQLException {
        return DBDBUser.selectAllUsers(con);
    }

    /**
     * Inserta un DBUser en la base de datos
     *
     * @param username el nombre de usuario
     * @param password la contraseña
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertDBDBUser(String username, char[] password, Connection con) throws SQLException {
        DBDBUser.insertDBUser(username, password, con);
    }

    /**
     * Elimina un DBUser de la base de datos
     *
     * @param username el nombre de usuario
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void deleteDBDBUser(String username, Connection con) throws SQLException {
        DBDBUser.deleteDBUser(username, con);
    }

    /**
     * Cambia los datos de un usuario
     *
     * @param newUsername nuevo nombre de usuario, a insertar
     * @param oldUsername viejo nombre de usuario, para identificar
     * @param password contraseña, a insertar
     * @param con la conexion
     * @throws SQLException si hay alguna excepcion SQL
     */
    public static void updateDBUser(String newUsername, String oldUsername, char[] password, Connection con) throws SQLException {
        DBDBUser.updateDBUser(newUsername, oldUsername, password, con);
    }

    /**
     * Cambia el nombre de usuario de un usuario
     *
     * @param newUsername nuevo nombre de usuario, a insertar
     * @param oldUsername viejo nombre de usuario, para identificar
     * @param con la conexion
     * @throws SQLException si hay alguna excepcion SQL
     */
    public static void updateDBUser(String newUsername, String oldUsername, Connection con) throws SQLException {
        DBDBUser.updateDBUser(newUsername, oldUsername, con);
    }

    /**
     * Pide a la clase DBLeague que le devuelva el ultimo id de las Ligas
     *
     * @param con la conexion
     * @return el id de la ultima liga como int
     * @throws SQLException si se da alguna excepcion en SQL
     */
    public static int obtainLastLeagueID(Connection con) throws SQLException {
        int idLeague = getLastLeagueID(con);
        return idLeague;
    }

    /**
     * Pide a la clase DBMatchSet que le devuelva los id de los MatchSet
     * correspondientes al id de la liga que se le envia
     *
     * @param idLeague el id de la liga
     * @param con la conexion
     * @return un ArrayList con los id
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> obtainMatchSetsID(int idLeague, Connection con) throws SQLException {
        ArrayList<Integer> matchSetsID = getMatchSetsID(idLeague, con);

        return matchSetsID;
    }

    /**
     * Pide a la clase DBGame que le devuelva un ArrayList con los id de los
     * Game correspondientes al MatchSet que se le pasa
     *
     * @param matchSetId el id del MatchSet al que corresponden
     * @param con la conexion
     * @return un ArrayList de int con los id de los Game
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> obtainGamesID(int matchSetId, Connection con) throws SQLException {
        ArrayList<Integer> gamesId = getGamesID(matchSetId, con);

        return gamesId;
    }

    /**
     * Pide a la clase DBGameResult los id de los equipos que participan en el
     * partido del cual se facilita el id
     *
     * @param gameID el id del partido
     * @param con la conexion
     * @return un ArrayList con los id de los equipos
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> obtainGameTeamID(int gameID, Connection con) throws SQLException {
        ArrayList<Integer> teamID = getGameTeamID(gameID, con);
        return teamID;
    }

    /**
     * Pide a la clase DBGameResult los resultados de cada equipo que participa
     * en el partido del cual se facilita el id
     *
     * @param gameID el id del partido
     * @param con la conexion
     * @return un ArrayList con los resultados del partido
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> obtainScores(int gameID, Connection con) throws SQLException {
        ArrayList<Integer> scores = getScores(gameID, con);
        return scores;
    }

    /**
     * Pide a la clase DBTeam que le devuelva un objeto Team con la id que se le
     * facilita
     *
     * @param tid el id del equipo
     * @param con la conexion
     * @return un objeto Team con teamName y nationality
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static Team obtainTeam(int tid, Connection con) throws SQLException {
        Team team = getGameTeam(tid, con);
        return team;
    }

    /**
     * Pide a la clase DBGame que le devuelva la fecha en la que se juega el
     * partido
     *
     * @param id la id del Game
     * @param con la conexion
     * @return la fecha del partido
     * @throws SQLException si se dal alguna excepcion SQL
     */
    public static Date obtainGameDate(int id, Connection con) throws SQLException {
        Date date = getGameDate(id, con);
        return date;

    }

    /**
     * Llama a la clase DBTeamOwner para que inserte un TeamOwner
     *
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
     *
     * @param username el nombre de usuario
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void deleteTeamOwner(String username, Connection con) throws SQLException {
        DBTeamOwner.deleteDBTeamOwner(username, con);
    }

    /**
     * Llama a la clase DBTeamOwner para que actualice un TeamOwner
     *
     * @param username el nombre de usuario actual
     * @param newUsername el nuevo nombre de usuario
     * @param password la contraseña
     * @param fullName el nombre completo
     * @param telephone el numero de telefono
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updateTeamOwner(String username, String newUsername, char[] password, String fullName, String telephone, Connection con) throws SQLException {
        DBTeamOwner.updateDBTeamOwner(username, newUsername, password, fullName, telephone, con);
    }

    /**
     * Llama a la clase DBPlayer para que inserte un Player en la base de datos
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param salary salario
     * @param email email
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertPlayer(String fullName, String nickname, BigDecimal salary, String email, Connection con) throws SQLException {
        DBPlayer.insertPlayer(fullName, nickname, salary, email, con);
    }

    /**
     * Obtiene el ID del Team cuyo nombre se introduce
     *
     * @param teamname el nombre del equipo
     * @param con la conexion
     * @return un int con el ID del equipo
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static int getTeamID(String teamname, Connection con) throws SQLException {
        int teamid = DBTeam.searchTeam(teamname, con);
        return teamid;
    }

    /**
     * LLama a la clase DBPlayer para que inserte un Player en la base de datos
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param salary salario
     * @param email email
     * @param teamid el id del equipo
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertPlayerT(String fullName, String nickname, BigDecimal salary, String email, int teamid, Connection con) throws SQLException {
        DBPlayer.insertPlayerT(fullName, nickname, salary, email, teamid, con);
    }

    /**
     * Llama a la clase DBPlayer para que elimine un Player de la base de datos
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void deletePlayer(String fullName, String nickname, Connection con) throws SQLException {
        DBPlayer.deletePlayer(fullName, nickname, con);
    }

    /**
     * Llama a la clase DBPlayer para que actualice un Player de la base de
     * datos
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param oldnickname el nickname antiguo
     * @param salary salario
     * @param email email
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updatePlayerNT(String fullName, String nickname, String oldnickname, BigDecimal salary, String email, Connection con) throws SQLException {
        DBPlayer.updatePlayerNT(fullName, oldnickname, nickname, salary, email, con);
    }

    /**
     * Llama a la clase DBPlayer para que actualice un Player de la base de
     * datos
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param oldnickname antiguo nickname
     * @param salary salario
     * @param email email
     * @param teamid el id del equipo al que se va a unir
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updatePlayerT(String fullName, String nickname, String oldnickname, BigDecimal salary, String email, int teamid, Connection con) throws SQLException {
        DBPlayer.updatePlayerT(fullName, oldnickname, nickname, salary, email, teamid, con);
    }

    /**
     * Llama a la clase DBPlayer para que actualice un Player de la base de
     * datos
     *
     * @param fullName nombre completo del Player
     * @param nickname nickname
     * @param oldnickname antiguo nickname
     * @param salary salario
     * @param email email
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updatePlayer(String fullName, String nickname, String oldnickname, BigDecimal salary, String email, Connection con) throws SQLException {
        DBPlayer.updatePlayer(fullName, oldnickname, nickname, salary, email, con);
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Users
     *
     * @param con la conexion
     * @return La lista de Users
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<DBUser> selectDBUsers(Connection con) throws SQLException {
        return DBDBUser.selectAllUsers(con);
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Admins
     *
     * @param con la conexion
     * @return La lista de Admins
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<TeamOwner> selectDBOwners(Connection con) throws SQLException {
        return DBTeamOwner.selectAllTeamOwners(con);
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Players
     *
     * @param con la conexion
     * @return La lista de Players
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Player> selectDBPlayers(Connection con) throws SQLException {
        return DBPlayer.selectAllPlayers(con);
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Teams
     *
     * @param con la conexion
     * @return La lista de Teams
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     *
     */
    public static ArrayList<Team> selectDBTeams(Connection con) throws SQLException, ClassNotFoundException {
        return DBTeam.getTeams(con);
    }

    /**
     * Devuelve la id de un TeamOwner cuyo nombre se facilita
     *
     * @param teamownername nombre del dueño
     * @param con la conexion
     * @return devuelve el id
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static int getTeamOwnerID(String teamownername, Connection con) throws SQLException {
        int x = DBTeamOwner.selectTeamOwnerID(teamownername, con);
        return x;
    }

    /**
     * Llama a la clase DBTeam para que inserte un Team en la base de datos sin
     * nacionalidad
     *
     * @param teamname nombre del equipo
     * @param teamownerid id del TeamOwner
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertTeam(String teamname, int teamownerid, Connection con) throws SQLException {
        DBTeam.insertTeam(teamname, teamownerid, con);
    }

    /**
     * LLama a la clase DBTeam para que inserte un Team en la base de datos
     *
     * @param teamname el nombre del equipo
     * @param nationality la nacionalidad
     * @param teamownerid el id del TeamOwner
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertTeam(String teamname, String nationality, int teamownerid, Connection con) throws SQLException {
        DBTeam.insertTeam(teamname, nationality, teamownerid, con);
    }

    /**
     * LLama a la clase DBTeam para que elimine un Team de la base de datos
     *
     * @param teamname el nombre del equipo
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void deleteTeam(String teamname, Connection con) throws SQLException {
        DBTeam.deleteTeam(teamname, con);
    }

    /**
     * Llama a la clase DBTeam para que actualice un Team de la base de datos
     * sin nacionalidad
     *
     * @param teamname el nombre del equipo
     * @param newTeamname nuevo nombre del equipo
     * @param teamownerid la id del TeamOwner
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updateTeam(String teamname, String newTeamname, int teamownerid, Connection con) throws SQLException {
        DBTeam.updateTeam(teamname, newTeamname, teamownerid, con);
    }

    /**
     * Llama a la clase DBTeam para que actualice un Team de la base de datos
     * sin nacionalidad
     *
     * @param teamname el nombre del equipo
     * @param newTeamname nuevo nombre del equipo
     * @param newNationality nueva nacionalidad
     * @param teamownerid el id del TeamOwner
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updateTeam(String teamname, String newTeamname, String newNationality, int teamownerid, Connection con) throws SQLException {
        DBTeam.updateTeam(teamname, newTeamname, newNationality, teamownerid, con);
    }

    /**
     * Obtiene la clasificacion en forma de un objeto ResultSet
     *
     * @param leagueid el id de la liga de la cual se quiere obtener la
     * clasificacion
     * @param con la conexion
     * @return un ResultSet con la clasificacion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ResultSet getClassification(int leagueid, Connection con) throws SQLException {
        ResultSet rs = DBProcedures.getClassification(leagueid, con);
        return rs;
    }

    /**
     *
     * Obtiene los nombres de todas las ligas
     *
     * @param con la conexion
     * @return devuelve un ArrayList con los nombres de todas las ligas
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<String> getAllLeagueNames(Connection con) throws SQLException {
        ArrayList<String> leaguenames = DBLeague.getAllLeagueNames(con);
        return leaguenames;
    }

    /**
     * Devuelve el ID de la liga de la cual se le pasa el nombre
     *
     * @param leaguename el nombre de la liga
     * @param con la conexion
     * @return devuelve un int con el id de la liga
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static int getLeagueID(String leaguename, Connection con) throws SQLException {
        int x = DBLeague.askForLeague(leaguename, con);
        return x;
    }

    /**
     * Obtiene los Game que hay dentro de un MatchSet del cual se pasa el ID
     *
     * @param leaguenum el id del League
     * @param matchSetnum el id del MatchSet
     * @param con la conexion
     * @return un ArrayList de Game con los Game que hay dentro de un MatchSet
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Game> getMatchSetGames(int leaguenum, int matchSetnum, Connection con) throws SQLException {
        ArrayList<Game> matchSetGames = new ArrayList();
        ArrayList<Integer> gamesID = getGamesID(matchSetnum, con);
        for (Integer e : gamesID) {
            Game tempgame = new Game();
            ArrayList<Integer> tempgameteamid = obtainGameTeamID(e.intValue(), con);
            tempgame.setTeam1(DBTeam.getGameTeam(tempgameteamid.get(0), con));
            tempgame.setScore1(DBGameResult.getTeamScore(e.intValue(), tempgameteamid.get(0), con));
            tempgame.setTeam2(DBTeam.getGameTeam(tempgameteamid.get(1), con));
            tempgame.setScore2(DBGameResult.getTeamScore(e.intValue(), tempgameteamid.get(1), con));
            matchSetGames.add(tempgame);
        }
        return matchSetGames;
    }

    /**
     * Coge de la base de datos el Date más elevado última liga
     *
     * @param con la conexion
     * @return devuelve un objeto Date
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static Date getLeagueEndDate(Connection con) throws SQLException {
        return DBGame.getLeagueEndDate((getLastLeagueID(con)), con);
    }

    /**
     * Coge todos los Game con todos sus datos que se correspondan con el ID del
     * Matchset
     *
     * @param matchSetId el ID del matchset
     * @param con la conexion
     * @return un treemap de Games, en el que la key es el ID del juego
     * @throws SQLException cuando caen rayos y truenos por todos los cielos
     */
    public static TreeMap<Integer, Game> getGames(int matchSetId, Connection con) throws SQLException {
        return DBGame.getGames(matchSetId, con);
    }

    /**
     * Introduce a la base de datos la información contenida en el TreeMap
     *
     * @param games el TreeMap con todos los juegos, puntuaciones, y equipos
     * @param con la conexión
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void setGames(TreeMap<Integer, Game> games, Connection con) throws SQLException {
        DBGame.setGames(games, con);
    }

    /**
     * Obtiene el Score de un Team dentro de un GameResult
     *
     * @param id el id del Game
     * @param teamID El id del Team
     * @param con la conexion
     * @return un Integer que contiene el numero de puntos que ha logrado un
     * equipo en un partido
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static Integer getTeamGameScore(Integer id, Integer teamID, Connection con) throws SQLException {
        int x = DBGameResult.getTeamScore(id, teamID, con);
        return x;
    }

    /**
     * Método que verifica si la última liga ha terminado, y si lo ha hecho,
     * desbloquea los equipos.
     *
     * @param con la conexión
     * @throws SQLException si ocurre un error de SQL
     */
    public static void updateLastLeagueStatus(Connection con) throws SQLException {
        DBLeague.updateLastLeagueStatus(con);
    }

  /**
     * Devuelve un ArrayList con el nombre y la nacionalidad
     * @param con la conexion
     * @return un ArrayList con los equipos
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList selectDBTeamsOG(Connection con) throws ClassNotFoundException, SQLException {
        return DBTeam.getTeamsOG(con);

    /**
     * Devuelve los equipos no bloqueados que se corresponden a un dueño Si el
     * string de username es vacío, devuelve todos los equipos
     *
     * @param con la conexion
     * @param ownerUsername el nombre de usuario del dueño
     * @throws ClassNotFoundException no se encuentra la clase
     * @throws SQLException hay una excepcion SQL
     * @return lista con los equipos
     */
    public static ArrayList<Team> getTeamsByOwner(Connection con, String ownerUsername) throws ClassNotFoundException, SQLException {
        return DBTeam.getTeamsByOwner(con, ownerUsername);
    }

    /**
     * Método que bloquea un equipo basado en su nombre.
     *
     * @param teamName nombre del equipo a bloquear
     * @param con la conexion
     * @throws SQLException si ocurre un error de SQL
     */
    public static void blockTeam(String teamName, Connection con) throws SQLException {
        DBTeam.blockTeam(teamName, con);
    }

    /**
     * Le cambia el valor de TEAM a un PLAYER
     *
     * @param nickname el nickname actual
     * @param newTeam el nombre del nuevo equipo
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updatePlayerT(String nickname, String newTeam, Connection con) throws SQLException {
        DBPlayer.updatePlayerT(nickname, newTeam, con);
    }

    /**
     * Devuelve el ID correspondiente con el nombre del equipo tomado como
     * parametro
     *
     * @param teamName nombre del equipo
     * @param con conexion
     * @return el ID del equipo
     * @throws SQLException si ocurre algun error de SQL
     */
    public static int searchTeam(String teamName, Connection con) throws SQLException {
        return DBTeam.searchTeam(teamName, con);
    }

    /**
     * Actualiza el Player y cambial el valor de TEAM al ser eliminado de un
     * equipo
     *
     * @param nickname
     * @param con
     * @throws SQLException
     */
    public static void updatePlayerTeamEmpty(String nickname, Connection con) throws SQLException {
        DBPlayer.updatePlayerTeamEmpty(nickname, con);
    }
}
