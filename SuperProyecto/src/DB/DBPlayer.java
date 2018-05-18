/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import static DB.DBController.createConnection;
import ModelUML.Player;
import View.ViewController;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Esta clase gestiona las acciones necesarias en labase de datos sobre los
 * objetos Player
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class DBPlayer {

    /**
     * Realiza una consulta para devolver los jugadores pertenecientes al equipo
     * del cual le pasamos la id
     *
     * @param team el nombre del equipo del cual hay que buscar los jugadores
     * @param con la conexion
     * @param with si es con, o sin el equipo
     * @return ArrayList con los jugadores
     * @throws SQLException hay una excepcion SQL
     */
    public static ArrayList<Player> getPlayers(String team, Connection con, boolean with) throws SQLException {
        Statement est = con.createStatement();
        ResultSet rest;
        if (with) {
            rest = est.executeQuery("SELECT * FROM PLAYER WHERE TEAM = (SELECT ID_TM FROM TEAM WHERE TEAM_NAME = '" + team + "')");
        } else {
            rest = est.executeQuery("SELECT * FROM PLAYER WHERE TEAM IS NULL");
        }
        ArrayList<Player> players = new ArrayList();
        while (rest.next()) {
            players.add(new Player(rest.getString(2), rest.getString(3), rest.getBigDecimal(4), rest.getString(5)));
        }
        rest.close();
        est.close();
        return players;
    }

    /**
     * Inserta un Player en la base de datos sin equipo
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param salary salario del jugador
     * @param email email del jugador
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion en SQL
     */
    public static void insertPlayer(String fullName, String nickname, BigDecimal salary, String email, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("INSERT INTO PLAYER(FULL_NAME,NICKNAME,SALARY,EMAIL) "
                + "VALUES('" + fullName + "','" + nickname + "'," + salary.longValue() + ",'" + email + "')");
        sta.close();
    }

    /**
     * Inserta un Player en la base de datos con equipo
     *
     * @param fullName el nombre completo
     * @param nickname el nickname
     * @param salary el salario
     * @param email el email
     * @param teamid el id del equipo
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertPlayerT(String fullName, String nickname, BigDecimal salary, String email, int teamid, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("INSERT INTO PLAYER(FULL_NAME,NICKNAME,SALARY,EMAIL,TEAM)"
                + "VALUES('" + fullName + "','" + nickname + "'," + salary + ",'" + email + "'," + teamid + ")");
        sta.close();
    }

    /**
     * Elimina un Player de la base de datos
     *
     * @param fullName nombre completo
     * @param nickname nickname
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion en SQL
     */
    public static void deletePlayer(String fullName, String nickname, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("DELETE FROM PLAYER WHERE FULL_NAME='" + fullName + "' AND NICKNAME='" + nickname + "'");
        sta.close();
    }

    /**
     * Cambiar los datos de un Player
     *
     * @param fullName nombre completo actual
     * @param newFullName nuevo nombre completo
     * @param nickname nickname actual
     * @param newNickname nuevo nickname
     * @param newSalary nuevo salario
     * @param newEmail nuevo email
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion en SQL
     */
    public static void updatePlayer(String newFullName, String nickname, String newNickname, BigDecimal newSalary, String newEmail, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("UPDATE PLAYER SET FULL_NAME='" + newFullName + "',NICKNAME='" + newNickname + "',SALARY=" + newSalary.longValue() + ",EMAIL='" + newEmail + "'"
                + "WHERE  NICKNAME='" + nickname + "'");
        sta.close();
    }

    /**
     * Actualiza un Player y le cambia el valor de TEAM
     *
     * @param newFullName el nuevo nombre
     * @param nickname el nickname actual
     * @param newNickname el nuevo nickname
     * @param newSalary el salario
     * @param newEmail el nuevo email
     * @param newTeamID el id del nuevo equipo
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updatePlayerT(String newFullName, String nickname, String newNickname, BigDecimal newSalary, String newEmail, int newTeamID, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("UPDATE PLAYER SET FULL_NAME='" + newFullName + "',NICKNAME='" + newNickname + "',SALARY=" + newSalary.longValue() + ",EMAIL='" + newEmail + "',TEAM=" + newTeamID + ""
                + "WHERE  NICKNAME='" + nickname + "'");
        sta.close();
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
        Statement sta = con.createStatement();
        sta.executeUpdate("UPDATE PLAYER SET TEAM=" + DBController.searchTeam(newTeam, con) + "WHERE  NICKNAME='" + nickname + "'");
        sta.close();
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
        Statement sta = con.createStatement();
        sta.executeUpdate("UPDATE PLAYER SET TEAM = NULL WHERE NICKNAME='" + nickname + "'");
        sta.close();
    }

    /**
     * Actualiza un Player y le quita el TEAM
     *
     * @param newFullName nuevo nombre completo
     * @param nickname el nickname actual
     * @param newNickname el nuevo nickname
     * @param newSalary el nuevo salario
     * @param newEmail el nuevo email
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updatePlayerNT(String newFullName, String nickname, String newNickname, BigDecimal newSalary, String newEmail, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("UPDATE PLAYER SET FULL_NAME='" + newFullName + "',NICKNAME='" + newNickname + "',SALARY=" + newSalary.longValue() + ",EMAIL='" + newEmail + "',TEAM=" + "NULL" + ""
                + "WHERE  NICKNAME='" + nickname + "'");
        sta.close();
    }

    /**
     * Devuelve todos los Player
     *
     * @param con la conexion
     * @return un ArrayList con los Player
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Player> selectAllPlayers(Connection con) throws SQLException {
        ArrayList<Player> players = new ArrayList();
        ArrayList<Integer> teamIds = new ArrayList();
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT * FROM PLAYER");
        while (resul.next()) {
            players.add(new Player(resul.getString(2), resul.getString(3), resul.getBigDecimal(4), resul.getString(5)));
            teamIds.add(resul.getInt(6));
        }
        resul.close();
        sta.close();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setTeam(DBController.obtainTeam(teamIds.get(i), con));
        }
        return players;
    }
}
