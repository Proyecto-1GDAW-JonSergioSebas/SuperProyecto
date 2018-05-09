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
     * Construcion del atributo games.
     */
    private ArrayList<Game> games;

    /**
     * Construtor lleno.
     *
     * @param games (requiered)partidos que se van a jugar y que se han jugado
     */
    public MatchSet(ArrayList<Game> games) {
        this.games = games;
    }

    /**
     * Constructor vacio. (Para invocación por constructores de subclases,
     * típicamente implícito.)
     */
    public MatchSet() {

    }

    /**
     * Es un Get que devuelve los partidos
     *
     * @return games devuelve los partidos
     */
    public ArrayList<Game> getGames() {
        return games;
    }

    /**
     * Es un Set que establece los partidos
     *
     * @param games establece los partidos
     */
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

}
