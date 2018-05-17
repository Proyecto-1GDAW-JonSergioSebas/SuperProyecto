/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase gestiona las acciones necesarias en la base de datos sobre los
 * objetos League
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class DBLeague {

    /**
     * Crea la liga en la base de datos
     *
     * @param leaguename el nombre de la liga
     * @param con la conexion
     * @throws SQLException hay una excepcion SQL
     */
    public static void insertLeague(String leaguename, Connection con) throws SQLException {
        Statement est = con.createStatement();
        est.executeUpdate("INSERT INTO LEAGUE(LEAGUE_NAME) VALUES('" + leaguename + "')");
        est.close();
    }

    /**
     * Busca la liga y devuelve su id
     *
     * @param leaguename el nombre de la liga
     * @param con la conexion
     * @return devuelve el id de la liga
     * @throws SQLException hay una excepcion SQL
     */
    public static int askForLeague(String leaguename, Connection con) throws SQLException {
        if (leaguename.equalsIgnoreCase("Liga actual")) {
            int y = getLastLeagueID(con);
            return y;
        } else {
            Statement est = con.createStatement();
            ResultSet resul = est.executeQuery("SELECT ID_LG FROM LEAGUE WHERE LEAGUE_NAME='" + leaguename + "'");
            int temp = -1;
            while (resul.next()) {
                temp = resul.getInt("ID_LG");
            }
            resul.close();
            est.close();
            return temp;
        }
    }

    /**
     * Recoge el ultimo id de las ligas almacenadas en la base de datos y lo
     * devuelve
     *
     * @param con la conexion
     * @return el id de la ultima liga como int
     * @throws SQLException si se da alguna excepcion en SQL
     */
    public static int getLastLeagueID(Connection con) throws SQLException {
        int idLeague = 1;
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT * FROM LEAGUE");
        while (resul.next()) {

            idLeague = resul.getInt("ID_LG");

        }

        resul.close();
        sta.close();
        return idLeague;
    }

    /**
     * Obtiene los nombres de todas las ligas
     *
     * @param con la conexion
     * @return un ArrayList de String con los nombres de las ligas
     * @throws SQLException si se da alguna excepcion SQL
     */
    static ArrayList<String> getAllLeagueNames(Connection con) throws SQLException {
        ArrayList<String> leaguenames = new ArrayList();
        Statement sta = con.createStatement();
        ResultSet rst = sta.executeQuery("SELECT LEAGUE_NAME FROM LEAGUE");
        while (rst.next()) {

            leaguenames.add(rst.getString("LEAGUE_NAME"));

        }
        rst.close();
        sta.close();
        return leaguenames;
    }

    /**
     * Método que verifica si la última liga ha terminado, y si lo ha hecho,
     * desbloquea los equipos.
     *
     * @param con la conexión
     * @throws SQLException si ocurre un error de SQL
     */
    public static void updateLastLeagueStatus(Connection con) throws SQLException {
        int leagueId = getLastLeagueID(con);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT OVER FROM LEAGUE WHERE ID_LG = " + leagueId);
        boolean over = true;
        while (rs.next()) {
            over = rs.getInt(1) != 0;
        }
        if (!over) {
            boolean shouldBeOver = true;
            rs = st.executeQuery("SELECT MAX(DATE_TIME) FROM GAME WHERE MATCHSET IN (SELECT ID_MS FROM MATCHSET WHERE LEAGUE = " + leagueId + ")");
            while (rs.next()) {
                shouldBeOver = rs.getDate(1).before(Date.from(Instant.now()));
            }

            if (shouldBeOver) {
                st.executeUpdate("UPDATE LEAGUE SET OVER = 1 WHERE ID_LG = " + leagueId);
                st.executeUpdate("UPDATE TEAM SET BLOCKED = 0");
            }
        }
    }
}
