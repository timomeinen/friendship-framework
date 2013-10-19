/*
 * BankBean.java
 *
 * Created on 15. Oktober 2007, 13:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.hibernate;

/**
 *
 * @author timo
 */
public class BankBeanReflection {
    private Long id;
    private int saldo;
    
    /** Creates a new instance of BankBean */
    public BankBeanReflection() {
    }
    
    public BankBeanReflection(int firstSaldo) {
        this.saldo = firstSaldo;
    }

    public String toString() {
        return ("Ihr Kontostand: " + this.saldo);
    }
}
