/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import ModelUML.DBUser;
import ModelUML.Game;
import ModelUML.MatchSet;
import ModelUML.Player;
import ModelUML.Team;
import ModelUML.TeamOwner;
import Parser.SAXParserLeague;
import java.awt.Frame;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import superproyecto.SuperProyecto;

/**
 * Esta clase se encarga de hacer de intermediario entre las vistas y el resto
 * del programa, es decir, ninguna función de las vistas saldrá de ellas sin
 * pasar por esta clase.
 *
 * @author Sebastián Zawisza
 * @author Sergio Zulueta
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class ViewController {

    /**
     * Abre la ventana de Login.
     */
    public static void login() {
        Login login = new Login();
        login.setVisible(true);
    }

    /**
     * Abre la ventana de User.
     *
     * @param child Generado automáticamente
     * @throws java.sql.SQLException si se da alguna excepcion SQL
     * @throws java.lang.ClassNotFoundException si no se encuentra la clase
     */
    public static void user(boolean child) throws SQLException, ClassNotFoundException {
        if (ViewController.getLeagueNames().size() == 0) {
            JOptionPane.showMessageDialog(null, "Se debe crear un calendario antes de poder acceder a esta funcionalidad.\nContacta con el administrador para mas información.");

        } else {
            User user = new User(child);
            user.setVisible(true);
        }
    }

    /**
     * Abre la ventana de Owner.
     *
     * @param child Generado automáticamente
     */
    public static void owner(boolean child, String username) {
        Owner owner = new Owner(child, username);
        owner.setVisible(true);
    }

    /**
     * Abre la ventana de Admin.
     *
     * @param child Generado automáticamente
     */
    public static void admin(boolean child) {
        Admin admin = new Admin(child);
        admin.setVisible(true);
    }

    /**
     * Abre la ventana del CRUD de usuario.
     *
     * @param f La ventana que ejecuta éste método.
     * @param mode Determina el modo de la ventana del CRUD, y la acción que
     * realiza.
     */
    public static void userCRUD(Frame f, byte mode) {
        UserCRUD uc = new UserCRUD(f, true, mode);
        uc.setVisible(true);
    }

    /**
     * Abre la ventana del CRUD de usuario.
     *
     * @param f La ventana que ejecuta éste método.
     * @param mode Determina el modo de la ventana del CRUD, y la acción que
     * realiza.
     */
    public static void ownerCRUD(Frame f, byte mode) {
        OwnerCRUD oc = new OwnerCRUD(f, true, mode);
        oc.setVisible(true);
    }

    /**
     * Abre la ventana del CRUD de usuario.
     *
     * @param f La ventana que ejecuta éste método.
     * @param mode Determina el modo de la ventana del CRUD, y la acción que
     * realiza.
     */
    public static void playerCRUD(Frame f, byte mode) {
        PlayerCRUD pc = new PlayerCRUD(f, true, mode);
        pc.setVisible(true);
    }

    /**
     * Abre la ventana del CRUD de usuario.
     *
     * @param f La ventana que ejecuta éste método.
     * @param mode Determina el modo de la ventana del CRUD, y la acción que
     * realiza.
     */
    public static void teamCRUD(Frame f, byte mode) {
        TeamCRUD tc = new TeamCRUD(f, true, mode);
        tc.setVisible(true);
    }

    /**
     * Abre la ventana de League
     *
     * @param f La ventana que ejecuta este metodo
     */
    public static void league(Frame f) {
        League league = new League(f, true);
        league.setVisible(true);
    }

    /**
     * Realiza una consulta a las cuentas en la base de datos con usuario y
     * contraseña. Devuelve un int representando al tipo de cuenta si existe, y
     * un 0 si no.
     *
     * @param username el nombre del usuario
     * @param password un array de caracteres que contiene la contraseña
     * @return el tipo de cuenta
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepción SQL
     */
    public static byte LoginAccountQuery(String username, char[] password) throws ClassNotFoundException, SQLException {
        return SuperProyecto.getAccountType(username, password);
    }

    /**
     * Inserta un DBUser en la base de datos
     *
     * @param us el nombre de usuario
     * @param pw la contraseña
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase en la conexion
     */
    public static void insertUser(String us, char[] pw) throws SQLException, ClassNotFoundException {
        SuperProyecto.insertDBUser(us, pw);
    }

    /**
     * Elimina un DBUser de la base de datos
     *
     * @param us el nombre de usuario
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase en la conexion
     */
    public static void deleteUser(String us) throws SQLException, ClassNotFoundException {
        SuperProyecto.deleteDBUser(us);
    }

    /**
     * Vacio
     */
    public static void selectUsers() {

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
        SuperProyecto.updateDBUser(newUsername, oldUsername, password);
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
        SuperProyecto.updateDBUser(newUsername, oldUsername);
    }

    /**
     * Llama a la funcion createCalendar ubicada en SuperProyecto
     *
     * @param leaguename el nombre de la liga
     * @param date la fecha de inicio
     * @throws ParseException si se da alguna excepcion a la hora de Parsear
     * @see superproyecto.SuperProyecto#createCalendar(java.lang.String,
     * java.lang.String)
     */
    public static void createCalendar(String leaguename, String date) throws ParseException {
        SuperProyecto.createCalendar(leaguename, date);
    }

    /**
     * Inserta un TeamOwner en la base de datos
     *
     * @param username el nombre de usuario
     * @param password la contraseña
     * @param fullName nombre completo
     * @param telephone numero de telefono
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion en SQL
     */
    static void insertTeamOwner(String username, char[] password, String fullName, String telephone) throws ClassNotFoundException, SQLException {
        SuperProyecto.insertTeamOwner(username, password, fullName, telephone);
    }

    /**
     * Elimina un TeamOwner de la base de datos
     *
     * @param username el nombre de usuario
     * @param password la contraseña
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion en SQL
     */
    static void deleteTeamOwner(String username) throws ClassNotFoundException, SQLException {
        SuperProyecto.deleteTeamOwner(username);
    }

    /**
     * Actualiza un TeamOwner de la base de datos
     *
     * @param username el nombre de usuario actual
     * @param newUsername el nuevo nombre de usuario
     * @param password la contrasela
     * @param fullName el nombre completo
     * @param telephone numero de telefono
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    static void updateTeamOwner(String username, String newUsername, char[] password, String fullName, String telephone) throws ClassNotFoundException, SQLException {
        SuperProyecto.updateTeamOwner(username, newUsername, password, fullName, telephone);
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
    static void insertPlayer(String fullName, String nickname, BigDecimal salary, String email) throws ClassNotFoundException, SQLException {
        SuperProyecto.insertPlayer(fullName, nickname, salary, email);
    }

    /**
     * Inserta un Player en la base de datos con Team
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param salary salario
     * @param email email
     * @param teamname nombre de equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    static void insertPlayerT(String fullName, String nickname, BigDecimal salary, String email, String teamname) throws ClassNotFoundException, SQLException {
        SuperProyecto.insertPlayerT(fullName, nickname, salary, email, teamname);
    }

    /**
     * Elimina un Player de la base de datos
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQl
     */
    static void deletePlayer(String fullName, String nickname) throws ClassNotFoundException, SQLException {
        SuperProyecto.deletePlayer(fullName, nickname);
    }

    /**
     * Actualiza un Player de la base de datos y le quita el Team
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param oldnickname antiguo nickname
     * @param salary salario
     * @param email email
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    static void updatePlayerNT(String fullName, String nickname, String oldnickname, BigDecimal salary, String email) throws ClassNotFoundException, SQLException {
        SuperProyecto.updatePlayerNT(fullName, nickname, oldnickname, salary, email);
    }

    /**
     * Actualiza un Player de la base de datos y le cambia el Team
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
    static void updatePlayerT(String fullName, String nickname, String oldnickname, BigDecimal salary, String email, String teamname) throws ClassNotFoundException, SQLException {
        SuperProyecto.updatePlayerT(fullName, nickname, oldnickname, salary, email, teamname);
    }

    /**
     * Actualiza un Player de la base de datos
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param oldnickname antiguo nickname
     * @param salary salario
     * @param email email
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQl
     */
    static void updatePlayer(String fullName, String nickname, String oldnickname, BigDecimal salary, String email) throws ClassNotFoundException, SQLException {
        SuperProyecto.updatePlayer(fullName, nickname, oldnickname, salary, email);
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Users
     *
     * @return La lista de Users
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<DBUser> selectDBUsers() throws SQLException, ClassNotFoundException {
        return SuperProyecto.selectDBUsers();
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Admins
     *
     * @return La lista de Admins
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<TeamOwner> selectDBOwners() throws SQLException, ClassNotFoundException {
        return SuperProyecto.selectDBOwners();
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Players
     *
     * @return La lista de Players
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<Player> selectDBPlayers() throws SQLException, ClassNotFoundException {
        return SuperProyecto.selectDBPlayers();
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Teams
     *
     * @return La lista de Teams
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static ArrayList<Team> selectDBTeams() throws SQLException, ClassNotFoundException {
        return SuperProyecto.selectDBTeams();
    }

    /**
     * Inserta un Team en la base de datos sin nacionalidad
     *
     * @param teamname el nombre del equipo
     * @param teamownername el nombre del dueño del equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    static void insertTeam(String teamname, String teamownername) throws ClassNotFoundException, SQLException {
        SuperProyecto.insertTeam(teamname, teamownername);
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
    static void insertTeam(String teamname, String nationality, String teamownername) throws ClassNotFoundException, SQLException {
        SuperProyecto.insertTeam(teamname, nationality, teamownername);
    }

    /**
     * Elimina un team de la base de datos
     *
     * @param teamname el nombre del equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    static void deleteTeam(String teamname) throws ClassNotFoundException, SQLException {
        SuperProyecto.deleteTeam(teamname);
    }

    /**
     * Actualiza un Team de la base de datos sin nacionalidad
     *
     * @param teamname el nombre del equipo
     * @param newTeamname el nuevo nombre del equipo
     * @param newTeamownername el nombre del nuevo dueño de equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    static void updateTeam(String teamname, String newTeamname, String newTeamownername) throws ClassNotFoundException, SQLException {
        SuperProyecto.updateTeam(teamname, newTeamname, newTeamownername);
    }

    /**
     * Actualiza un Team de la base de datos
     *
     * @param teamname el nombre del equipo
     * @param newTeamname el nuevo nombre del equipo
     * @param newNationality la nueva nacionalidad
     * @param newTeamownername el nombre del nuevo dueño de equipo
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    static void updateTeam(String teamname, String newTeamname, String newNationality, String newTeamownername) throws ClassNotFoundException, SQLException {
        SuperProyecto.updateTeam(teamname, newTeamname, newNationality, newTeamownername);
    }

    /**
     * Ejecuta el SAXPArser de liga y devuelve un arrayList con todos los
     * matchsets, y games
     *
     * @return un ArrayList con todos los MatchSet de una liga
     */
    public static ArrayList<MatchSet> executeSaxParserLeague() {
        ArrayList<MatchSet> temparry = SAXParserLeague.executeSAXLeague();

        return temparry;
    }

    /**
     * Obtiene los nombres de todas las ligas
     *
     * @return un ArrayList de String con los nombres de las ligas
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    static ArrayList<String> getLeagueNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> leaguenames = SuperProyecto.getAllLeagueNames();
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
    static int getLeagueNum(String leaguename) throws ClassNotFoundException, SQLException {
        int x = SuperProyecto.getLeagueID(leaguename);
        return x;
    }

    /**
     * Devuelve un ArrayList con los id de los MatchSets de la liga la cual se
     * le pasa el id
     *
     * @param leaguenum el id de la liga
     * @return un ArrayList con los id de los MatchSets
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    static ArrayList<Integer> getLeagueMatchSetsID(int leaguenum) throws ClassNotFoundException, SQLException {
        ArrayList<Integer> temparry = SuperProyecto.getLeagueMatchSetsID(leaguenum);
        return temparry;
    }

    /**
     * Obtiene todos los Game que haya dentro de un MatchSet
     *
     * @param leaguenum el id de la liga
     * @param matchSetnum el id del MatchSet
     * @return un ArrayList con Game
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    static ArrayList<Game> getMatchSetGames(int leaguenum, int matchSetnum) throws ClassNotFoundException, SQLException {
        ArrayList<Game> matchSetGames = SuperProyecto.getMatchSetGames(leaguenum, matchSetnum);
        return matchSetGames;
    }

    /**
     * Abre la ventana de matchsetUpdate
     *
     * @param f la ventana que se ejecuta
     * @param matchset el id del MatchSet correspondiente
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void matchsetUpdate(Frame f, int matchset) throws ClassNotFoundException, SQLException {
        MatchsetUpdate mu = new MatchsetUpdate(f, true, getGames(matchset));
        mu.setVisible(true);
    }

    /**
     * Coge de la base de datos el Date más elevado última liga
     *
     * @return devuelve un objeto Date
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static Date getLeagueEndDate() throws SQLException, ClassNotFoundException {
        return SuperProyecto.getLeagueEndDate();
    }

    /**
     * Coge todos los Game con todos sus datos que se correspondan con el ID del
     * Matchset
     *
     * @param id el ID del matchset
     * @return un treemap de Games, en el que la key es el ID del juego
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static TreeMap<Integer, Game> getGames(int id) throws SQLException, ClassNotFoundException {
        return SuperProyecto.getGames(id);
    }

    /**
     * Introduce a la base de datos la información contenida en el TreeMap
     *
     * @param games el TreeMap con todos los juegos, puntuaciones, y equipos
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static void setGames(TreeMap<Integer, Game> games) throws SQLException, ClassNotFoundException {
        SuperProyecto.setGames(games);
    }

    /**
     * Método que verifica si la última liga ha terminado, y si lo ha hecho,
     * desbloquea los equipos.
     *
     * @throws SQLException si ocurre un error de SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    public static void updateLastLeagueStatus() throws SQLException, ClassNotFoundException {
        SuperProyecto.updateLastLeagueStatus();
    }

    /**
     * Devuelve los equipos no bloqueados que se corresponden a un dueño Si el
     * string de username es vacío, devuelve todos los equipos
     *
     * @param ownerUsername el nombre de usuario del dueño
     * @throws ClassNotFoundException no se encuentra la clase
     * @throws SQLException hay una excepcion SQL
     * @return lista con los equipos
     */
    public static ArrayList<Team> getTeamsByOwner(String ownerUsername) throws ClassNotFoundException, SQLException {
        return SuperProyecto.getTeamsByOwner(ownerUsername);
    }

    /**
     * Método que bloquea un equipo basado en su nombre.
     *
     * @param teamName nombre del equipo a bloquear
     * @throws SQLException si ocurre un error de SQL
     * @throws java.lang.ClassNotFoundException si no se encuentra la clase
     */
    public static void blockTeam(String teamName) throws SQLException, ClassNotFoundException {
        SuperProyecto.blockTeam(teamName);
    }

    /**
     * Pide a la clase DBPlayer un ArrayList de Player
     *
     * @param team el nombre del equipo del cual queremos los jugadores
     * @param with si es con, o sin el equipo
     * @return ArrayList de los jugadores
     * @throws SQLException hay una excepcion SQL
     * @see DBPlayer#getPlayers(int, java.sql.Connection)
     */
    public static ArrayList<Player> getPlayers(String team, boolean with) throws SQLException, ClassNotFoundException {
        return SuperProyecto.getPlayers(team, with);
    }

    /**
     * Le cambia el valor de TEAM a un PLAYER
     *
     * @param nickname el nickname actual
     * @param newTeam el id del nuevo equipo
     * @throws SQLException si se da alguna excepcion SQL
     * @throws java.lang.ClassNotFoundException si no se encuentra la clase
     */
    public static void updatePlayerT(String nickname, String newTeam) throws SQLException, ClassNotFoundException {
        SuperProyecto.updatePlayerT(nickname, newTeam);
    }

    /**
     * Actualiza el Player y cambial el valor de TEAM al ser eliminado de un
     * equipo
     *
     * @param nickname
     * @throws SQLException
     */
    public static void updatePlayerTeamEmpty(String nickname) throws SQLException, ClassNotFoundException {
        SuperProyecto.updatePlayerTeamEmpty(nickname);
    }
}
