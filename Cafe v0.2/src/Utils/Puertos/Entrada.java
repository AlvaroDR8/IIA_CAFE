/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Puertos;

import Utils.Slots.Slot;
import org.w3c.dom.Document;

/**
 *
 * @author usuario
 */
public class Entrada {
    
    public void escribirSlotEntrada(Document doc, Slot s){
        s.entra(doc);
    }
}