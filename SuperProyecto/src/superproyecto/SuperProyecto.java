/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superproyecto;

import static ModelDB.DBController.createConnection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.ViewController;
import DB.DBController;
/**
 *
 * @author Jon Maneiro
 * @author Sebastián Zawisza
 * @author Sergio Zulueta
 * @version %I% %G%
 * @since 1.0
 */
public class SuperProyecto {

    /**
     * Abre la ventana de Login, de la cual nacen todas las demás.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            /*vvNO MODIFICAR ESTOvv*/
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        
        /*^^NO MODIFICAR ESTO^^*/
        createConnection();
        } catch (SQLException ex) {
            Logger.getLogger(SuperProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SuperProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        ViewController.login();
    }

    /**
     * Crea el calendario para la liga actual, dando a la base de datos los
     * datos para la creacion de League, MatchSets, Game y GameResult.
     * 
     * 
     */
    public static void createCalendar(){
        
    }
    
    /**
     * Busca la cuenta con los parametros introducidos y devuelve un int dependiendo del tipo de cuenta.
     * Usado por el Login.
     * @param username El usuario de la cuenta.
     * @param password La contraseña de la cuenta.
     * @return El tipo de cuenta.
     */
    public static byte getAccountType(String username, char[] password){
    //buscar la cuenta y hacer un switch o algo para devolver un byte dependiendo del tipo
    //yo(sebas) puedo hacer esto sin problema una vez lo de la DB esté hecho
    }
}
