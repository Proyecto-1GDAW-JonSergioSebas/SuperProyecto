/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class TeamOwner {

    /**
     * Creacion de los atributos fullName, telephone.
     */
    private String fullName;
    private String telephone;

    /**
     * Constructor vacio. (Para invocación por constructores de subclases,
     * típicamente implícito.)
     */
    public TeamOwner() {
    }

    /**
     * Constructor lleno
     *
     * @param fullName (requerido) el nombre del dueño del equipo
     * @param telephone (requerido) El telefono del dueño values 9 numbers
     */
    public TeamOwner(String fullName, String telephone) {
        this.fullName = fullName;
        this.telephone = telephone;
    }

    /**
     * Es un Get que devuelve el nombre del dueño del equipo
     *
     * @return fullName el nombre devuelto
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Es un Set que establece el nombre del dueño
     *
     * @param fullName el nombre del dueño establecido
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Es un Get que devuelve el telefono del dueño
     *
     * @return telephone devuelve el numero de telefono
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Es un Set que establece el telefono
     *
     * @param telephone es un numero de telefono establecido
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
