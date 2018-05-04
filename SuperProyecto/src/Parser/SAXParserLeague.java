/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import ModelUML.*;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author Sergio Zulueta
 * @author Sebastián Zawisza
 * @author Jon Maneiro
 * @version %I% %G%
 * @since 1.0
 */
public class SAXParserLeague extends DefaultHandler {

    ArrayList myLeague;

    private String tempVal;

    //Para mantener el contexto
    private League league;
    private ArrayList<MatchSet> matchsets;
    private Game game;
    private Team team1;
    private Team team2;
    private Game score1;
    private Game score2;
    private Game dateTime;
    private MatchSet matchset;

    public SAXParserLeague() {
        myLeague = new ArrayList();
    }

    public void runExample() {
        parseDocument();
        printData();
    }

    private void parseDocument() {
        //Creamos una nueva factoria de parsers SAX
        SAXParserFactory spf = SAXParserFactory.newInstance();

        try {
            //instanciamos un nuevo parser SAX a partir de la factroría
            SAXParser sp = spf.newSAXParser();

            //parseamos el fichero xml y enviamos la clase para los call backs
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

        System.out.println("Leagues'" + myLeague.size() + "'.");

        Iterator it = myLeague.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }

    }

    //Event Handlers
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //inicializamos
        tempVal = "";
        //Crear nuevas instancias de los objetos
        if (qName.equalsIgnoreCase("League")) {
            matchsets = new ArrayList<MatchSet>();
            league.setMatchsets(matchsets);
        } else if (qName.equalsIgnoreCase("Matchset")) {            
            matchset = new MatchSet();
        } else if (qName.equalsIgnoreCase("Match")) {
            game = new Game();
        }
    }
    


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tempVal = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("League")) {
            //Añanadirlo a la lista
            myLeague.add(league);
        } else if (qName.equalsIgnoreCase("Matchsets")) {
            team1.setTeamName(tempVal);
            team2.setTeamName(tempVal);
        } else if (qName.equalsIgnoreCase("Team1")) {
            team1.setTeamName(tempVal);
        } else if (qName.equalsIgnoreCase("Team2")) {
            team2.setTeamName(tempVal);
        } else if (qName.equalsIgnoreCase("Score1")) {
            score1.setScore1(tempVal.charAt(0));
        } else if (qName.equalsIgnoreCase("Score2")) {
            score1.setScore2(tempVal.charAt(0));
        } else if (qName.equalsIgnoreCase("Date")) {
            dateTime.setDateTime(Date.from(Instant.parse(tempVal)));
        }

    }

    public static void main(String[] args) {

        System.out.println("SAX");
        System.out.println("---");

        SAXParserLeague spe = new SAXParserLeague();
        spe.runExample();
    }

}
