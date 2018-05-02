/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import ModelUML.DBAdmin;
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
public class DBDBAdmin {
    /**
     * Inserta un administrador en la base de datos
     * @param username nombre de usuario
     * @param password contraseña 
     * @param con la conexion
     * @throws SQLException por si se da una excepcion SQL
     */
    public static void insertDBAdmin(String username,char[] password,Connection con) throws SQLException{
        
        Statement sta = con.createStatement();
        sta.executeUpdate("INSERT INTO DBADMIN(USERNAME,PASSWD) VALUES('"+username+"','"+String.valueOf(password)+"')");
        sta.close();
    }
    /**
     * Borra un administrador de la base de datos
     * @param username nombre de usuario
     * @param password contraseña
     * @param con la conexion
     * @throws SQLException si se da una excepcion SQL
     */
    public static void deleteDBAdmin(String username,char[] password,Connection con) throws SQLException{
        
        Statement sta= con.createStatement();
        sta.executeUpdate("DELETE FROM DBADMIN WHERE USERNAME='"+username+"' AND PASSWD='"+String.valueOf(password)+"'");
        sta.close();
    }
    /**
     * Cambia la contraseña del administrador 
     * @param username nombre de usuario
     * @param newPassword nueva contraseña
     * @param password contraseña actual
     * @param con la conexion
     * @throws SQLException si se da una excepcion SQL
     */
    public static void updateDBAdminPassword(String username,char[] newPassword,char[] password,Connection con) throws SQLException{
        Statement sta=con.createStatement();
        sta.executeUpdate("UPDATE DBADMIN SET PASSWD='"+String.valueOf(newPassword)+"'"
                + " WHERE USERNAME='"+username+"' AND PASSWD='"+String.valueOf(password)+"'");
        sta.close();
    }
     /**
     * Consulta a la base de datos todos los usuarios y devuelve un ArrayList
     * @param con la conexion
     * @return un ArrayList con todos los usuarios
     * @throws SQLException si se da alguna excepcion
     */
    public static ArrayList<DBAdmin> selectAllAdmins(Connection con) throws SQLException{
        ArrayList<DBAdmin> admins= new ArrayList();
        Statement sta = con.createStatement();
        ResultSet resul = sta.executeQuery("SELECT * FROM DBADMIN");
        while(resul.next()){
            admins.add(new DBAdmin(resul.getString(2),resul.getString(3).toCharArray()));
        }
        resul.close();
        sta.close();
        return admins;
    }
    
}
