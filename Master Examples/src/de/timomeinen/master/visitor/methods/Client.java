/*
 * Client.java
 *
 * Created on 15. Oktober 2007, 18:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.visitor.methods;

import de.timomeinen.master.visitor.hierarchy.BankKunde;

/**
 *
 * @author timo
 */
public class Client {
    
    public static void main(String[] args) {
        BankKunde kunde = new BankKunde("Timo", 100);
        FeeVisitor fee = new FeeVisitor();
        System.out.println(kunde);
        kunde.accept(fee);
        System.out.println(kunde);
    }
    
}
