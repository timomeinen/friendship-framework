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

package de.timomeinen.master.friendship.test.hierarchy;

import de.timomeinen.master.friendship.annotations.Friendship;


public class Hierarchy {
  /*
   * Diese Variable ist geschuetzt. Sie kann nur aus der eigenen Klasse aufgerufen werden
   */
  @Friendship
  public int protectedValue;

  /*
   * Diese Variable ist geschuetzt. Sie kann nur aus der eigenen Klasse aufgerufen werden
   * _UND_ aus der Friend-Klasse AccountTest
   */
  @Friendship(friendPackages = "de.timomeinen.master.friendship.test.hierarchy.testpackage")
  public int friendValue;
  @Friendship(friendClasses = HierarchyCombinedTest.class, friendPackages = "de.timomeinen.master.friendship.test.hierarchy.testpackage")
  public int packageAndClassFriend;
  @Friendship(friendPackages =  {
    "de.timomeinen.master.friendship.test.hierarchy.friendpackage", "de.timomeinen.master.friendship.test.hierarchy.anotherfriendpackage"}
  , friendClasses =  {
    HierarchyCombinedTest.class}
  )
  public int twoPackages;

  public Hierarchy() {
  }

  public Hierarchy(int protectedValue, int friendValue) {
    this.protectedValue = protectedValue;
    this.friendValue = friendValue;
  }
}
