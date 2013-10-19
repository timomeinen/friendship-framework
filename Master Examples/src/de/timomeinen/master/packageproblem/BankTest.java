/*
 * BankTest.java
 *
 * Created on 11. Oktober 2007, 16:06
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.packageproblem;

/**
 *
 * @author timo
 */
public class BankTest {

    public static void main(String[] args) {
        BankKonto konto = new BankKonto();
        BankKontoPlus plus = new BankKontoPlus();
        
        System.out.println(konto.kontostand());
        konto.einzahlung(5);
        System.out.println(konto.kontostand());
        
        System.out.println(plus.kontostand());
        plus.einzahlung(10);
        System.out.println(plus.kontostand());
        plus.auszahlung(5);
        System.out.println(plus.kontostand());
        
        System.out.println("Problem: Direkter Zugriff auf die Variable im selben Package");
        plus.saldo = 100;
        System.out.println(plus.kontostand());
    }
    
}
