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
     * Creacion de los atributos teanName, nationality, teamOwner, players.
     */
    private String teamName;
    private String nationality;
    private TeamOwner teamOwner;
    private ArrayList<Player> players;

    /**
     *
     * @param teamName (requerido) El nombre del equipo
     * @param nationality (requerido) La nacionalidad del equipo
     * @param teamOwner (requerido) El nombre del dueño del equipo
     * @param players (requerido) El nombre de los jugadores en el equipo
     */
    public Team(String teamName, String nationality, TeamOwner teamOwner, ArrayList<Player> players) {
        this.teamName = teamName;
        this.nationality = nationality;
        this.teamOwner = teamOwner;
        this.players = players;
    }
    /**
     * Constructor que requiere nombre y nacinalidad
     * @param teamName nombre del equipo
     * @param nationality nacionalidad del equipo
     */
    public Team(String teamName, String nationality) {
        this.teamName = teamName;
        this.nationality = nationality;
    }
    
    

    /**
     * Es un Get que devuelve el nombre del equipo
     *
     * @return teamName devuelve el nombre del equipo
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Es un Set que establece el nombre del equpio
     *
     * @param teamName establece el nombre del equipo
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Es un Get que devuelve la nacionalidad del equipo
     *
     * @return nationality devuelve la nacionalidad del equipo
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Es un Set que establece la nacionalidad del equipo
     *
     * @param nationality establece la nacionalidad del equipo
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Es un Get que devuelve el nombre del dueño del equipo
     *
     * @return teamOwner devuelve el nombre del dueño
     */
    public TeamOwner getTeamOwner() {
        return teamOwner;
    }

    /**
     * Es un Set que establece el nombre del dueño del equipo
     *
     * @param teamOwner establece el nombre del dueño
     */
    public void setTeamOwner(TeamOwner teamOwner) {
        this.teamOwner = teamOwner;
    }

    /**
     * Es un Get que devuelve los jugadores en el equipo
     *
     * @return players devuelve los jugadores del equipo
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Es un Set que establece los jugadores del equipo
     *
     * @param players establece los jugadores del equipo
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

}
