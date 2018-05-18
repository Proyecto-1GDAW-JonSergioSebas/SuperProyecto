/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superproyecto;

import static DB.DBController.createConnection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.ViewController;
import DB.DBController;
import static DB.DBController.createGames;
import static DB.DBController.createLeague;
import static DB.DBController.createMatchSet;
import static DB.DBController.obtainGameDate;
import static DB.DBController.obtainGameTeamID;
import static DB.DBController.obtainGamesID;
import static DB.DBController.obtainLastLeagueID;
import static DB.DBController.obtainMatchSetsID;
import static DB.DBController.obtainTeam;
import static DB.DBController.teams;
import ModelUML.DBUser;
import ModelUML.Game;
import ModelUML.MatchSet;
import ModelUML.Player;
import ModelUML.Team;
import ModelUML.TeamOwner;
import Parser.DOMParserLeague;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

/**
 * Esta es la madre de todas las clases, la que las sujeta en el sitio
 *
 * @author Jon Maneiro
 * @author Sebastián Zawisza
 * @author Sergio Zulueta
 * @version %I% %G%
 * @since 1.0
 */
public class SuperProyecto {

    /**
     * Abre la ventana de Login, de la cual nacen todas las demás.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*vvNO MODIFICAR ESTOvv*/
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(SuperProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            /*^^NO MODIFICAR ESTO^^*/
            if (getAllLeagueNames().size() != 0) {
                DOMParserLeague.executeDOMLeague();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SuperProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SuperProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        ViewController.login();
    }

    /**
     * Crea el calendario para la liga actual, dando a la base de datos los
     * datos para la creacion de League, MatchSets, Game y GameResult.
     *
     * @throws ParseException when the parse fails
     * @param leaguename nombre de la liga para la cual se va a crear el
     * calendario
     * @param date the date as String
     */
    public static void createCalendar(String leaguename, String date) throws ParseException {
        try {

            Connection con = createConnection();//Se crea la conexion..

            ArrayList<Team> completeTeams = teams(con);//Los equipos..
            //ArrayList<Calendar> dates;//Las fechas..WIP IGUAL NO ES NECESARIO
            ArrayList<Game> games;//Los partidos..
            ArrayList<MatchSet> league = new ArrayList();//Las jornadas..
            Calendar cal = Calendar.getInstance();//Instancia de calendario, para organizar los partidos
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            cal.setTime(sdf.parse(date));
            if (completeTeams.size() % 2 != 0) { //Si el numero de equipos es impar añade un equipo fantasma

                completeTeams.add(new Team("Null", "Null"));
            }
            int numDays = (completeTeams.size() - 1);//Dias necesarios en el torneo o lo que sea
            int halfSize = completeTeams.size() / 2;//La mitad del tamaño de el ArrayList completo de equipos

            ArrayList<Team> teams = new ArrayList();//Añadir los equipos y eliminar el primero
            teams.addAll(completeTeams);
            /*  for(Team t:completeTeams){
                System.out.println(t.getTeamName()+"\n");
            }
             */
            teams.remove(0);
            //Se crean las jornadas de "ida"
            for (int day = 0; day < numDays; day++) {//Se repetirá el numero de dias requeridos
                games = new ArrayList();

                int teamIdx = day % teams.size();//magic

                games.add(new Game(teams.get(teamIdx), completeTeams.get(0), cal.getTime()));//El primer partido de cada jornada

                for (int idx = 1; idx < halfSize; idx++) {
                    int firstTeam = (day + idx) % teams.size();
                    int secondTeam = (day + teams.size() - idx) % teams.size();
                    cal.add(Calendar.DATE, 1);

                    games.add(new Game(teams.get(firstTeam), teams.get(secondTeam), cal.getTime()));
                }
                cal.add(Calendar.DATE, 1);
                league.add(new MatchSet(games));
            }
            //Se crean las jornadas de "vuelta"
            int leagueSize = league.size();//Tamaño actual del ArrayList, antes de añadir las jornadas que faltan
            for (int h = 0; h < leagueSize; h++) {//Este pedacito de codigo Recibe los partidos que ya hay y les da la vuelta a los equipos
                //para crear la estructura de ida y vuelta.
                MatchSet tempMatchSet = league.get(h);
                ArrayList<Game> tempGames = new ArrayList();
                for (Game g : tempMatchSet.getGames()) {
                    cal.add(Calendar.DATE, 1);
                    Team tempteam1 = g.getTeam2();
                    Team tempteam2 = g.getTeam1();
                    tempGames.add(new Game(tempteam1, tempteam2, cal.getTime()));
                }
                MatchSet tempms = new MatchSet(tempGames);
                league.add(tempms);
            }

            for (int x = 0; x < league.size(); x++) {
                for (Game mthc : league.get(x).getGames()) {
                    System.out.println(mthc.getTeam1().getTeamName() + "  " + mthc.getTeam2().getTeamName());
                }
            }

            //Ahora creamos la Liga en la Base de Datos
            createLeague(leaguename, con);

            //Insertamos las jornadas
            int x = 0;
            ArrayList<Integer> matchsetsid = new ArrayList();
            while (x < league.size()) {

                matchsetsid.add(createMatchSet(leaguename, con));

                x++;
            }

            //Insertamos los juegos
            int y = 0;

            for (MatchSet m : league) {
                for (Game gm : m.getGames()) {

                    createGames(gm, matchsetsid.get(y), con);

                }
                y++;
            }

            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SuperProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SuperProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Busca la cuenta con los parametros introducidos y devuelve un int
     * dependiendo del tipo de cuenta. Usado por el Login.
     *
     * @param username El usuario de la cuenta.
     * @param password La contraseña de la cuenta.
     * @return El tipo de cuenta.
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepción SQL
     */
    public static byte getAccountType(String username, char[] password) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        int type = DB.DBController.getAccountType(username, String.valueOf(password), con);
        con.close();
        return (byte) type;
    }

    /**
     * Pide el id de la ultima liga
     *
     * @param con la conexion
     * @return el id de la ultima liga como int
     * @throws ClassNotFoundException No se encuentra la clase en la conexion
     * @throws SQLException si se da alguna excepcion en SQL
     */
    public static int askLastLeagueID(Connection con) throws ClassNotFoundException, SQLException, SQLException, SQLException {

        int idLeague = obtainLastLeagueID(con);

        return idLeague;

    }

    /**
     * Pide los id de los MatchSets correspondientes a la liga
     *
     * @param idLeague el id de la liga
     * @param con la conexion
     * @return un ArrayList con los id de los MatchSet correspondientes
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> askMatchSetsID(int idLeague, Connection con) throws SQLException {
        ArrayList<Integer> matchSetsID = obtainMatchSetsID(idLeague, con);

        return matchSetsID;
    }

    /**
     * Recoge los datos necesario para crear un objeto MatchSet en relacion a la
     * id que se le envie
     *
     * @param flip si se tiene que dar la vuelta al equipo o no
     * @param matchSetId el id del MatchSet a crear
     * @param con la conexion
     * @return un MatchSet
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static MatchSet createMatchSets(boolean flip, int matchSetId, Connection con) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> gameID = obtainGamesID(matchSetId, con);
        ArrayList<Game> games = new ArrayList();

        int x = 0;
        for (Integer id : gameID) {
            ArrayList<Integer> teamID = obtainGameTeamID(id, con);
            ArrayList<Integer> compScores = new ArrayList();
            compScores.add(DBController.getTeamGameScore(id, teamID.get(0), con));
            compScores.add(DBController.getTeamGameScore(id, teamID.get(1), con));
            ArrayList<Integer> scores = new ArrayList();
            ArrayList<Team> teams = new ArrayList();
            if (flip) {
                for (Integer tid : teamID) {
                    teams.add(obtainTeam(tid, con));

                }
                scores.add(compScores.get(0));
                scores.add(compScores.get(1));
            } else {
                teams.add(obtainTeam(teamID.get(1), con));
                teams.add(obtainTeam(teamID.get(0), con));
                scores.add(compScores.get(1));
                scores.add(compScores.get(0));
            }
            games.add(new Game(teams.get(0), teams.get(1), scores.get(0), scores.get(1), obtainGameDate(id, con)));
            x++;
        }
        MatchSet tempMatch = new MatchSet(games);
        return tempMatch;
    }

    /**
     * Recoge los id de todos los Game que haya dentro de la League cuyo id se
     * envia
     *
     * @param idLeague el id de la League
     * @param con la conexion
     * @return un ArrayList de Integer con las id de los Game
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> askAllGamesID(int idLeague, Connection con) throws SQLException {
        ArrayList<Integer> matchSetsID = obtainMatchSetsID(idLeague, con);
        ArrayList<Integer> allGamesID = new ArrayList();
        for (Integer id : matchSetsID) {
            ArrayList<Integer> tempGamesID = obtainGamesID(id, con);
            for (Integer di : tempGamesID) {
                allGamesID.add(di);
            }
        }
        return allGamesID;
    }

    /**
     * Selecciona todos los User de la base de datos
     *
     * @return un ArrayList de User que contiene todos los User de la base de
     * datos
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<DBUser> selectAllDBUsers() throws SQLException, ClassNotFoundException {

        Connection con = createConnection();
        ArrayList<DBUser> allDBUsers = DBController.selectAllDBUsers(con);
        con.close();
        return allDBUsers;
    }

    /**
     * Inserta un DBUser en la base de datos
     *
     * @param username el nombre de usuario
     * @param password la contraseña
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase en la conexion
     */
    public static void insertDBUser(String username, char[] password) throws SQLException, ClassNotFoundException {
        Connection con = createConnection();
        DBController.insertDBDBUser(username, password, con);
        con.close();
    }

    /**
     * Elimina un DBUser de la base de datos
     *
     * @param username el nombre de usuario
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase en la conexion
     */
    public static void deleteDBUser(String username) throws SQLException, ClassNotFoundException {
        Connection con = createConnection();
        DBController.deleteDBDBUser(username, con);
        con.close();
    }

    /**
     * Cambia los datos de un usuario
     *
     * @param newUsername nuevo nombre de usuario, a insertar
     * @param oldUsername viejo nombre de usuario, para identificar
     * @param password contraseña, a insertar
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase en la conexion
     *
     */
    public static void updateDBUser(String newUsername, String oldUsername, char[] password) throws SQLException, ClassNotFoundException {
        Connection con = createConnection();
        DBController.updateDBUser(newUsername, oldUsername, password, con);
        con.close();
    }

    /**
     * Cambia el nombre de usuario de un usuario
     *
     * @param newUsername nuevo nombre de usuario, a insertar
     * @param oldUsername viejo nombre de usuario, para identificar
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase en la conexion
     *
     */
    public static void updateDBUser(String newUsername, String oldUsername) throws SQLException, ClassNotFoundException {
        Connection con = createConnection();
        DBController.updateDBUser(newUsername, oldUsername, con);
        con.close();
    }

    /**
     * Inserta un TeamOwner en la Base de datos
     *
     * @param username nombre de usuario
     * @param password contraseña
     * @param fullName nombre completo
     * @param telephone numero de telefono
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertTeamOwner(String username, char[] password, String fullName, String telephone) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        DBController.insertTeamOwner(username, password, fullName, telephone, con);
        con.close();

    }

    /**
     * Elimina un TeamOwner de la Base de datos
     *
     * @param username el nombre de usuario
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void deleteTeamOwner(String username) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        DBController.deleteTeamOwner(username, con);
        con.close();
    }

    /**
     * Actualiza un TeamOwner de la Base de datos
     *
     * @param username el nombre de usuario actual
     * @param newUsername el nuevo nombre de usuario
     * @param password la contraseña
     * @param fullName el nombre completo
     * @param telephone numero de telefono
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updateTeamOwner(String username, String newUsername, char[] password, String fullName, String telephone) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        DBController.updateTeamOwner(username, newUsername, password, fullName, telephone, con);
        con.close();
    }

    /**
     * Inserta un Player en la base de datos
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param salary salario
     * @param email email
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertPlayer(String fullName, String nickname, BigDecimal salary, String email) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        DBController.insertPlayer(fullName, nickname, salary, email, con);
        con.close();
    }

    /**
     * Inserta un Player en la base de datos on equipo
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param salary salario
     * @param email email
     * @param teamname nombre del equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertPlayerT(String fullName, String nickname, BigDecimal salary, String email, String teamname) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        int teamid = DBController.getTeamID(teamname, con);
        DBController.insertPlayerT(fullName, nickname, salary, email, teamid, con);
        con.close();
    }

    /**
     * Elimina un Player de la base de datos
     *
     * @param fullName nombre compelto
     * @param nickname nickname
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void deletePlayer(String fullName, String nickname) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        DBController.deletePlayer(fullName, nickname, con);
        con.close();
    }

    /**
     * Actualiza un Player en la base de datos y le quita el equipo
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param oldnickname antiguo nickname
     * @param salary salario
     * @param email email
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updatePlayerNT(String fullName, String nickname, String oldnickname, BigDecimal salary, String email) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        DBController.updatePlayerNT(fullName, nickname, oldnickname, salary, email, con);
        con.close();
    }

    /**
     * Actualiza un Player de la base de datos y le cambia el equipo
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param oldnickname antiguo nickname
     * @param salary salario
     * @param email email
     * @param teamname nombre del equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updatePlayerT(String fullName, String nickname, String oldnickname, BigDecimal salary, String email, String teamname) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        int teamid = DBController.getTeamID(teamname, con);
        DBController.updatePlayerT(fullName, nickname, oldnickname, salary, email, teamid, con);
        con.close();
    }

    /**
     * Actualiza un Player de la base de datos
     *
     * @param fullName nombre completo
     * @param nickname nickame
     * @param oldnickname antiguo nickname
     * @param salary salario
     * @param email email
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updatePlayer(String fullName, String nickname, String oldnickname, BigDecimal salary, String email) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        DBController.updatePlayer(fullName, nickname, oldnickname, salary, email, con);
        con.close();
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Users
     *
     * @return La lista de Users
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<DBUser> selectDBUsers() throws SQLException, ClassNotFoundException {
        ArrayList arry = new ArrayList();
        Connection con = createConnection();
        arry = DBController.selectDBUsers(con);
        con.close();
        return arry;
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Admins
     *
     * @return La lista de Admins
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<TeamOwner> selectDBOwners() throws SQLException, ClassNotFoundException {
        ArrayList arry = new ArrayList();
        Connection con = createConnection();
        arry = DBController.selectDBOwners(con);
        con.close();
        return arry;
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Players
     *
     * @return La lista de Players
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<Player> selectDBPlayers() throws SQLException, ClassNotFoundException {
        ArrayList arry = new ArrayList();
        Connection con = createConnection();
        arry = DBController.selectDBPlayers(con);
        con.close();
        return arry;
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Teams
     *
     * @return La lista de Teams
     * @throws SQLException s se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<Team> selectDBTeams() throws SQLException, ClassNotFoundException {
        ArrayList arry = new ArrayList();
        Connection con = createConnection();
        arry = DBController.selectDBTeams(con);
        con.close();
        return arry;
    }

    /**
     * Inserta un Team en la base de datos sin nacionalidad
     *
     * @param teamname el nombre del equipo
     * @param teamownername el nombre del dueño del equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertTeam(String teamname, String teamownername) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        int teamownerid = DBController.getTeamOwnerID(teamownername, con);
        DBController.insertTeam(teamname, teamownerid, con);
        con.close();
    }

    /**
     * Inserta un Team en la base de datos
     *
     * @param teamname el nombre del equipo
     * @param nationality la nacionalidad
     * @param teamownername el nombre del dueño del equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertTeam(String teamname, String nationality, String teamownername) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        int teamownerid = DBController.getTeamOwnerID(teamownername, con);
        DBController.insertTeam(teamname, nationality, teamownerid, con);
        con.close();
    }

    /**
     * Elimina un Team de la base de datos
     *
     * @param teamname el nombre del equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void deleteTeam(String teamname) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        DBController.deleteTeam(teamname, con);
        con.close();
    }

    /**
     * Actualiza un Team de la base de datos sin nacionalidad
     *
     * @param teamname el nombre del equipo
     * @param newTeamname el nuevo nombre del equipo
     * @param newTeamownername el nombre del nuevo dueño del equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updateTeam(String teamname, String newTeamname, String newTeamownername) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        int teamownerid = DBController.getTeamOwnerID(newTeamownername, con);
        DBController.updateTeam(teamname, newTeamname, teamownerid, con);
        con.close();
    }

    /**
     * Actualiza un Team de la base de datos
     *
     * @param teamname el nombre del equipo
     * @param newTeamname el nuevo nombre del equipo
     * @param newNationality la nueva nacionalidad
     * @param newTeamownername el nombre del nuevo dueño del equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updateTeam(String teamname, String newTeamname, String newNationality, String newTeamownername) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        int teamownerid = DBController.getTeamOwnerID(newTeamownername, con);
        DBController.updateTeam(teamname, newTeamname, newNationality, teamownerid, con);
        con.close();
    }

    /**
     * Devuelve un ResultSet que contiene la clasificacion
     *
     * @param leagueid el id de la liga de la cual se quiere la clasificacion
     * @param con la conexion
     * @return un ResultSet con la clasificacion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ResultSet getClassification(int leagueid, Connection con) throws SQLException {
        ResultSet rs = DBController.getClassification(leagueid, con);
        return rs;
    }

    /**
     * Obtiene los nombres de todas las ligas
     *
     * @return un ArrayList de String con los nombres de las ligas
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<String> getAllLeagueNames() throws SQLException, ClassNotFoundException {
        Connection con = DBController.createConnection();
        ArrayList<String> leaguenames = DBController.getAllLeagueNames(con);
        con.close();
        return leaguenames;
    }

    /**
     * Obtiene el id de la liga de la cual se le pasa el nombre
     *
     * @param leaguename el nombre de la liga
     * @return el id de la liga
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static int getLeagueID(String leaguename) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        int x = DBController.getLeagueID(leaguename, con);
        con.close();
        return x;
    }

    /**
     * Devuelve un ArrayList con los id de los MatchSets de una liga
     *
     * @param leaguenum el id de la liga
     * @return un ArrayList con los id de los MatchSets
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> getLeagueMatchSetsID(int leaguenum) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        ArrayList<Integer> tempmatchnum = DBController.obtainMatchSetsID(leaguenum, con);
        con.close();
        return tempmatchnum;
    }

    /**
     * Obtiene los Game que estan en un MatchSet del cual se pasa el id
     *
     * @param leaguenum el id de la liga
     * @param matchSetnum el id del MatchSet
     * @return un ArrayList con los games del MatchSet
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Game> getMatchSetGames(int leaguenum, int matchSetnum) throws ClassNotFoundException, SQLException {
        Connection con = createConnection();
        ArrayList<Game> matchSetGames = DBController.getMatchSetGames(leaguenum, matchSetnum, con);
        con.close();
        return matchSetGames;
    }

    /**
     * Coge de la base de datos el Date más elevado última liga
     *
     * @return devuelve un objeto Date
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static Date getLeagueEndDate() throws SQLException, ClassNotFoundException {
        Connection con = createConnection();
        Date date = DBController.getLeagueEndDate(con);
        con.close();
        return date;
    }      
    

    /**
     * Coge todos los Game con todos sus datos que se correspondan con el ID del
     * Matchset
     *
     * @param matchSetId el ID del matchset
     * @return un treemap de Games, en el que la key es el ID del juego
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static TreeMap<Integer, Game> getGames(int matchSetId) throws SQLException, ClassNotFoundException {
        Connection con = createConnection();
        TreeMap<Integer, Game> games = DBController.getGames(matchSetId, con);
        con.close();
        return games;
    }

    /**
     * Introduce a la base de datos la información contenida en el TreeMap
     *
     * @param games el TreeMap con todos los juegos, puntuaciones, y equipos
     * @throws SQLException si se da alguna excepcion SLQ
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static void setGames(TreeMap<Integer, Game> games) throws SQLException, ClassNotFoundException {
        Connection con = createConnection();
        DBController.setGames(games, con);
        con.close();
    }

    /**
     * Método que verifica si la última liga ha terminado, y si lo ha hecho,
     * desbloquea los equipos.
     *
     * @throws SQLException si ocurre un error de SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static void updateLastLeagueStatus() throws SQLException, ClassNotFoundException {
        Connection con = createConnection();
        DBController.updateLastLeagueStatus(con);
        con.close();
    }
    /**
     * Devuelve un ArrayList con los Team
     * @return un ArrayList de Team
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Team> selectDBTeamsOG() throws ClassNotFoundException, SQLException {
        ArrayList arry = new ArrayList();
        Connection con = createConnection();
        arry = DBController.selectDBTeamsOG(con);
        con.close();
        return arry;
    }
}
