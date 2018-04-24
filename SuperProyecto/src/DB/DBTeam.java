/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;
import static DB.DBController.createConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class DBTeam {
    /**
     * Devuelve el numero de equipos
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return el numero de equipos
     */
    public static int getTeamQuantity() throws ClassNotFoundException, SQLException{
        createConnection();
        Statement sent = createConnection().createStatement();
        ResultSet resul=sent.executeQuery("SELECT COUNT(*) AS TEAMS FROM TEAM");
        int num=resul.getInt("TEAMS");
        return 
    }
}
