/*
 * DerivedCalss.java
 *
 * Created on 11. Oktober 2007, 15:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.packageproblem.packageb;

import de.timomeinen.master.packageproblem.packagea.PackageProblem;

/**
 *
 * @author timo
 */
public class DerivedClass extends PackageProblem{
    
    public void performTest() {
        System.out.println("\nDerived Class in other package");
        // System.out.println("Package Private (default) : " + packagePrivateField); // Compile Error
        System.out.println("Protected: " + protectedField);
        System.out.println("Public: " + publicField);
    
    }
    
}
