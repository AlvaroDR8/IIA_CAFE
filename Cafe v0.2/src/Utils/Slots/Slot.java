/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Slots;

import java.util.LinkedList;
import java.util.Queue;
import org.w3c.dom.Document;

/**
 *
 * @author usuario
 */
public class Slot {
    private final Queue<Document> datos;
    Document aux;
    public Slot(){
        datos = new LinkedList<>();
    }
    public Document sale(){
        aux=datos.poll();
        return aux;
    }
    public void entra(Document doc){
        datos.add(doc);
    }
}
