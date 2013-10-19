/*
 * DerivedClass.java
 *
 * Created on 11. Oktober 2007, 15:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.packageproblem.packagea;

/**
 *
 * @author timo
 */
public class DerivedClass extends PackageProblem {
    
    public void performTest() {
        System.out.println("\nDerived Class in same package");
        System.out.println("Package Private (default) : " + packagePrivateField);
        System.out.println("Protected: " + protectedField);
        System.out.println("Public: " + publicField);
        // System.out.println("Private: " + privateField); // Compiler Error
    }
}
