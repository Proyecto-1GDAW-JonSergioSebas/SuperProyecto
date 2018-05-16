/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

/**
 * Esta clase contiene los metodos y constructores de los objetos TeamSax
 * @author Sergio Zulueta
 */
public class TeamSax {

    /**
     * Creacion de los atributos name, points.
     */
    private String name;
    private int points;

    /**
     * Constructor vacio. (Para invocación por constructores de subclases,
     * típicamente implícito.)
     */
    public TeamSax() {
    }

    /**
     * Constructor lleno
     * @param name (requerido) Nombre recibido del XML
     * @param points (requerido) Puntos recibidos del XML
     */
    public TeamSax(String name, int points) {
        this.name = name;
        this.points = points;
    }

    /**
     * Es un Get que devuelve el nombre leido en el XML.
     * 
     * @return name devuelve el name
     */
    public String getName() {
        return name;
    }

    /**
     * Es un Set que establece el nombre leido en el XML.
     * 
     * @param name establece el name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Es un Get que devuelve los puntos leidos en el XML.
     * 
     * @return points devuelve los points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Es un Get que establece los puntos leidos en el XML.
     * 
     * @param points establece los points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Para sacar por pantalla los datos.
     * 
     * @return un String con los datos de TeamSax
     */
    @Override
    public String toString() {
        return "TeamSax{" + "name=" + name + ", points=" + points + '}';
    }

}
