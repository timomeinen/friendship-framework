/*
 * BankKonto.java
 *
 * Created on 15. Oktober 2007, 18:16
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
public class BankKonto implements Visitable {
    
    private int saldo;
    
    /** Creates a new instance of BankKonto */
    public BankKonto(int initialSaldo) {
        this.setSaldo(initialSaldo);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
}
