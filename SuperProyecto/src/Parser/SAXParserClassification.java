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

import org.xml.sax.helpers.DefaultHandler;

/**
 * Esta clase se encarga de parsear el documento Classification.xml y de devolver
 * un ArrayList que contiene objetos TeamSax
 * @author Sergio Zulueta
 * @version %I% %G%
 * @since 1.0
 */
public class SAXParserClassification extends DefaultHandler {

    /**
     * Creacion de los atributos Classification, tempVal, tema.
     */
    static ArrayList Classification;
    private String tempVal;
    //Para mantener el contexto
    private TeamSax team;

    /**
     * Inicializamos el ArrayList
     */
    public SAXParserClassification() {
        Classification = new ArrayList();
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
        System.out.println("En la clasificacion estan : '" + Classification.size() + "' equipos.");
        Iterator it = Classification.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }

    }

    /**
     *
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
        if (qName.equalsIgnoreCase("team")) {
            team = new TeamSax();
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
     *
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
        if (qName.equalsIgnoreCase("team")) {
            Classification.add(team);
        } else if (qName.equalsIgnoreCase("name")) {
            team.setName(tempVal);
        } else if (qName.equalsIgnoreCase("points")) {
            team.setPoints(Integer.parseInt(tempVal));
        }
    }

    /**
     * Se encarga de ejecutar todo el parser
     *
     * @return un ArrayList compuesto de objetos TeamSax
     */
    public static ArrayList executeSAXClassification() {

        System.out.println("SAX");
        System.out.println("---");

        SAXParserClassification spe = new SAXParserClassification();
        spe.runExample();
        return Classification;
    }
}
