/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 * Esta clase gestiona la activacion de las sentencias PL/SQL dentro de la base
 * de datos
 *
 * @author Sebastián Zawisza
 * @version %I% %G%
 * @since 1.0
 */
public class DBProcedures {

    /**
     * Retorna el tipo de cuenta, expresado en un int
     *
     * @param us Usuario
     * @param pw Contraseña
     * @param con La conexión
     * @return el tipo de cuenta
     * @throws SQLException si sucede algun error de sql
     */
    public static int LoginGetType(String us, String pw, Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{CALL LOGIN.GET_TYPE(?, ?, ?)}"); //todo esto es brujería        
        cs.setString(1, us);
        cs.setString(2, pw);
        cs.registerOutParameter(3, Types.INTEGER);
        cs.execute();
        return cs.getInt(3);
    }

    /**
     * Llama a un procedimiento almacenado en la base de datos que
     * convenientemente devuelve un REF_CURSOR con los equipos y su puntuacion,
     * que serán transformados a ResultSet
     *
     * @param leagueid el id de la liga
     * @param con la conexion
     * @return un objeto ResultSet
     * @throws SQLException si se da alguna excepcion SQL
     */
    public static ResultSet getClassification(int leagueid, Connection con) throws SQLException {
        CallableStatement cs = con.prepareCall("{CALL CLASSIFICATION.GET_CLASSIFICATION(?,?)}");//Magia oscura
        cs.setInt(1, leagueid);
        cs.registerOutParameter(2, OracleTypes.CURSOR);
        cs.execute();
        ResultSet rs = ((OracleCallableStatement) cs).getCursor(2);
        return rs;
    }
}
