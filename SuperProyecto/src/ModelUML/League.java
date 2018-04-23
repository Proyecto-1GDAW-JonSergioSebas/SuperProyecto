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
public class League {

    /**
     * Creacion de los atributos leagueName, matchsets.
     */
    private String leagueName;
    private ArrayList<MatchSet> matchsets;

    /**
     * Constructor lleno
     * 
     * @param leagueName (requerido) Nombre de la liga
     * @param matchsets (requerido) Lista de los partidos
     */
    public League(String leagueName, ArrayList<MatchSet> matchsets) {
        this.leagueName = leagueName;
        this.matchsets = matchsets;
    }

    /**
     * Es un Get que devuelve el nombre de la liga
     * 
     * @return leagueName el nombre de la liga que devuelve
     */
    public String getLeagueName() {
        return leagueName;
    }

    /**
     * Es un Set que establece el nombre de la liga
     * 
     * @param leagueName el nombre de la liga establecido
     */
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    /**
     * Es un Get que devuelve los partidos que se juegan
     * 
     * @return matchsets devuelve los partidos que se juegan
     */
    public ArrayList<MatchSet> getMatchsets() {
        return matchsets;
    }

    /**
     * Es un Set que establece los partidos que se juegan
     * 
     * @param matchsets los partidos que se juegan establecidos
     */
    public void setMatchsets(ArrayList<MatchSet> matchsets) {
        this.matchsets = matchsets;
    }

}
