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

package de.timomeinen.master.visitor.hierarchy;

import de.timomeinen.master.friendship.annotations.Friendship;
import de.timomeinen.master.visitor.methods.Visitable;
import de.timomeinen.master.visitor.methods.Visitor;
import de.timomeinen.master.visitor.processes.Cashpoint;


/**
 *
 * @author timo
 */
public class BankKonto implements Visitable {
  private int saldo;

  /** Creates a new instance of BankKonto */
  public BankKonto(int initialSaldo) {
    this.setSaldo(initialSaldo);
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  @Friendship(friendClasses =  {
    Visitor.class, BankKunde.class, BankKontoAccessRule.class}
  , inheritanceAccess = true, errorMessage = "Bitte verwende die Methode hebeAb(int)")
  private int getSaldo() {
    return saldo;
  }

  @Friendship(friendClasses = Visitor.class, inheritanceAccess = true, errorMessage = "Direkter Zugriff nur für die Visitor möglich.")
  private void setSaldo(int saldo) {
    this.saldo = saldo;
  }

  /**
   * Geschäftsmethode zum Abheben von Geld. Beispielsweise von
   * einem Geldautomaten (Cashpoint).
   *
   * Überprüft, ob genug Geld auf dem Konto ist. Dispo wird
   * nicht gewährt.
   *
   * @param betrag Der abzuhebende Betrag
   * @return true, falls das abheben funktioniert; sonst false.
   */
  @Friendship(accessRule = BankKontoAccessRule.class, errorMessage = "Das Saldo ist negativ.")
  public boolean hebeAb(int betrag) {
    //    	if (betrag > saldo)
    //    		return false;
    //    	else {
    saldo -= betrag;

    return true;

    //    	}
  }
}
