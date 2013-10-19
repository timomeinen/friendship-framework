/*
 * PackageProblem.java
 *
 * Created on 11. Oktober 2007, 15:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.packageproblem.packagea;

/**
 *
 * @author timo
 */
public class PackageProblem {
    
    int packagePrivateField;
    public int publicField;
    private int privateField;
    protected int protectedField;
    
    public PackageProblem() {
        packagePrivateField = 1;
        publicField = 2;
        privateField = 3;
        protectedField = 4;
    }
}
