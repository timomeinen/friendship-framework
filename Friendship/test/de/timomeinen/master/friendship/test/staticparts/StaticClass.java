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

package de.timomeinen.master.friendship.test.staticparts;

import de.timomeinen.master.friendship.test.friendclasses.Account;


public class StaticClass {
  private StaticClass() {
  }

  public static void enemyStatic(Account account) {
    account.protectedValue = 99;
  }

  public static void friendStatic(Account account) {
    account.friendValue = 99;
  }

  public static void protectedStaticMethod() {
    Account.protectedStaticMethod();
  }

  public static void friendlyStaticMethod() {
    Account.friendlyStaticMethod();
  }
}
