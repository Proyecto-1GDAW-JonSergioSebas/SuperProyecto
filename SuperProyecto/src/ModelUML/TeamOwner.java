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
     * @param fullName (requerido) nombre del dueño
     * @param telephone (requerido) telefono del dueño valor de 9 digitos
     */
    public TeamOwner(String fullName, String telephone) {
        this.fullName = fullName;
        this.telephone = telephone;
    }

    /**
     * Es un Get que devuelve el fullName
     *
     * @return fullName el fullName que se devuelve
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Es un Set que establece el fullName
     *
     * @param fullName el fullName que se establece
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Es un Get que devuelve el telephone
     *
     * @return telephone el telephone que se devuelve
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Es un Set que establece el telephone
     *
     * @param telephone el telephone que se establece
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
