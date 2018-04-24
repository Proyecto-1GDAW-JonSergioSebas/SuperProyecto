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
public class Accounts {

    /**
     * Creacion del atributo userName.
     */
    private String userName;

    /**
     * Constructor vacio. (Para invocación por constructores de subclases,
     * típicamente implícito.)
     */
    public Accounts() {
    }

    /**
     * 
     * @param userName (requerido) Nombre de usuario para acceder al programa
     */
    public Accounts(String userName) {
        this.userName = userName;
    }

    /**
     * Es un Get que devuelve el nombre de usuario de las personas registradas
     * 
     * @return userName devuelve el nombre de usuario
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Es un Set que establece el nombre de usuario de las personas registradas
     * 
     * @param userName el nombre de usuario establecido
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
