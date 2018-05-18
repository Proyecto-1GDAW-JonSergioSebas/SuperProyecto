/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import ModelUML.Team;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Esta clase gestiona las acciones necesarias en la base de datos sobre los
 * objetos Team
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class DBTeam {

    /**
     * Devuelve los equipos con el nombre y la nacionalidad
     *
     * @param con la conexion
     * @throws ClassNotFoundException no se encuentra la clase
     * @throws SQLException hay una excepcion SQL
     * @return lista con los equipos
     */
    public static ArrayList<Team> getTeams(Connection con) throws ClassNotFoundException, SQLException {

        ArrayList<Team> teams = new ArrayList();
        ArrayList<Integer> owners = new ArrayList();
        Statement sent = con.createStatement();
        ResultSet resul = sent.executeQuery("SELECT * FROM TEAM WHERE BLOCKED = 1");
        while (resul.next()) {
            teams.add(new Team(resul.getString(2), resul.getString(3)));
            owners.add(resul.getInt(4));
        }
        for (int i = 0; i < teams.size(); i++) {
            teams.get(i).setTeamOwner(DBController.obtainTeamOwner(owners.get(i), con));
        }
        resul.close();
        sent.close();
        return teams;
    }

    /**
     * Busca el equipo obteniendo como dato el nombre del equipo
     *
     * @param teamname el nombre del equipo
     * @param con la conexion
     * @return in int con el id del equipo
     * @throws SQLException hay una excepcion SQL
     */
    public static int searchTeam(String teamname, Connection con) throws SQLException {
        int x = -1;
        Statement sent = con.createStatement();
        ResultSet resul = sent.executeQuery("SELECT ID_TM FROM TEAM WHERE TEAM_NAME='" + teamname + "'");
        while (resul.next()) {
            x = resul.getInt("ID_TM");
        }
        resul.close();
        sent.close();
        return x;

    }

    /**
     * Inserta un Team en la base de datos
     *
     * @param teamname el nombre del equipo
     * @param nationality la nacionalidad del equipo
     * @param ownerID el id del TeamOwner
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertTeam(String teamname, String nationality, int ownerID, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("INSERT INTO TEAM(TEAM_NAME,NATIONALITY,TEAM_OWNER)"
                + "VALUES('" + teamname + "','" + nationality + "'," + ownerID + ")");
        sta.close();
    }

    /**
     * Inserta un Team en la base de datos sin nacionalidad
     *
     * @param teamname el nombre del equipo
     * @param ownerID el id del TeamOwner
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void insertTeam(String teamname, int ownerID, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("INSERT INTO TEAM(TEAM_NAME,TEAM_OWNER)"
                + "VALUES('" + teamname + "'," + ownerID + ")");
        sta.close();
    }

    /**
     * Elimina un Team de la base de datos
     *
     * @param teamname el nombre del equipo
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void deleteTeam(String teamname, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("DELETE FROM TEAM WHERE TEAM_NAME='" + teamname + "'");
        sta.close();
    }

    /**
     * Actualiza un Team con los datos que se le pasan
     *
     * @param teamname el nombre actual del equipo
     * @param newTeamname nuevo nombre del equipo
     * @param newNationality nueva nacionalidad
     * @param newownerID nuevo id del TeamOwner
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updateTeam(String teamname, String newTeamname, String newNationality, int newownerID, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("UPDATE TEAM SET TEAM_NAME='" + newTeamname + "',NATIONALITY='" + newNationality + "',TEAM_OWNER=" + newownerID + " "
                + "WHERE TEAM_NAME='" + teamname + "'");
        sta.close();
    }

    /**
     * Actualiza un Team con los datos que se le pasan (sin nacionalidad)
     *
     * @param teamname el nombre actual del equipo
     * @param newTeamname nuevo nombre del equipo
     * @param newownerID nuevo id del TeamOwner
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static void updateTeam(String teamname, String newTeamname, int newownerID, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        sta.executeUpdate("UPDATE TEAM SET TEAM_NAME='" + newTeamname + "',TEAM_OWNER=" + newownerID + " "
                + "WHERE TEAM_NAME='" + teamname + "'");
        sta.close();
    }

    /**
     * Devuelve un ArrayList con todos los equipos
     *
     * @param con la conexion
     * @return un ArrayList con todos los equipos
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Team> selectAllTeams(Connection con) throws SQLException {
        ArrayList<Team> teams = new ArrayList();
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT TEAM_NAME,NATIONALITY FROM TEAM");
        while (resul.next()) {
            teams.add(new Team(resul.getString(1), resul.getString(2)));
        }
        resul.close();
        sta.close();
        return teams;
    }

    /**
     * Devuelve el equipo que corresponde al id que se le facilita
     *
     * @param tid el id del equipo
     * @param con la conexion
     * @return Un objeto Team con teamName y nationality
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static Team getGameTeam(int tid, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT * FROM TEAM WHERE ID_TM=" + tid + "");
        Team team = new Team();
        while (resul.next()) {
            team = new Team(resul.getString(2), resul.getString(3));
        }
        resul.close();
        sta.close();
        return team;
    }
}
