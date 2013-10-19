/*
 * BankAccountPlus.java
 *
 * Created on 11. Oktober 2007, 16:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.packageproblem;

/**
 * Ein Bankkonto das auch Auszahlungen erlaubt
 *
 * @author timo
 */
public class BankKontoPlus extends BankKonto {
    
    public BankKontoPlus() {
        super();        
    }
    
    protected void auszahlung(int betrag) {
        saldo -= betrag;
    }
    
    @Override
    protected String kontostand() {
        return "Kontostand Konto Plus: " + toString();
    }
    
}
