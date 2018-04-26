/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import ModelUML.TeamOwner;
import java.sql.Connection;
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
public class DBTeamOwner {
    /**
     * Busca el TeamOwner correspondiente a la id que le pasamos
     * @param teamownerid la id del TeamOwner 
     * @param con la conexion
     * @return el TeamOwner correspondiente
     * @throws SQLException 
     */
    public static TeamOwner getTeamOwner(int teamownerid,Connection con) throws SQLException{
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM TEAM_OWNER WHERE ID_TO="+teamownerid);
        rs.next();
        TeamOwner own= new TeamOwner(rs.getString(4),rs.getString(5));
        rs.close();
        stat.close();
        return own;
    }
}
