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

package de.timomeinen.master.friendship.test.friendclasses;

import de.timomeinen.master.friendship.exception.FriendshipAccessException;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AccountTest extends TestCase {
  private static Log logger = LogFactory.getLog(AccountTest.class);
  Account account;
  int testValue;

  protected void setUp() throws Exception {
    super.setUp();
    account = new Account(0, 0);
    testValue = 0;
  }

  protected void tearDown() throws Exception {
    super.tearDown();
    account = null;
  }

  public void testChangeProtectedValue() {
    logger.trace("Entering 'testChangeProtectedValue()'");

    try {
      account.protectedValue = 99;
      fail("Zugriff auf protectedValue wurde nicht unterbunden.");
    } catch(FriendshipAccessException e) {
      // Zugriff muss verweigert werden
    }
  }

  public void testChangeFriendValue() {
    logger.trace("Entering 'testChangeFriendValue()'");
    account.friendValue = 99;
  }

  public void testSetProtectedValue() {
    logger.trace("Entering 'testSetProtectedValue()'");
    testValue++;
    account.setProtectedValue(testValue);
    assertEquals(testValue, account.getProtectedValue());
  }

  public void testProtectedMethod() {
    logger.trace("Entering 'testProtectedMethod()'");

    try {
      account.protectedMethod(99);
      fail("Zugriff auf protectedMethod wurde nicht unterbunden.");
    } catch(FriendshipAccessException e) {
      // Zugriff muss verweigert werden
    }
  }

  public void testFriendMethod() {
    logger.trace("Entering 'testFriendMethod()'");
    testValue++;
    account.friendMethod(testValue);
    // Zugriff muss erlaubt werden
    assertEquals(testValue, account.getProtectedValue());
  }

  public void testCallOwnMethod() {
    logger.trace("Entering 'testCallOwnMethod()'");

    testValue++;
    account.callOwnMethod(testValue);
    assertEquals(testValue, account.getProtectedValue());
  }
}
