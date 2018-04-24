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
public class DBAdmin extends Accounts {

    /**
     * Constructor vacio. (Para invocación por constructores de subclases,
     * típicamente implícito.)
     */
    public DBAdmin() {
    }

    /**
     * Constructor lleno
     *
     * @param userName (requerido) Nombre de usuario para acceder al programa
     */
    public DBAdmin(String userName) {
        super(userName);
    }

}
