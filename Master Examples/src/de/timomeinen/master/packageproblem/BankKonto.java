/*
 * BankAccount.java
 *
 * Created on 11. Oktober 2007, 15:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.packageproblem;

/**
 * Ein Bankkonto, dass nur Einzahlungen erlaubt.
 *
 * @author timo
 */
public class BankKonto {
    protected int saldo;
    
    public BankKonto() {
        saldo = 0;
    }
    
    protected void einzahlung(int betrag) {
        saldo += betrag;
    }

    protected String kontostand() {
        return "Kontostand Konto: " + toString();
    }
    
    public String toString() {
        return ("" + saldo);        
    }
}
