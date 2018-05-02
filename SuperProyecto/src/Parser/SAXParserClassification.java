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

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author 1gdaw07
 */
public class SAXParserClassification extends DefaultHandler {

    ArrayList myClassification;

    private String tempVal;

    private Team team;
    private Team points;

    public SAXParserClassification() {
        myClassification = new ArrayList();
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
            sp.parse("../XML/Classification.xml", this);

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

        System.out.println("La clasificacion es: '" + myClassification.size() + "'.");

        Iterator it = myClassification.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        //inicializamos
        tempVal = "";
        //Crear nuevas instancias d elos objetos
        if (qName.equalsIgnoreCase("Clasificacion")) {
            team = new Team();
            team.setTeamName(attributes.getValue("Nombre del equipo: "));
        } else if (qName.equalsIgnoreCase("Puntos del equipo: ")) {

        }

    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        tempVal = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Clasificacion")) {
            //Añanadirlo a la lista
            myClassification.add(team);
        } else if (qName.equalsIgnoreCase("Equipos ")) {
            team.setTeamName(tempVal);
        } else if (qName.equalsIgnoreCase("Puntos ")) {

        }

    }

    public static void main(String[] args) {

        System.out.println("SAX");
        System.out.println("---");

        SAXParserLeague spe = new SAXParserLeague();
        spe.runExample();
    }
}
