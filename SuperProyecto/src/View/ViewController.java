/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import ModelUML.DBUser;
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
import superproyecto.SuperProyecto;

/**
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
     */
    public static void user(boolean child) {
        User user = new User(child);
        user.setVisible(true);
    }

    /**
     * Abre la ventana de Owner.
     */
    public static void owner(boolean child) {
        Owner owner = new Owner(child);
        owner.setVisible(true);
    }

    /**
     * Abre la ventana de Admin.
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
     * @param pw la contraseña
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase en la conexion
     */
    public static void deleteUser(String us) throws SQLException, ClassNotFoundException {
        SuperProyecto.deleteDBUser(us);
    }

    public static void selectUsers() {

    }

    /**
     * Actualiza un DBUser de la base de datos
     *
     * @param us el nombre de usuario
     * @param pw la contraseña
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase en la conexion
     */
    public static void updateUser(String us, char[] pw) throws SQLException, ClassNotFoundException {
        SuperProyecto.updateDBuser(us, pw);
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
     * @throws SQLException
     */
    public static ArrayList<DBUser> selectDBUsers() throws SQLException, ClassNotFoundException {
        return SuperProyecto.selectDBUsers();
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Admins
     *
     * @return La lista de Admins
     * @throws SQLException
     */
    public static ArrayList<TeamOwner> selectDBOwners() throws SQLException, ClassNotFoundException {
        return SuperProyecto.selectDBOwners();
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Players
     *
     * @return La lista de Players
     * @throws SQLException
     */
    public static ArrayList<Player> selectDBPlayers() throws SQLException, ClassNotFoundException {
        return SuperProyecto.selectDBPlayers();
    }

    /**
     * Realiza una consulta a la base de datos y devuelve todos los Teams
     *
     * @return La lista de Teams
     * @throws SQLException
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
    
    public static ArrayList<MatchSet> executeSaxParserLeague(){
        ArrayList<MatchSet> temparry = SAXParserLeague.executeSAXLeague();
        
        return temparry;
    }
    /**
     * Obtiene los nombres de todas las ligas
     * @return un ArrayList de String con los nombres de las ligas
     * @throws SQLException si se da alguna excepcion SQL
     * @throws ClassNotFoundException si no se encuentra la clase
     */
    static ArrayList<String> getLeagueNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> leaguenames = SuperProyecto.getAllLeagueNames();
        return leaguenames;
    }
}
