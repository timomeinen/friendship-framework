/*
 * BankKunde.java
 *
 * Created on 15. Oktober 2007, 18:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.visitor.hierarchy;

import de.timomeinen.master.visitor.methods.Visitable;
import de.timomeinen.master.visitor.methods.Visitor;

/**
 *
 * @author timo
 */
public class BankKunde implements Visitable {
    private String name;
    private BankKonto konto;
    
    /** Creates a new instance of BankKunde */
    public BankKunde(String name, int initialSaldo) {
        this.name = name;
        konto = new BankKonto(initialSaldo);
    }

    public void accept(Visitor v) {
        konto.accept(v);
        v.visit(this);
    }
    
    public String toString() {
        return ("Kontostand: " + konto.getSaldo());
    }
}
