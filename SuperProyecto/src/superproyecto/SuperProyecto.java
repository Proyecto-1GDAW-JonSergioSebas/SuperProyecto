/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superproyecto;

import static DB.DBController.createConnection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.ViewController;
import DB.DBController;
import static DB.DBController.teams;
import ModelUML.Player;
import ModelUML.Team;
import ModelUML.TeamOwner;
import java.sql.Connection;
import java.util.ArrayList;
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
    public static void main(String[] args){
        /*vvNO MODIFICAR ESTOvv*/
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException ex) {
            Logger.getLogger(SuperProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } 
        /*^^NO MODIFICAR ESTO^^*/
        //ViewController.login();
        createCalendar();
    }

    /**
     * Crea el calendario para la liga actual, dando a la base de datos los
     * datos para la creacion de League, MatchSets, Game y GameResult.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @see DB.DBController#teamQuantity() 
     */
    public static void createCalendar() {
        try{
            Connection con = createConnection();
            ArrayList<Team> completeTeams=teams(con);//Los equipos
            
            if(completeTeams.size()%2 !=0){
                ArrayList<Player> pl = new ArrayList();
                completeTeams.add(new Team("Null","Null"));
            }
            int numDays = (completeTeams.size()-1);//Dias necesarios en el torneo o lo que sea
            int halfSize = completeTeams.size()/2;
            
            ArrayList<Team> teams=new ArrayList();//Añadir los equipos y eliminar el primero
            teams.addAll(completeTeams);
            teams.remove(0);
            
            int teamsSize=teams.size();
            
           // for(int day=0; day<numDays; day++){
            //    
           // }
            
          con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SuperProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SuperProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    return 0; //esto es solo para que no de error mientras el metodo no está hecho
    }
}
