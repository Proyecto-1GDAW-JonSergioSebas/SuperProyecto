/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;
        
import static DB.DBController.createConnection;
import ModelUML.League;
import ModelUML.MatchSet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.sql.Connection;
import java.sql.SQLException;
import static superproyecto.SuperProyecto.askLastLeagueID;
import static superproyecto.SuperProyecto.askMatchSetsID;
//import static superproyecto.SuperProyecto.createMatchSets;

/**
 * Esta clase se encarga de crear el Documento XML para la liga, con el objetivo de minimizar las consultas a la base de datos
 * siendo este el documento que se va a consultar para visualizar el calendario.
 * @author Jon Maneiro
 */
public class LeagueXMLCreator {
    //Variables globales 
    Document dom;//el documento
    
    League league;
    
    /**
     * Main for the XMLCreator
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException, SQLException {

        //create an instance
        LeagueXMLCreator lxc = new LeagueXMLCreator();

        //run the creator
       // lxc.runCreator();
    }
    
    public LeagueXMLCreator() throws ClassNotFoundException, SQLException{
        //Cargar los datos en League
        loadData();//Seleccionar todos los datos de la liga y llenarla de alguna forma con los datos actuales
    }
    
    private void loadData() throws ClassNotFoundException, SQLException{/*
        Connection con=createConnection();
        int idLeague = askLastLeagueID(con);
        ArrayList<Integer> idMatchSets= askMatchSetsID(idLeague,con);
        ArrayList<MatchSet> matchSets= new ArrayList();
        for(Integer x:idMatchSets){
            matchSets.add(createMatchSets(x,con));
        }
        
        
        
        
        */
        
        //con.close();
    }
     
     
}
