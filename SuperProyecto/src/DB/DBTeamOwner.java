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
import java.util.ArrayList;

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
     * @throws SQLException hay una excepcion SQL
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
    /**
     * Inserta un Team_Owner en la base de datos
     * @param username el nombre de usuario 
     * @param password la contraseña
     * @param fullName nombre completo
     * @param telephone el telefono
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion en SQL
     */
    public static void insertDBTeamOwner(String username,char[] password,String fullName,String telephone,Connection con) throws SQLException{
        
        Statement sta = con.createStatement();
        sta.executeUpdate("INSERT INTO TEAM_OWNER(USERNAME,PASSWD,FULL_NAME,TELEPHONE) VALUES('"+username+"'"
                + ",'"+String.valueOf(password)+"','"+fullName+"','"+telephone+"')");
        sta.close();
    }
    /**
     * Elimina un Team_Owner de la base de datos
     * @param username el nombre de usuario
     * @param password la contraseña
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion en SQL
     */
    public static void deleteDBTeamOwner(String username,char[] password,Connection con) throws SQLException{
        
        Statement sta = con.createStatement();
        sta.executeUpdate("DELETE FROM TEAM_OWNER WHERE USERNAME='"+username+"' AND PASSWD='"+String.valueOf(password)+"'");
        sta.close();
    }
    /**
     * Cambia la contraseña de un TeamOwner
     * @param username nombre de usuario
     * @param newPassword la nueva contraseña
     * @param password la contraseña actual
     * @param con la conexion
     * @throws SQLException si se da alguna excepcion en SQL 
     */
    public static void updateDBTeamOwner(String username,String newUsername,char[] newPassword,String newFullName,String newTelephone,Connection con) throws SQLException{
        
        Statement sta = con.createStatement();
        sta.executeUpdate("UPDATE TEAM_OWNER SET USERNAME='"+newUsername+"',PASSWD='"+String.valueOf(newPassword)+"'"
                + ",FULL_NAME='"+newFullName+"',TELEPHONE='"+newTelephone+"' "
                + " WHERE USERNAME='"+username+"' ");
        sta.close();
    }
    /**
     * Devuelve todos los TeamOwners
     * @param con la conexion
     * @return un ArrayList con todos los TeamOwners
     * @throws SQLException si se da alguna excepcion en SQL 
     */
    public static ArrayList<TeamOwner> selectAllTeamOwners(Connection con) throws SQLException{
        ArrayList<TeamOwner> teamOwners = new ArrayList();
        Statement sta= con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT * FROM TEAM_OWNER");
        while(resul.next()){
            teamOwners.add(new TeamOwner(resul.getString(4),resul.getString(5),resul.getString(2),resul.getString(3).toCharArray()));
        }
        resul.close();
        sta.close();
        return teamOwners;
    }
    /**
     * Busca la id de un TeamOwner
     * @param owner el nombre de usuario del TeamOwner a buscar
     * @param con la conexion
     * @return el valor de la id como int
     * @throws SQLException  si se da alguna excepcion en SQL
     */
    public static int selectTeamOwnerID(String owner,Connection con) throws SQLException{
        int id=0;
        Statement sta = con.createStatement();
        ResultSet resul =  sta.executeQuery("SELECT ID_TO FROM TEAM_OWNER WHERE USERNAME='"+owner+"'");
        resul.next();
        id=resul.getInt(1);
        resul.close();
        sta.close();
        return id;
    }
}
