/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Conectores;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;


/**
 *
 * @author usuario
 */
public class lecturaComandas {

    int numcom;

    public Document lecturaComandas(int numcom) {
        Document doc = null;
        try {

            this.numcom = numcom;
            System.out.println("Procesando comanda: order" + this.numcom + ".xml.");
            //Leemos la comanda
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse("src/Utils/Comandas/order" + this.numcom + ".xml");
            
            return doc;

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(lecturaComandas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(lecturaComandas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(lecturaComandas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return doc;
        }

    }
}
