/*
 * OtherPackage.java
 *
 * Created on 11. Oktober 2007, 15:29
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
public class OtherPackage {
    
    
    public void performTest() {
        PackageProblem problem = new PackageProblem();
        
        System.out.println("\nKlasse im anderen Paket");
        // System.out.println("Package Private (default): " + problem.packagePrivateField); // Compile Error
        // System.out.println("Private: " + problem.privateField); // Compiler Error
        // System.out.println("Protected: " + problem.protectedField); // Compile Error
        System.out.println("Public: " + problem.publicField);
    }
    
}
