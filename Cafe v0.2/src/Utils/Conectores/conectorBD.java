/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Conectores;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author usuario
 */
public class conectorBD {

    Connection connection;

    public conectorBD() {
        try {
            //CONEXION BASE DE DATOS
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11451622";
            String user = "sql11451622";
            String pass = "vFCSwragZV";
            System.out.println("Estableciendo conexion a la BD...");
            connection = DriverManager.getConnection(url, user, pass);
            // DESCONEXION BASE DE DATOS
            //connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conectorBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(conectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*EL TRANSLATOR LE PASARA UN STRING TRADUCIDA DE LOS CORTES DEL DOCUMENTO 
    Y LA BASE DE DATOS SACARA UN DOCUMENTOS POR CADA CONSULTA REALIZADA*/
    public Document devolverStock(/*String Sql*/) {
        Document doc = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT stock FROM B_bebidasfrias WHERE nombre= 'coca-cola'");
            while (rs.next()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                doc = builder.parse(new InputSource(new StringReader("<response><stock>" + rs.getInt("stock") + "</stock></response>")));
                System.out.println(doc.getDocumentElement().getTextContent());
                

            }
            rs.close();
            stmt.close();
            return doc;

        } catch (SQLException ex) {
            Logger.getLogger(conectorBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(conectorBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(conectorBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(conectorBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return doc;
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
