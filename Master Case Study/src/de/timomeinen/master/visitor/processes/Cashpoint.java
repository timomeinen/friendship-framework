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

package de.timomeinen.master.visitor.processes;

import de.timomeinen.master.visitor.hierarchy.BankKonto;
import de.timomeinen.master.visitor.hierarchy.BankKunde;


public class Cashpoint {
  public void doSomeBankingBusinessProcesses(BankKunde kunde) {
    BankKonto konto = kunde.getKonto();

    int betrag = 100;
    /*
     * Der Automat will 100 Euro auszahlen und verwendet die
     * falsche Methode. Er sollte lieber die korrekte
     * Geschäftsmethode verwenden, statt direkt auf den
     * Attributen zu agieren. Eine Möglichkeit wäre die Methode
     * hebeAb(int betrag) zu verwenden.
     */

    //		konto.setSaldo(konto.getSaldo() - betrag);
    System.out.println("Cashpoint: Hebe ab: " + betrag);

    if(!konto.hebeAb(betrag)) {
      System.out.println("Konnte nicht abheben, weil kein Dispo gewährt wird!");
    }
  }
}
