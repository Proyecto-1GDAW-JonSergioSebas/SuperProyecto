/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;
import static DB.DBController.createConnection;
import static DB.DBController.obtainPlayers;
import static DB.DBController.obtainTeamOwner;
import ModelUML.Player;
import ModelUML.Team;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
     * Devuelve los equipos con el nombre y la nacionalidad
     * @param con la conexion
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return lista con los equipos
     */
    public static ArrayList<Team> getTeams(Connection con) throws ClassNotFoundException, SQLException{
        
        ArrayList<Team> teams= new ArrayList();
        Statement sent = con.createStatement();
        ResultSet resul=sent.executeQuery("SELECT * FROM TEAM");
        while(resul.next()){
            teams.add(new Team(resul.getString(2),resul.getString(3)));
        }
        resul.close();
        sent.close();
        return teams;
    }
    /**
     * Busca el equipo obteniendo como dato el nombre del equipo
     * @param teamname el nombre del equipo
     * @param con la conexion
     * @return in int con el id del equipo
     * @throws SQLException 
     */
    public static int searchTeam(String teamname,Connection con) throws SQLException{
        int x=-1;
        Statement sent= con.createStatement();
        ResultSet resul= sent.executeQuery("SELECT ID_TM FROM TEAM WHERE TEAM_NAME='"+teamname+"'");
        resul.next();
        x=resul.getInt("ID_TM");
        resul.close();
        sent.close();
        return x;
        
    }
}
