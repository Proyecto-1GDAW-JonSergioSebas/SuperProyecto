/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author 1gdaw07
 */
public class DBProcedures {
/**
 * Retorna el tipo de cuenta, expresado en un int
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
}
