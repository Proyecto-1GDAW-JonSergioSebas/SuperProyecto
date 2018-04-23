/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

import java.util.ArrayList;

/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class Team {

    /**
     * Creacion de los atributos teamName, nationality, teamOwner, players.
     */
    private String teamName;
    private String nationality;
    private TeamOwner teamOwner;
    private ArrayList<Player> players;

    /**
     * Constructor lleno
     *
     * @param teamName (requerido) Nombre del equipo
     * @param nationality (requerido) Nacionalidad del equipo
     * @param teamOwner (requerido) Dueño del equipo
     * @param players (requerido) Jugadores del equipo
     */
    public Team(String teamName, String nationality, TeamOwner teamOwner, ArrayList<Player> players) {
        this.teamName = teamName;
        this.nationality = nationality;
        this.teamOwner = teamOwner;
        this.players = players;
    }

    /**
     * Es un Get que devuelve el teamName
     *
     * @return teamName el teamName que se devuelve
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Es un Set que establece el teamName
     *
     * @param teamName el teamName establecido
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Es un Get que devuelve la nationality
     *
     * @return nationality la nationality que se devuelve
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Es un Set que establece la nationality
     *
     * @param nationality la nationality establecida
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Es un Get que devuelve el nombre del dueño del equipo
     *
     * @return teamOwner el nombre del dueño que se devuelve
     */
    public TeamOwner getTeamOwner() {
        return teamOwner;
    }

    /**
     * Es un Set que establece el nombre del dueño del equipo
     *
     * @param teamOwner el nombre del dueño establecido
     */
    public void setTeamOwner(TeamOwner teamOwner) {
        this.teamOwner = teamOwner;
    }

    /**
     * Es un Get que devuelve la lista de jugadores del equipo
     *
     * @return players la lista de jugadores que se devuelve
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Es un Set que establece la lista de jugadores del equipo
     *
     * @param players la lista de jugadores establecida
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

}
