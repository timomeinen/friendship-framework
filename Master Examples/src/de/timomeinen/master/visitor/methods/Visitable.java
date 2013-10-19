/*
 * Visitable.java
 *
 * Created on 15. Oktober 2007, 18:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.visitor.methods;

/**
 *
 * @author timo
 */
public interface Visitable {
    public abstract void accept(Visitor v);
}
