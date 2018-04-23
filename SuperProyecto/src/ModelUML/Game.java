/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

import java.util.Calendar;

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
    private Calendar dateTime;

    /**
     * Constructor lleno
     *
     * @param team1 (requerido) Equipo 1 que juega
     * @param team2 (requerido) Equipo 2 que juega
     * @param score1 (requerido) Puntos del primer equipo
     * @param score2 (requerido) Puntos del segundo equipo
     * @param dateTime (requerido) Horario en el que juegan
     */
    public Game(Team team1, Team team2, int score1, int score2, Calendar dateTime) {
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
    public Game(Team team1, Team team2, Calendar dateTime) {
        this.team1 = team1;
        this.team2 = team2;
        this.dateTime = dateTime;
    }

    /**
     * 
     * @return 
     */
    public Team getTeam1() {
        return team1;
    }

    /**
     * 
     * @param team1 
     */
    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    /**
     * 
     * @return 
     */
    public Team getTeam2() {
        return team2;
    }

    /**
     * 
     * @param team2 
     */
    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    /**
     * 
     * @return 
     */
    public int getScore1() {
        return score1;
    }

    /**
     * 
     * @param score1 
     */
    public void setScore1(int score1) {
        this.score1 = score1;
    }

    /**
     * 
     * @return 
     */
    public int getScore2() {
        return score2;
    }

    /**
     * 
     * @param score2 
     */
    public void setScore2(int score2) {
        this.score2 = score2;
    }

    /**
     * 
     * @return 
     */
    public Calendar getDateTime() {
        return dateTime;
    }

    /**
     * 
     * @param dateTime 
     */
    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

}
