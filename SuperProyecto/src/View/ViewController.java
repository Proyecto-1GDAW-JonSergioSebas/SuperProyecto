/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import ModelUML.DBUser;
import java.awt.Frame;
import java.sql.SQLException;
import java.text.ParseException;
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
    public static void user(boolean child) {
        User user = new User(child);
        user.setVisible(true);
    }

    /**
     * Abre la ventana de Owner.
     */
    public static void owner(boolean child) {
        Owner owner = new Owner(child);
        owner.setVisible(true);
    }

    /**
     * Abre la ventana de Admin.
     */
    public static void admin(boolean child) {
        Admin admin = new Admin(child);
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
    
    public static void league(Frame f){
        League league = new League(f, true);
        league.setVisible(true);
    }

    /**
     * Realiza una consulta a las cuentas en la base de datos con usuario y
     * contraseña. Devuelve un int representando al tipo de cuenta si existe, y
     * un 0 si no.
     *
     * @param username el nombre del usuario
     * @param password un array de caracteres que contiene la contraseña
     * @return el tipo de cuenta
     */
    public static byte LoginAccountQuery(String username, char[] password) throws ClassNotFoundException, SQLException {
        return SuperProyecto.getAccountType(username, password);
    }
    
    public static void insertUser(String us, char[] pw){
        
    }
    
    public static void selectUsers(){
        
    }
    
    public static void createCalendar(String leaguename, String date) throws ParseException {
        SuperProyecto.createCalendar(leaguename, date);
    }
}
