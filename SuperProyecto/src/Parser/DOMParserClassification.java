/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;
import DB.DBController;
import static DB.DBController.createConnection;
import ModelUML.Game;
import ModelUML.League;
import ModelUML.MatchSet;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import superproyecto.SuperProyecto;
import static superproyecto.SuperProyecto.askAllGamesID;
import static superproyecto.SuperProyecto.askLastLeagueID;
import static superproyecto.SuperProyecto.askMatchSetsID;
import static superproyecto.SuperProyecto.createMatchSets;
/**
 *  Esta clase se encarga de obtener la informacion de la base de datoss y con ella
 * crear un arbol de DOM, para despues introducir esa informacion en el fichero
 * xml Classification.xml
 * @version %I% %G%
 * @author Jon
 */
public class DOMParserClassification {
    
    Document dom;
    ArrayList<Integer> points;
    ArrayList<String> names;
    public DOMParserClassification(){
        loadData();
    }

    private void loadData(){
        try {
            
            Connection con= DBController.createConnection();
            int leagueid = SuperProyecto.askLastLeagueID(con);
            ResultSet rs = SuperProyecto.getClassification(leagueid,con);
            while (rs.next()){
                points.add(rs.getInt(3));
                names.add(rs.getString(2));
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DOMParserClassification.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DOMParserClassification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        //Creamos una instancia
        DOMParserClassification dClassification = new DOMParserClassification();
        
        //Ejecutamos
        dClassification.execute();
    }

    public void execute() {
        System.out.println("Ejecutando..");
        //Volcamos el Fichero XML en memoria como Arbol DOM
        parseXMLFile();
        //Creamos los elementos y los agregamos al arbol DOM
        createDOMTree();
        //Escribimos el Arbol DOM en el fichero XML
        writeXMLFile();
    }

    private void parseXMLFile() {
        //Creamos el DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            //Usamos la factory para obtener una instancia del documento
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parseamos utilizando el builder para obtener una instancia en DOM del XML
            dom = db.parse("../XML/Classification.xml");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void createDOMTree() {
        //referencia al objeto raiz<classification>
        Element rootClassification = dom.getDocumentElement();
        //Para contar
        int x = names.size();
        for(int y =0; y<x;y++){
            Element teamEle = createTeamEle(y);
            rootClassification.appendChild(teamEle);
        }
        
    }

    private Element createTeamEle(int y) {
        Element teamEle = dom.createElement("team");
        
        Element nameEle = dom.createElement("name");
        Text nameTxt = dom.createTextNode(names.get(y));
        nameEle.appendChild(nameTxt);
        
        Element pointEle = dom.createElement("points");
        Text pointsTxt = dom.createTextNode(Integer.toString(points.get(y)));
        pointEle.appendChild(pointsTxt);
        
        teamEle.appendChild(nameEle);
        teamEle.appendChild(pointEle);
        
        return teamEle;
    }

    private void writeXMLFile() {
         try {
            //Configuramos el formato de salida del fichero
            OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);

            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File("../XML/Classification.xml")), format);

            serializer.serialize(dom);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
