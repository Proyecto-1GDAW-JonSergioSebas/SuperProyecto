/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import ModelUML.Team;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jon
 */
public class DBTeamTest {
    
    public DBTeamTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    

    /**
     * Test of searchTeam method, of class DBTeam.
     */
    @Test
    public void testSearchTeam() throws Exception {
        System.out.println("searchTeam");
        String teamname = "Aon";
        Connection con = DBController.createConnection();
        int expResult = 1;
        int result = DBTeam.searchTeam(teamname, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    
}
