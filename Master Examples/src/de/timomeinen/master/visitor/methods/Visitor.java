/*
 * Visitor.java
 *
 * Created on 15. Oktober 2007, 18:10
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
public abstract interface Visitor {
//    public abstract void visit(Object o);
    
    public void visit(BankKunde b);
    public void visit(BankKonto b);
}
