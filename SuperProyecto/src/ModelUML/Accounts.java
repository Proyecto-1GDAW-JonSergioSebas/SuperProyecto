/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelUML;

/**
 * Esta clase contiene los metodos y constructores de los objetos Accounts
 *
 * @author Sergio Zulueta
 * @version %I% %G%
 * @since 1.0
 */
public class Accounts {

    /**
     * Creacion del atributo userName y password.
     */
    private String userName;
    private char[] password;

    /**
     * Constructor vacio. (Para invocación por constructores de subclases,
     * típicamente implícito.)
     */
    public Accounts() {
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

    /**
     * Es un Set que establece la contraseña de las personas registradas
     *
     * @param password la contraseña establecida
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

    /**
     * Es un Get que devuelve la contraseña de las personas registradas
     *
     * @return devuelve la contraseña como array de caracteres
     */
    public char[] getPassword() {
        return password;
    }

}
