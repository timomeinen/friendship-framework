package de.timomeinen.master.mutable;

import java.util.logging.Logger;

/**
 * @author  Timo Meinen
 * @author  <a href="mailto:jcm@TimoMeinen.de">jcm@TimoMeinen.de</a>
 * @author  <a target="_blank" href="http://www.TimoMeinen.de">www.TimoMeinen.de</a>
 *
 * MutableDemonstration.java
 * Created on 9. Oktober 2007, 16:38
 */

class Immutable {
    private boolean status = false;
    
    public void setStatus(boolean s) {
        status = s;
    }
    
    public boolean getStatus() {
        return status;
    }
}

class Mutable {
    private boolean[] status = {false, false, false};

    public boolean[] getStatus() {
        return status;
    }
    
    public boolean[] getStatusImmutable() {
        // Eine Loesung kann ein neues Objekt sein, das kopiert bzw. geklont wird
        return status.clone();
    }

    public void setStatus(boolean[] s) {
        this.status = s;
    }
}

public class MutableDemonstration {
    
    private static Logger logger = Logger.getLogger("de.timomeinen.master.mutable");
    
    public static void main(String[] args) {
        System.out.println("Starting (Im)Mutable Demonstration");
        System.out.println("==================================");
        
        System.out.println("Part I: Getting an _immutable_ copy of class Immutable->status");
        System.out.println("--------------------------------------------------------------");
        Immutable immutable = new Immutable();
        
        // Booleans sind immutable weshalb eine Kopie des primitiven Typs uebergeben wird
        boolean immutableCopy = immutable.getStatus();
        
        // Veraenderung der Kopie
        immutableCopy = true;
        
        // Ergebnis
        System.out.println("Reference: " +immutable.getStatus()+ " Lokal: " +immutableCopy+ "\n");
        
        System.out.println("Part II: Getting an _mutable_ reference to class Mutable->status[]");
        System.out.println("----------------------------------------------------------------");
        Mutable mutable = new Mutable();
        
        // Jedes Array ist mutable. Es wird also eine Referenz uebergeben
        boolean[] mutableReference = mutable.getStatus();
        
        //Veraenderung der Daten anhand der Referenz
        mutableReference[0] = true;
        mutableReference[2] = true;
        
        // Neue Referenz auf das Original
        boolean[] originalArray = mutable.getStatus();
        
        // Ergebnis
        for (int i=0; i<mutableReference.length; i++)
            System.out.println(i+ ": Reference: " +originalArray[i]+ " Lokal: " +mutableReference[i]);
        
        System.out.println("\nPart III: Getting an _immutable_ reference to class Mutable->status[]");
        System.out.println("---------------------------------------------------------------------");
        Mutable solution = new Mutable();
        
        // Der Clone ist ein eigenes Objekt, daher ist dieser Aufruf immutable
        boolean[] immutableReference = solution.getStatusImmutable();
        
        //Veraenderung der Daten anhand der Referenz
        immutableReference[0] = true;
        immutableReference[2] = true;
        
        // Neue Referenz auf das Original
        originalArray = solution.getStatus();
        
        // Ergebnis
        for (int i=0; i<immutableReference.length; i++)
            System.out.println(i+ ": Reference: " +originalArray[i]+ " Lokal: " +immutableReference[i]);
    }
    
}
