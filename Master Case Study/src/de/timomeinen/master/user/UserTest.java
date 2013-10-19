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

package de.timomeinen.master.user;


/**
 * Das Friendship-Framework bietet die Möglichkeit eigene Regelwerke zu
 * schreiben, um Zugriffsentscheidungen zur Laufzeit auszuführen. Das
 * Framework übergibt dazu den eigenen Regelklassen die Referenzen auf die
 * Objekte, also auf den Aufrufer als auch den Aufgerufenen. Dadurch lassen
 * sich auch die Instanzvariablen als Grundlage für Regeln einsetzen.
 * <p>
 * Das User-Beispiel zeigt die Verwendung einer eigenen Regelklasse, die von
 * {@link AccessRule} erbt und eine Regel umsetzt, die überprüft, ob eine
 * Benutzer-Klasse ({@link User}) einer bestimmten Rolle angehört, wie es in
 * RBAC-basierten Zugriffskontrollen üblich ist.
 * <p>
 * Dieses Beispiel zeigt auch, dass das Framework an eigene Bedürfnisse anpassbar
 * ist und weitgehende Zugriffskontrollen ermöglicht.
 * <p>
 * Weitere Informationen zur Role-Based-Access-Control (RBAC) finden sich in
 * der Masterarbeit oder z.B. in der Wikipedia unter http://de.wikipedia.org/wiki/RBAC.
 *
 * @author Timo Meinen
 *
 */
public class UserTest {
  public static void main(String[] args) {
    /*
     * Jeder Benutzer erhält eine Rolle
     */
    User user = new User(User.Role.USER);
    User admin = new User(User.Role.ADMIN);
    User guest = new User(User.Role.GUEST);

    BusinessClass business = new BusinessClass();

    System.out.println("Admin doeing userStuff: ");
    admin.doUserStuff(business);
    System.out.println("\nAdmin doeing adminStuff: ");
    admin.doAdminStuff(business);

    System.out.println("\nUser doeing userStuff: ");
    user.doUserStuff(business);
    System.out.println("\nUser doeing adminStuff: ");
    user.doAdminStuff(business);

    System.out.println("\nGuest doeing userStuff: ");
    guest.doUserStuff(business);
    System.out.println("\nGeust doeing adminStuff: ");
    guest.doAdminStuff(business);
  }
}
