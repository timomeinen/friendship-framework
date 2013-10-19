/*
 * FeeVisitor.java
 *
 * Created on 15. Oktober 2007, 18:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.visitor.methods;

import de.timomeinen.master.visitor.hierarchy.BankKonto;
import de.timomeinen.master.visitor.hierarchy.BankKunde;

/**
 *
 * @author timo
 */
public class FeeVisitor implements Visitor {
    
    /** Creates a new instance of FeeVisitor */
    public FeeVisitor() {
    }

    public void visit(BankKonto b) {
        System.out.println("Visiting BankKonto");
        // Break of Encapsulation
        b.setSaldo(b.getSaldo() + 5);
    }

    public void visit(BankKunde b) {
        System.out.println("Visiting BankKunde");
    }
    
}
