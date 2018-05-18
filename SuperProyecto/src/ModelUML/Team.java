/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

import java.util.ArrayList;

/**
 * Esta clase contiene los metodos y constructores de los objetos Team
 *
 * @author Sergio Zulueta
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
     * Constructor lleno
     *
     * @param teamName El nombre del equipo
     * @param teamOwner El nombre del dueño del equipo
     */
    public Team(String teamName, TeamOwner teamOwner) {
        this.teamName = teamName;
        this.teamOwner = teamOwner;
    }

    /**
     * Constructor lleno.
     *
     * @param teamName El nombre del equipo
     * @param nationality El nombre del dueño del equipo
     */
    public Team(String teamName, String nationality) {
        this.teamName = teamName;
        this.nationality = nationality;
    }

    /**
     * Constructor lleno.
     *
     * @param teamName El nombre del equipo
     * @param nationality La nacionalidad del equipo
     * @param teamOwner El nombre del dueño del equipo
     *
     */
    public Team(String teamName, TeamOwner teamOwner, String nationality) {
        this.teamName = teamName;
        this.teamOwner = teamOwner;
        this.nationality = nationality;
    }

    /**
     * Constructor vacio. (Para invocación por constructores de subclases,
     * típicamente implícito.)
     */
    public Team() {

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

    /**
     * Función que añade un jugador al equipo
     *
     * @param player el jugador a añadir
     */
    public void addPlayer(Player player) {
        if (players == null) {
            players = new ArrayList();
        }
        this.players.add(player);
    }

    /**
     * Para sacar por pantalla los datos.
     *
     * @return un String con los datos de Team
     */
    @Override
    public String toString() {
        return "Team{" + "teamName=" + teamName + '}';
    }

}
