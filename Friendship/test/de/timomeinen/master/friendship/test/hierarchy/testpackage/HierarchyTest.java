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

package de.timomeinen.master.friendship.test.hierarchy.testpackage;

import de.timomeinen.master.friendship.exception.FriendshipAccessException;
import de.timomeinen.master.friendship.test.hierarchy.Hierarchy;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class HierarchyTest extends TestCase {
  private static Log logger = LogFactory.getLog(HierarchyTest.class);
  Hierarchy hierarchy;

  protected void setUp() throws Exception {
    super.setUp();
    hierarchy = new Hierarchy();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testChangeProtectedValue() {
    logger.trace("Entering 'testChangeProtectedValue'");

    try {
      hierarchy.protectedValue = 99;
      fail("Zugriff muss abgefangen werden.");
    } catch(FriendshipAccessException e) {
    }
  }

  public void testChangeFriendValue() {
    logger.trace("Entering 'testChangeFriendValue'");
    hierarchy.friendValue = 99;
  }

  public void testPackageAndClassFriend() {
    logger.trace("Entering 'testPackageAndClassFriend'");
    hierarchy.packageAndClassFriend = 99;
  }
}
