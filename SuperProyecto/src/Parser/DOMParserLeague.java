/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;
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
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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
 * Esta clase se encarga de obtener la informacion de la base de datos y de 
 * construir un arbol DOM con esa información(League,MatchSet,Game,Game_result)
 * y de introducir el arbol DOM en el fichero xml League.xml
 * @author Jon
 * @version %I% %G%
 */
public class DOMParserLeague {
    //
    League league= new League("tempname");
    private static Document dom;
    private int gameIDCounter=0;
    ArrayList<Integer> gamesID;
    /**
     * Constructor de DOM, llama a la funcion de cargar datos
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL
     */
    public DOMParserLeague() throws ClassNotFoundException, SQLException{
        loadData();
    }
    /**
     * Carga los datos recogidos de la base de datos dentro de league
     * @throws ClassNotFoundException si no se encuentra la clase
     * @throws SQLException si se da alguna excepcion SQL 
     */
     private void loadData() throws ClassNotFoundException, SQLException{
        Connection con =createConnection();
        int idLeague = askLastLeagueID(con);
        ArrayList<Integer> matchSetsID =askMatchSetsID(idLeague,con);
        ArrayList<MatchSet> matchSets = new ArrayList();
        int magic = (matchSetsID.size()/2);
        int flippy = 0;
        for(Integer x:matchSetsID){
            if(flippy<magic){
            MatchSet tempMatchSet = SuperProyecto.createMatchSets(true,x,con);
            league.addMatchSets(tempMatchSet);
            }
            else{
            MatchSet tempMatchSet = SuperProyecto.createMatchSets(false,x,con);
            league.addMatchSets(tempMatchSet);
            }
            flippy++;
        }
        
        gamesID=askAllGamesID(idLeague,con);
        con.close();
    }
    /**
     * LLama a las funciones necesarias para parsear, crear el arbol de DOM, y
     * escribir en el fichero XML
     */
    public void execute(){
        System.out.println("Ejecutando..");
        //Volcamos el fichero XML en memoria como arbol de DOM
        parseXMLFile();
        if(updateneeded()){//Se comprueba que sea necesario actualizar
        //Borramos el fichero desactualizado
        deletePreviousContent();
        //Creamos los elementos y los agregamos al arbol de DOM
        createDOMTree(createupdatedateElement());
        //Escribimos el arbol DOM en el fichero XML
        writeXMLFile();
        System.out.println("Fichero modificado correctamente");
        //Y finalmente ejecutamos el DOM de la clasificacion
        DOMParserClassification.executeDOMClassification();
        }
        else{
            System.out.println("No habia necesidad de modificar..");
        }
    }
    /**
     * Escribe en el documento XML el arbol DOM
     */
    private void writeXMLFile(){
        try {
            //Configuramos el formato de salida del fichero
            OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);

            //to generate a file output use fileoutputstream instead of system.out
            XMLSerializer serializer = new XMLSerializer(
                    new FileOutputStream(new File("../XML/League.xml")), format);

            serializer.serialize(dom);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
    /**
     * Crea el arbol DOM
     */
    private void createDOMTree(Element updateele){
        //referencia al objeto raiz<league>
        Element rootLeague = dom.getDocumentElement();
        /*Ahora se va a recorrer el ArrayList de MatchSet que hay en league, creando cada
        objeto MatchSet, a su vez, para cada MatchSet, se recorrera el ArrayList de Game 
        creando los objetos cada uno con sus elementos y atributos */
        Element dateEle = updateele;
        rootLeague.appendChild(dateEle);
        Iterator it = league.getMatchsets().iterator();
        while(it.hasNext()){
            MatchSet ms = (MatchSet)it.next();
            //Cogemos la informacion almacenada y creamos
            Element matchsetEle =createMatchSetElement(ms);
            rootLeague.appendChild(matchsetEle);
        }
        gameIDCounter=0;
    }
    /**
     * Crea un elemento MatchSet y devuelve un Element
     * @param ms un MatchSet
     * @return un Element 
     */
    private Element createMatchSetElement(MatchSet ms){
        Element matchsetEle = dom.createElement("matchset");
        //Ahora creamos los Game del MatchSet correspondiente
        for(Game gm:ms.getGames()){
            Element gameEle = createGameElement(gm);
            matchsetEle.appendChild(gameEle);
        }
        return matchsetEle;
    }
    /**
     * Crea un elemento Game y devuelve un Element 
     * @param gm un Game
     * @return un Element
     */
    private Element createGameElement(Game gm){
        Element gameEle = dom.createElement("match");
        //Ahora creamos los elementos y el atributo y lo asociamos al Game
        gameEle.setAttribute("id",""+gamesID.get(gameIDCounter));//esa forma de convertir a String tan "Meh"
        System.out.println(""+gamesID.get(gameIDCounter));
        gameIDCounter++;
        //Equipo1
        Element team1Ele=dom.createElement("team1");
        Text team1Text = dom.createTextNode(gm.getTeam1().getTeamName());
        team1Ele.appendChild(team1Text);
        gameEle.appendChild(team1Ele);
        //Equipo2
        Element team2Ele=dom.createElement("team2");
        Text team2Text = dom.createTextNode(gm.getTeam2().getTeamName());
        team2Ele.appendChild(team2Text);
        gameEle.appendChild(team2Ele);
        //Si no son nulos los score
        System.out.println(gm.getTeam1().getTeamName()+" "+gm.getTeam2().getTeamName());
        if(Integer.valueOf(gm.getScore1())!=null||Integer.valueOf(gm.getScore2())!=null){
            //Score1
            Element score1Ele = dom.createElement("score1");
            Text score1Text = dom.createTextNode(""+gm.getScore1());
            score1Ele.appendChild(score1Text);
            gameEle.appendChild(score1Ele);
            //Score2 
            Element score2Ele = dom.createElement("score2");
            Text score2Text = dom.createTextNode(""+gm.getScore2());
            score2Ele.appendChild(score2Text);
            gameEle.appendChild(score2Ele);
        }
        
        return gameEle;
    }
    /**
     * Parsea un documento XML 
     */
    private void parseXMLFile(){
        //Creamos el DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {

            //Usamos la factory para obtener una instancia del documento
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parseamos utilizando el builder para obtener una instancia en DOM del XML
            dom = db.parse("../XML/League.xml");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Ejecuta todo el parser
     */
    public static void executeDOMLeague() throws ClassNotFoundException, SQLException{
        //Creamos una instancia
        DOMParserLeague dLeague = new DOMParserLeague();
        
        //Ejecutamos
        dLeague.execute();
        
    }
    /**
     * Comprueba que la fecha de actualizacion sea menor que el dia actual y si es asi
     * actualiza la fecha de actualizacion con la fecha del siguiente partido
     * @return un Element
     */
    private Element createupdatedateElement() {
        
        //Obtenemos el documento
        Element docEle = dom.getDocumentElement();
        //Obtenemos el nodo <updatedate>
        NodeList nl = docEle.getElementsByTagName("updatedate");
        //El Text que se le asignara a update date
        /*
        Tengo que CAMBIAR la forma en la que organiza todo esto, 
        1-transformar lo que devuelve el node list en un elemento
        2-sacar el dato que necesito de ese elemento, que es su texto
        3- convertir el texto a Date, eso ya no tengo que cambiar casi nada
        4- Para el String de currentDateS no le puedo dar directamente el objeto y el text value
        */
        Element ele =(Element) nl.item(0);
        Text updatedateText= dom.createTextNode(ele.getTextContent());
        //Pasamos el texto de ese nodo a date
        String currentdateS = ele.getTextContent();
        Date currentdate = Date.valueOf(currentdateS);
        //Obtenemos la fecha del sistema
        Date systemdate =Date.valueOf( new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));//Estoy orgulloso de esta linea
        if(currentdate.before(systemdate)){
            for(MatchSet mts:league.getMatchsets()){
                for(Game gms:mts.getGames()){
                    if(gms.getDateTime().equals(systemdate)){
                        updatedateText = dom.createTextNode(gms.getDateTime().toString());
                    }
                }
            }
        }
        //El objeto a retornar
        docEle.removeChild(ele);
        Element updatedateEle = dom.createElement("updatedate");
        updatedateEle.appendChild(updatedateText);
        return updatedateEle;
    }

    private void deletePreviousContent() {
        Element docEle= dom.getDocumentElement();
        for(int x=0;x<gamesID.size();x++){
        NodeList nl = docEle.getElementsByTagName("matchset");
            for (int i = 0; i < nl.getLength(); i++) {
                Element matchSeto = (Element) nl.item(i);
                    docEle.removeChild(matchSeto);
        }
        }
    }

    private boolean updateneeded() {
         //Obtenemos el documento
        Element docEle = dom.getDocumentElement();
        //Obtenemos el nodo <updatedate>
        NodeList nl = docEle.getElementsByTagName("updatedate");
        //El Text que se le asignara a update date
        Element ele =(Element) nl.item(0);
        //Pasamos el texto de ese nodo a date
        String currentdateS = ele.getTextContent();
        Date currentdate = Date.valueOf(currentdateS);
        //Obtenemos la fecha del sistema
        Date systemdate =Date.valueOf( new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));//Estoy orgulloso de esta linea
        if(currentdate.before(systemdate)){
            return true;
        }
        else{
            return false;
        }
    }
    
    
}
