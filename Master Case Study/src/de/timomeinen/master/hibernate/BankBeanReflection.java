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

package de.timomeinen.master.hibernate;

import de.timomeinen.master.friendship.annotations.Friendship;


/**
 *
 * @author timo
 */
public class BankBeanReflection {
  private Long id;
  @Friendship
  private int saldo;

  /** Creates a new instance of BankBean */
  public BankBeanReflection() {
  }

  public BankBeanReflection(int firstSaldo) {
    this.saldo = firstSaldo;
  }

  public String toString() {
    return ("Ihr Kontostand: " + this.saldo);
  }

  @Friendship(errorMessage = "Please use the toString() method.")
  public int getSaldo() {
    return saldo;
  }

  @Friendship
  public void setSaldo(int saldo) {
    this.saldo = saldo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
