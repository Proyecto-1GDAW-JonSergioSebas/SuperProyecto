/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import DB.DBController;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

/**
 * Esta clase se encarga de obtener la informacion de la base de datoss y con
 * ella crear un arbol de DOM, para despues introducir esa informacion en el
 * fichero xml Classification.xml
 *
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class DOMParserClassification {

    private Document dom;
    private static ArrayList<Integer> points;
    private static ArrayList<String> names;

    /**
     * Un constructor
     */
    public DOMParserClassification() {
        loadData();
    }

    /**
     * Carga los datos de la base de datos
     */
    private void loadData() {
        try {

            Connection con = DBController.createConnection();
            int leagueid = SuperProyecto.askLastLeagueID(con);
            ResultSet rs = SuperProyecto.getClassification(leagueid, con);
            while (rs.next()) {
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

    /**
     * Ejecuta el parser
     */
    public static void executeDOMClassification() {
        //Creamos una instancia
        points = new ArrayList();
        names = new ArrayList();
        DOMParserClassification dClassification = new DOMParserClassification();

        //Ejecutamos
        dClassification.execute();
    }

    /**
     * Ejecuta las funcionalidades del parser
     */
    public void execute() {
        System.out.println("Ejecutando..");
        //Volcamos el Fichero XML en memoria como Arbol DOM
        parseXMLFile();
        //borramos lo que hubiera antes
        deletePreviousContent();
        //Creamos los elementos y los agregamos al arbol DOM
        createDOMTree();
        //Escribimos el Arbol DOM en el fichero XML
        writeXMLFile();
        System.out.println("Fichero modificado correctamente.");
    }

    /**
     * Parsea el fichero Classification.xml
     */
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

    /**
     * Vuelca los datos guardados dentro del Arbol DOM
     */
    private void createDOMTree() {
        //referencia al objeto raiz<classification>
        Element rootClassification = dom.getDocumentElement();
        //Para contar
        int x = names.size();
        for (int y = 0; y < x; y++) {
            Element teamEle = createTeamEle(y);
            rootClassification.appendChild(teamEle);
        }

    }

    /**
     * Crea el elemento TEAM
     *
     * @param y contador para saber que Team se debe crear
     * @return un Element que tiene un equipo
     */
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

    /**
     * Escribe los datos del Arbol DOM en el fichero XML
     */
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

    /**
     * Elimina el contenido anterior
     */
    private void deletePreviousContent() {
        Element docEle = dom.getDocumentElement();

        for (int x = 0; x < names.size(); x++) {
            NodeList nl = docEle.getElementsByTagName("team");
            for (int i = 0; i < nl.getLength(); i++) {
                Element teamito = (Element) nl.item(i);
                docEle.removeChild(teamito);
            }
        }
    }
}
