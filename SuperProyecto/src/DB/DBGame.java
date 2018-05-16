/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import ModelUML.Game;
import ModelUML.Team;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class DBGame {

    /**
     * Introduce el juego con la jornada a la que pertenece y la fecha de ese
     * partido
     *
     * @param gm El juego
     * @param matchsetnum el numero de jornada
     * @param con la conexion
     * @throws SQLException hay una excepcion SQL
     */
    public static int insertGame(Game gm, int matchsetnum, Connection con) throws SQLException {
        int gamenum = -1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date gameday = new Date(gm.getDateTime().getTime());
        Statement sta = con.createStatement();
        sta.executeUpdate("INSERT INTO GAME(MATCHSET,DATE_TIME) VALUES(" + matchsetnum + ",TO_DATE('" + gameday + "','YYYY-MM-DD'))");
        sta.close();
        Statement sto = con.createStatement();
        ResultSet resul = sto.executeQuery("SELECT ID_GA FROM GAME WHERE MATCHSET = " + matchsetnum + " AND DATE_TIME = TO_DATE('" + gameday + "','YYYY-MM-DD')");
        while (resul.next()) {
            gamenum = resul.getInt("ID_GA");
        }
        resul.close();
        sta.close();
        return gamenum;
    }

    /**
     * Coge los ID de los Game que correspondan con el ID de MatchSet que se le
     * pasa
     *
     * @param matchSetId el id del matchSet al que corresponden
     * @param con la conexion
     * @return ArrayList de int con los ID de los Game
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ArrayList<Integer> getGamesID(int matchSetId, Connection con) throws SQLException {
        ArrayList<Integer> gamesID = new ArrayList();
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT * FROM GAME WHERE MATCHSET = " + matchSetId);
        while (resul.next()) {
            gamesID.add(resul.getInt(1));
        }
        resul.close();
        sta.close();
        return gamesID;
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
        TreeMap<Integer, Game> games = new TreeMap();
        Statement sta = con.createStatement();
        ResultSet rs1 = sta.executeQuery("SELECT * FROM GAME WHERE MATCHSET = " + matchSetId); //seleccionamos todos los partidos correspondientes a la jornada
        while (rs1.next()) {
            games.put(rs1.getInt(1), new Game());
        }
        rs1.close();

        games.forEach((i, g) -> { //por cada partido hacemos lo siguiente:
            try {
                ArrayList<Integer> teamids = new ArrayList(); //en este bloque obtenemos las IDs de los dos equipos que juegan en el partido
                ArrayList<Integer> scores = new ArrayList(); //y ya que estamos aquí, también las puntuaciones preexistentes
                ResultSet rs2 = sta.executeQuery("SELECT TEAM, NVL(SCORE, -1) FROM GAME_RESULT WHERE GAME = " + i); //ya que java toma nulos numericos como 0, uso un NVL para que la puntuación de -1 si no se ha introducido, para manejarlo luego a nivel de interfaz
                while (rs2.next()) {
                    teamids.add(rs2.getInt(1));
                    scores.add(rs2.getInt(2));
                }
                rs2.close();

                ArrayList<Team> teams = new ArrayList(); //teniendo estas IDs, obtenemos el resto de datos de los equipos
                teamids.forEach(e -> {
                    try {
                        ResultSet rs3 = sta.executeQuery("SELECT TEAM_NAME, NATIONALITY FROM TEAM WHERE ID_TM = " + e);
                        while (rs3.next()) {
                            teams.add(new Team(rs3.getString(1), rs3.getString(2))); //y con ellos, creamos objetos Team
                        }
                        rs3.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DBGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                g.setTeam1(teams.get(0)); //añadimos los Team al Game
                g.setTeam2(teams.get(1));
                g.setScore1(scores.get(0)); //y luego las puntuaciones
                g.setScore2(scores.get(1));

            } catch (SQLException ex) {
                Logger.getLogger(DBGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        con.close();
        return games; //no me fastidies onegai
    }

    /**
     * Introduce a la base de datos la información contenida en el TreeMap
     *
     * @param games el TreeMap con todos los juegos, puntuaciones, y equipos
     * @param con la conección
     */
    public static void setGames(TreeMap<Integer, Game> games, Connection con) throws SQLException {
        Statement st = con.createStatement();
        games.forEach((k, v) -> {
            try {
                if (v.getScore1() != -1) {
                    st.executeUpdate("UPDATE GAME_RESULT SET SCORE = " + v.getScore1() + " WHERE GAME = " + k + " AND TEAM IN (SELECT ID_TM FROM TEAM WHERE TEAM_NAME = '" + v.getTeam1().getTeamName() + "')");
                }
                if (v.getScore2() != -1) {
                    st.executeUpdate("UPDATE GAME_RESULT SET SCORE = " + v.getScore2() + " WHERE GAME = " + k + " AND TEAM IN (SELECT ID_TM FROM TEAM WHERE TEAM_NAME = '" + v.getTeam2().getTeamName() + "')");
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        st.close();
        con.close();
    }

    /**
     * Coge de la base de datos el Date de cuando se celebra el partido
     *
     * @param id el id del partido
     * @param con la conexion
     * @return devuelve un objeto Date
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static Date getGameDate(int id, Connection con) throws SQLException {

        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT DATE_TIME FROM GAME WHERE ID_GA=" + id + "");
        while (resul.next()) {
            Date date = resul.getDate(1);
            return date;
        }
        return null; //si esto puede llegar a suceder, ya tenemos un problema
    }

    /**
     * Coge de la base de datos el Date más elevado de la liga correspondiente
     *
     * @param leagueId el ID de la liga
     * @param con la conexion
     * @return devuelve un objeto Date
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static Date getLeagueEndDate(int leagueId, Connection con) throws SQLException {
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("select max(date_time) from game where matchset in (select id_ms from matchset where league = " + leagueId + ")");
        while (resul.next()) {
            Date date = resul.getDate(1);
            return date;
        }
        return null;
    }
}
