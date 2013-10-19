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

package de.timomeinen.master.friendship.test.declaremethods;

import de.timomeinen.master.friendship.exception.FriendshipAccessException;

import junit.framework.TestCase;


public class DeclareMethodTest extends TestCase {
  Entity e;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    e = new Entity();
  }

  public void testMethodA() {
    e.methodA();

    try {
      e.methodB();
      fail("Zugriff muss abgefangen werden.");
    } catch(FriendshipAccessException e) {
    }

    e.methodWildcard();
  }

  public void testMethodB() {
    try {
      e.methodA();
      fail("Zugriff muss abgefangen werden.");
    } catch(FriendshipAccessException e) {
    }

    e.methodB();
    e.methodWildcard();
  }

  public void testMethodAB() {
    e.methodA();
    e.methodB();
    e.methodWildcard();
  }

  public void testMethodAntiWildcard() {
    e.methodWildcard();

    try {
      e.methodA();
      fail("Zugriff muss abgefangen werden.");
    } catch(FriendshipAccessException e) {
    }

    try {
      e.methodB();
      fail("Zugriff muss abgefangen werden.");
    } catch(FriendshipAccessException e) {
    }
  }
}
