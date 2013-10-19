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

package de.timomeinen.master.inheritance;


/**
 * Der <code>InheritanceTest</code> zeigt eine mögliche Verwendung des
 * Friendship-Frameworks, um einen echten Vererbungsschutz zu realisieren.
 * <p>
 * Der {@link Modifier} 'protected' bietet unter Java den abgeleiteten Klassen
 * und den Klassen im selben Paket Zugriff. Beispielsweise sieht die UMl aber
 * als 'protected' Schutz keinen Zugriff von den Klassen innerhalb eines
 * Paketes vor. Der Zugriff "Abgeleitet Klassen haben Zugriff, Klassen im
 * selben Paket jedoch nicht." lässt sich in Java nicht umsetzen.
 * <p>
 * Mit Hilfe der Friendship-Frameworks und seiner Funktion 'inheritanceAccess'
 * lässt sich aber ein echter Vererbungsschutz umsetzen. Das Beispiel erzeugt
 * Objekte von {@link Entity} und seiner Spezialisierung {@link SubclassedEntity}
 * und lässt die Objekte aufeinander zugreifen. Das Objekt der Klasse {@link Enemy}
 * hat eigentlich auf das protected Element von Entity Zugriff, der jedoch
 * durch das Framework abgefangen wird.
 *
 * @author Timo Meinen
 *
 */
public class InheritanceTest {
  public static void main(String[] args) {
    Entity entity = new Entity();
    System.out.println("Accessed? " + entity);

    SubclassedEntity sub = new SubclassedEntity();
    sub.run(entity);
    System.out.println("Subclass access? " + entity);

    entity = new Entity();
    System.out.println("Accessed? " + entity);

    Enemy enemy = new Enemy();
    enemy.run(entity);
    System.out.println("Enemy Accessed? " + entity);
  }
}
