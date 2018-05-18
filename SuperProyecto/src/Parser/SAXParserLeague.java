/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import ModelUML.*;

import java.io.IOException;
import java.util.ArrayList;

import java.sql.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Esta clase se encarga de parsear el documento League.xml y de devolver un
 * ArrayList con todos los MatchSet llenos de sus respectivos Game, que a su vez
 * estan llenos de sus respectivos datos
 *
 * @author Sergio Zulueta
 * @version %I% %G%
 * @since 1.0
 */
public class SAXParserLeague extends DefaultHandler {

    /**
     * Creacion de los atributos matchset, updateDate, tempVal, date, match,
     * matchSet, team.
     */
    static ArrayList matchset;

    ArrayList updateDate;
    private String tempVal;
    //Para mantener el contexto
    private Date date;
    private Game match;
    private MatchSet matchSet;
    private Team team;

    /**
     * Inicializamos el ArrayList.
     */
    public SAXParserLeague() {
        matchset = new ArrayList();
        updateDate = new ArrayList();
    }

    /**
     * Se encarga de llamar y ejecutar otras funciones.
     */
    public void runExample() {
        parseDocument();
        printData();
    }

    /**
     * Parsea el documento XML.
     */
    private void parseDocument() {
        //Creamos una nueva factoria de parsers SAX
        SAXParserFactory spf = SAXParserFactory.newInstance();

        try {
            //instanciamos un nuevo parser SAX a partir de la factoría
            SAXParser sp = spf.newSAXParser();

            //parseamos el fichero xml y enviamos la clase para los cuando la llamemos
            sp.parse("../XML/League.xml", this);

        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Iteramos a través de la lista y mostramos la información por pantalla
     */
    private void printData() {
        System.out.println("Leagues'" + matchset.size() + "'.");

        //bucle ++
        int n = 0;
        Boolean vControl = true;
        while (vControl) {

            try {

                //for de los updateDate
                for (int i = 0; i < 1; i++) {
                    System.out.println(updateDate.get(n).toString());
                    //for para coger la jornada
                    for (int j = 0; j < 1; j++) {
                        MatchSet m = (MatchSet) matchset.get(n);
                        //de cada jornada mostrar sus games
                        for (Object object : m.myGames()) {
                            System.out.println(object.toString());
                        }
                    }
                }
                n++;
            } catch (IndexOutOfBoundsException e) {
                vControl = false;
            }
        }

    }

    /**
     * Controladora de eventos, recibe notificacion del inicio de un elemento
     *
     * @param uri El URI de espacio de nombres o la cadena vacia si el elemento
     * no tiene URI de espacio de nombres o si el procesamiento del espacio no
     * se este realizando
     * @param localName Nombre local (sin prefijo), o la cadena vacia si no se
     * esta procesando el espacio de nombres
     * @param qName El nombre calificado (con prefijo) o la cadena vacia si los
     * nombre calificados no estan disponibles
     * @param attributes Los atributos unidos al elemento. Si no hay atributos,
     * sera un objeto attributes vacio
     * @throws SAXException si se da alguna excepcion SAX
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //inicializamos
        tempVal = "";
        //Crear nuevas instancias de los objetos
        if (qName.equalsIgnoreCase("matchset")) {
            matchSet = new MatchSet();
        } else if (qName.equalsIgnoreCase("match")) {
            match = new Game();
            String id = attributes.getValue("id");
        }
    }

    /**
     *
     * Recibe notificacion de datos de caracteres dentro de un elemento.
     *
     * @param ch Los caracteres
     * @param start La posicion de inicio en el Array de caracteres
     * @param length La cantidad de caracteres a usar en el Array de caracteres
     * @throws SAXException si se da alguna excepcion SAX
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tempVal = new String(ch, start, length);
    }

    /**
     * Recibe notificacion del final de un elemento
     *
     * @param uri El URI de espacio de nombres o la cadena vacia si el elemento
     * no tiene URI de espacio de nombres o si el procesamiento del espacio no
     * se este realizando
     * @param localName Nombre local (sin prefijo), o la cadena vacia si no se
     * esta procesando el espacio de nombres
     * @param qName El nombre calificado (con prefijo) o la cadena vacia si los
     * nombre calificados no estan disponibles
     * @throws SAXException si se da alguna excepcion SAX
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("updateDate")) {
            //Añanadirlo a la lista
            updateDate.add(date = Date.valueOf(tempVal));
        } else if (qName.equalsIgnoreCase("matchset")) {
            matchset.add(matchSet);
        } else if (qName.equalsIgnoreCase("match")) {
            matchSet.addGame(match);            
        } else if (qName.equalsIgnoreCase("team1")) {
            team = match.getTeam1();
            team = new Team();
            team.setTeamName(tempVal);
            match.setTeam1(team);
        } else if (qName.equalsIgnoreCase("team2")) {
            team = match.getTeam2();
            team = new Team();
            team.setTeamName(tempVal);
            match.setTeam2(team);
        } else if (qName.equalsIgnoreCase("score1")) {
            match.setScore1(Integer.parseInt(tempVal));
        } else if (qName.equalsIgnoreCase("score2")) {
            match.setScore2(Integer.parseInt(tempVal));
        }
    }  
       

    /**
     * Se encarga de ejecutar todo el parser
     *
     * @return un ArrayList con todos los objetos MatchSet llenos
     */
    public static ArrayList<MatchSet> executeSAXLeague() {

        System.out.println("SAX");
        System.out.println("---");

        //Ejecutamos
        SAXParserLeague spe = new SAXParserLeague();
        spe.runExample();
        return matchset;
    }
    
    /*public static void main(String[] args) {

        System.out.println("SAX");
        System.out.println("---");

        //Ejecutamos
        SAXParserLeague spe = new SAXParserLeague();
        spe.runExample();
    }*/

}
