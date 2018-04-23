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
public class MatchSet {

    /**
     * Creacion del atributo games.
     */
    private ArrayList<Game> games;

    /**
     * Constructor lleno
     *
     * @param games (requerido) partidos que se van a disputar y los que se han
     * disputado
     */
    public MatchSet(ArrayList<Game> games) {
        this.games = games;
    }

    /**
     * Es un Get que devuelve los partidos que se van a jugar y los que se han
     * jugado
     *
     * @return games devuelve los partidos que se van a jugar y los que han
     * jugado
     */
    public ArrayList<Game> getGames() {
        return games;
    }

    /**
     * Es un Set que establece los partidos que van a jugar y los que se han
     * jugado
     *
     * @param games establece los partidos que se van a jugar y los que se han
     * jugado
     */
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

}
