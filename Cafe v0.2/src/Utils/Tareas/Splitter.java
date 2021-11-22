/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Tareas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author usuario
 */
public class Splitter {

    public Splitter() {

    }

    public Queue<Document> splitdocumento(Document d) {
        Queue<Document> dNodos = null;

        try {
            dNodos = new LinkedList<>();
            XPath xPath = XPathFactory.newInstance().newXPath();
            XPathExpression exp = xPath.compile("//drinks/drink");
            NodeList nl = (NodeList) exp.evaluate(d, XPathConstants.NODESET);
            System.out.println("se han encontrado " + nl.getLength() + " resultados");

            /*Creamos un builder para crear un documento auxiliar*/
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            /*------------------------------------------------------*/

            for (int i = 0; i < nl.getLength(); i++) {
                Document aux = builder.newDocument();

                /*Generamos el la raiz de los nodos*/
                Element raiz = aux.createElement("drinks");
                aux.appendChild(raiz);
                /*Hacemos un nodo para cada nodo de la nodolist y lo añadimos a la raiz*/

                Node drink = nl.item(i);
                aux.adoptNode(drink);
                raiz.appendChild(drink);
                //System.out.println(aux.getChildNodes().item(0).getChildNodes().item(0).getNodeName());
                dNodos.add(aux);
            }

            
            return dNodos;

//            System.out.println(d.getDocumentElement().getChildNodes().item(1).getChildNodes().item(0).getChildNodes().item(0).getTextContent());
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Splitter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Splitter.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
        return dNodos;
        }
        
    }
}
