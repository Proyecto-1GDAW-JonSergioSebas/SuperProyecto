/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

import java.util.ArrayList;

/**
 * Esta clase contiene los metodos y constuctores de los objetos MatchSet
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
    private ArrayList<Game> games = new ArrayList<>();

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

    /**
     * Funcion que añade partido a partidos.
     * 
     * @param game el objeto Game a añadir
     */
    public void addGame(Game game) {
        games.add(game);
    }

    /**
     * Para devolver la lista de games.
     *
     * @return un ArrayList con los Game contenidos en este MatchSet
     */
    public ArrayList<Game> myGames() {
        return games;
    }

    /**
     * para sacar por pantalla los datos.
     * @return un String con los datos del MatchSet
     */
    @Override
    public String toString() {
        return "MatchSet{" + "games=" + games + '}';
    }

}
