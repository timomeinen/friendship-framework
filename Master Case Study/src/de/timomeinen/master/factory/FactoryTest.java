/*
 * Friendship-Framework -
 * Extended and fine-grained access control in Java
 * Copyright (C) 2008 Timo Meinen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 *
 * Das Friendship-Framework entstand im Rahmen meiner Masterarbeit.
 * Autor:   Timo Meinen (mail@TimoMeinen.de)
 * Quellen: http://TimoMeinen.de
 *
 */

package de.timomeinen.master.factory;

import de.timomeinen.master.factory.product.Product;
import de.timomeinen.master.factory.product.SecondProduct;


/**
 * Das <b>Factory Pattern</b> ist ein Entwurfsmuster der GOF. Es definiert
 * eine Schnittstelle zur Erzeugung von Objekten, womit das System unabhängig
 * von der Art der Erzeugung dieser Produkte wird.
 * <p>
 * Weitere Informationen finden sich u.a. in der Wikipedia unter
 * http://de.wikipedia.org/wiki/Abstrakte_Fabrik
 * <p>
 * Dieser <code>FactoryTest</code> zeigt die Verwendung des Friendship Frameworks,
 * zur Zugriffs-Kontrolle auf die Konstruktoren und die Erzeugung von neuen
 * Objekten.
 * <p>
 * Das Entwurfsmuster laesst sich mit Hilfe des Frameworks konsequent in Java
 * umsetzen, indem die direkte Erzeugung von Objekten ausserhalb der Factory
 * unterbunden wird.
 *
 * @author Timo Meinen
 *
 */
public class FactoryTest {
  /**
   * @param args
   */
  public static void main(String[] args) {
    /*
     * Dieser Abschnitt zeigt die korrekte Verwendung des Factory Patterns
     */
    Factory factory;
    Product product;

    factory = Factory.getFactory(1);
    product = factory.createProduct();

    System.out.println("Produkt: " + product.getPrice());

    /*
     * Dieser Abschnitt zeigt eine falsche Verwendung des Patterns, die
     * aber unter Standard-Java möglich ist und damit das Pattern
     * unterwandert.
     * <p>
     * Hinweis: Die Programmteile sind auskommentiert, damit eine lauffähige
     * Version vorhanden ist. Entfernt man die Kommentare kommt es zu der
     * gewünschten {@link FriendshipAccessException}.
     *
     */

    // Direkte Erzeugung eines Produktes ist nicht möglich
    SecondProduct secondProduct = new SecondProduct();
    System.out.println("Produkt: " + secondProduct.getPrice());

    //		// Aufruf über konkrete Factory ist nicht möglich
    //		FirstFactory fac = new FirstFactory();
    //		FirstProduct firstProduct = (FirstProduct)fac.createProduct();
    //		System.out.println("Produkt aus FirstFactory: " + firstProduct.getPrice());
  }
}
