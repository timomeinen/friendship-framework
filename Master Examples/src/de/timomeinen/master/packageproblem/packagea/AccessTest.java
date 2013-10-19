/*
 * AccessTest.java
 *
 * Created on 11. Oktober 2007, 15:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.packageproblem.packagea;

import de.timomeinen.master.packageproblem.packageb.OtherPackage;

/**
 *
 * @author timo
 */
public class AccessTest {
    
    
    public static void main(String[] args) {
        
        PackageProblem problem = new PackageProblem();
        
        System.out.println("Klasse im selben Paket");
        System.out.println("Package Private (default): " + problem.packagePrivateField);
        // System.out.println("Private: " + problem.privateField); // Compiler Error
        System.out.println("Protected: " + problem.protectedField);
        System.out.println("Public: " + problem.publicField);
        
        new OtherPackage().performTest();
        new de.timomeinen.master.packageproblem.packagea.DerivedClass().performTest();
        new de.timomeinen.master.packageproblem.packageb.DerivedClass().performTest();
        
    }
    
}
