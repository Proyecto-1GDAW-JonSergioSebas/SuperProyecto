/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class Game {

    /**
     * Creacion de los atributos team1, team2, score1, score2, dateTime.
     */
    private Team team1;
    private Team team2;
    private int score1;
    private int score2;
    private Date dateTime;

    /**
     * Constructor lleno
     *
     * @param team1 (requerido) Equipo 1 que juega
     * @param team2 (requerido) Equipo 2 que juega
     * @param score1 (requerido) Puntos del primer equipo
     * @param score2 (requerido) Puntos del segundo equipo
     * @param dateTime (requerido) Horario en el que juegan
     */
    public Game(Team team1, Team team2, int score1, int score2, Date dateTime) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.dateTime = dateTime;
    }

    /**
     * Constructor lleno
     *
     * @param team1 (requerido) Equipo 1 que juega
     * @param team2 (requerido) Equipo 2 que juega
     * @param dateTime (requerido) Horario en el que juegan
     */
    public Game(Team team1, Team team2, Date dateTime) {
        this.team1 = team1;
        this.team2 = team2;
        this.dateTime = dateTime;
    }

    /**
     * Es un Get que devuelve el nombre del equipo 1
     *
     * @return team1  equipo devuelto
     */
    public Team getTeam1() {
        return team1;
    }

    /**
     * Es un Set que establece el nombre del equipo 1
     *
     * @param team1 establece el  equipo 1
     */
    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    /**
     * Es un Get que devuelve  equipo 2
     *
     * @return team2  equipo devuelto
     */
    public Team getTeam2() {
        return team2;
    }

    /**
     * Es un Set que establece el  equipo 2
     *
     * @param team2 establece el  equipo 2
     */
    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    /**
     * Es un Get que devuelve los puntos del equipo1
     *
     * @return score1 devuelve los puntos del equipo 1
     */
    public int getScore1() {
        return score1;
    }

    /**
     * Es un Set que establece los puntos del equipo 1
     *
     * @param score1 los puntos del equipo 1 establecidos
     */
    public void setScore1(int score1) {
        this.score1 = score1;
    }

    /**
     * Es un Get que devuelve los puntos del equipo 2
     *
     * @return score2 devuelve los puntos del equipo 2
     */
    public int getScore2() {
        return score2;
    }

    /**
     * Es un Set que establece los puntos del equipo 2
     *
     * @param score2 los puntos del equipo 2 establecidos
     */
    public void setScore2(int score2) {
        this.score2 = score2;
    }

    /**
     * Es un Get que devuelve la fecha del partido
     *
     * @return dateTime la fecha del partido devuelta
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Es un Set que establece la fecha del partido
     *
     * @param dateTime la fecha del partido devuelta
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

}
