/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

/**
 * Esta clase contiene los metodos y constructores de los objetos DBUser
 *
 * @author Sergio Zulueta
 * @version %I% %G%
 * @since 1.0
 * @see Accounts
 */
public class DBUser extends Accounts {

    /**
     * Constructor vacio. (Para invocación por constructores de subclases,
     * típicamente implícito.)
     */
    public DBUser() {
    }

    /**
     * Constructor lleno
     *
     * @param userName (requerido) Nombre de usuario para acceder al programa
     * @param password (requerido) Contraseña delusuario para accedera al
     * programa
     */
    public DBUser(String userName, char[] password) {
        super.setUserName(userName);
        super.setPassword(password);
    }

}
