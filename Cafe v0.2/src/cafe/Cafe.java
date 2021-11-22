/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cafe;

import Utils.Conectores.conectorBD;
import Utils.Conectores.lecturaComandas;
import Utils.Puertos.Entrada;
import Utils.Slots.Slot;
import Utils.Tareas.Splitter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

/**
 *
 * @author Aburo Senpai
 */
public class Cafe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //CREAMOS UNA COLA AUXILIAR PARA LOS SPLITS Y LO QUE HAGA FALTA
        Queue<Document> aux = new LinkedList<>();
        //CONEXION A LA BASE DE DATOS
        conectorBD c = new conectorBD();
        c.devolverStock();

        //HACEMOS LA LECTURA DE LAS COMANDAS
        lecturaComandas a = new lecturaComandas();
        Entrada pEntradaComandas = new Entrada();
        Slot deEntradaASplitter = new Slot();
        Slot deSplitterAdist = new Slot();
        Splitter deSplitteraDist = new Splitter();

        //conectamos el puerto de entrada y pasamos los datos
        //por la entrada del slot
        pEntradaComandas.escribirSlotEntrada(a.lecturaComandas(1), deEntradaASplitter); 
        //METEMOS EL DOCUMENTO DEL SLOT AL SPLITER Y DEVOLVEMOS UNA QUEUE DE DOCUMENTOS Y DENTRO CADA NODO LEIDO "<drinks><drink><name>...<type>...</drink></drinks>
        //CREAMOS UNA QUEUE PARA PODER ITERAR LA LISTA Y PASAR CADA DOCUMENTO DE LA QUEUE DE UNO EN UNO AL SLOT QUE VA AL DISTRIBUTOR
        aux = deSplitteraDist.splitdocumento(deEntradaASplitter.sale());
        int j= aux.size();
        for (int i = 0; i < j; i++) {
            System.out.println(aux.element().getChildNodes()./*item(0).getChildNodes().item(0).getChildNodes().item(1).getNodeName());*/item(0).getTextContent());
            deSplitterAdist.entra(aux.poll()); //Sacamos lo que hemos metido dentro del slot //Esto lo tenemos que pasar por referencia al splitter
        }

        try {
            c.getConnection().close();
            System.out.println("Se ha desconectado de la base de datos");
        } catch (SQLException ex) {
            Logger.getLogger(Cafe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
