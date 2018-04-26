/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Frame;
import superproyecto.SuperProyecto;

/**
 * @author Sebastián Zawisza
 * @author Sergio Zulueta
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class ViewController {

    /**
     * Abre la ventana de Login.
     */
    public static void login() {
        Login login = new Login();
        login.setVisible(true);
    }

    /**
     * Abre la ventana de User.
     */
    public static void user() {
        Admin user = new Admin();
        user.setVisible(true);
    }

    /**
     * Abre la ventana de Owner.
     */
    public static void owner() {
        Owner owner = new Owner();
        owner.setVisible(true);
    }

    /**
     * Abre la ventana de Admin.
     */
    public static void admin() {
        User admin = new User();
        admin.setVisible(true);
    }

    /**
     * Abre la ventana del CRUD de usuario.
     *
     * @param f La ventana que ejecuta éste método.
     * @param mode Determina el modo de la ventana del CRUD, y la acción que
     * realiza.
     */
    public static void userCRUD(Frame f, byte mode) {
        UserCRUD uc = new UserCRUD(f, true, mode);
        uc.setVisible(true);
    }

    /**
     * Abre la ventana del CRUD de usuario.
     *
     * @param f La ventana que ejecuta éste método.
     * @param mode Determina el modo de la ventana del CRUD, y la acción que
     * realiza.
     */
    public static void ownerCRUD(Frame f, byte mode) {
        OwnerCRUD oc = new OwnerCRUD(f, true, mode);
        oc.setVisible(true);
    }

    /**
     * Abre la ventana del CRUD de usuario.
     *
     * @param f La ventana que ejecuta éste método.
     * @param mode Determina el modo de la ventana del CRUD, y la acción que
     * realiza.
     */
    public static void playerCRUD(Frame f, byte mode) {
        PlayerCRUD pc = new PlayerCRUD(f, true, mode);
        pc.setVisible(true);
    }

    /**
     * Abre la ventana del CRUD de usuario.
     *
     * @param f La ventana que ejecuta éste método.
     * @param mode Determina el modo de la ventana del CRUD, y la acción que
     * realiza.
     */
    public static void teamCRUD(Frame f, byte mode) {
        TeamCRUD tc = new TeamCRUD(f, true, mode);
        tc.setVisible(true);
    }

    /**
     * Realiza una consulta a las cuentas en la base de datos con usuario y
     * contraseña. Devuelve un int representando al tipo de cuenta si existe, y
     * un 0 si no.
     * @param username el nombre del usuario
     * @param password un array de caracteres que contiene la contraseña
     * @return el tipo de cuenta
     */
    public static byte LoginAccountQuery(String username, char[] password) {
        return SuperProyecto.getAccountType(username, password);
    }
}
