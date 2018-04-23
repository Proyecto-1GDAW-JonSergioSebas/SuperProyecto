/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
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
     * Realiza una consulta a las cuentas en la base de datos con usuario y contraseña. 
     * Devuelve un int representando al tipo de cuenta si existe, y un 0 si no.
     */
    public static byte LoginAccountQuery(String username, char[] password) {
        return SuperProyecto.getAccountType(username, password);
    }
}
