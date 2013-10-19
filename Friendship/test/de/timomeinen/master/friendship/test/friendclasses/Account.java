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

import de.timomeinen.master.friendship.annotations.Friendship;
import de.timomeinen.master.friendship.test.staticparts.StaticClass;
import de.timomeinen.master.friendship.test.staticparts.StaticTest;


public class Account {
  @SuppressWarnings("unused")
  @Friendship
  private int privateField;

  /*
   * Diese Variable ist geschuetzt. Sie kann nur aus der eigenen Klasse aufgerufen werden
   */
  @Friendship
  public int protectedValue;

  /*
   * Diese Variable ist geschuetzt. Sie kann nur aus der eigenen Klasse aufgerufen werden
   * _UND_ aus der Friend-Klasse AccountTest
   */
  @Friendship(friendClasses =  {
    AccountTest.class, StaticClass.class}
  )
  public int friendValue;

  public Account() {
  }

  public Account(int protectedValue, int friendValue) {
    this.protectedValue = protectedValue;
    this.friendValue = friendValue;
    privateField = 99;
  }

  /*
   * Diese Methode ist _nicht_ geschuetzt. Sie kann die geschuetzte Variable veraendern.
   */
  public void setProtectedValue(int protectedValue) {
    this.protectedValue = protectedValue;
  }

  /*
   * Diese Methode ist geschuetzt. Sie kann nur aus der eigenen Klasse aufgerufen werden
   *
   * @see        #callOwnMethod
   */
  @Friendship
  public void protectedMethod(int newVal) {
    protectedValue = newVal;
  }

  @Friendship
  public static void protectedStaticMethod() {
  }

  @Friendship(friendClasses =  {
    StaticClass.class, StaticTest.class}
  )
  public static void friendlyStaticMethod() {
  }

  /*
   * Diese Methode ist geschuetzt. Sie kann nur aus der eigenen Klasse aufgerufen werden
   * _UND_ aus der Friend-Klasse AccountTest
   */
  @Friendship(friendClasses = AccountTest.class)
  public void friendMethod(int newVal) {
    protectedValue = newVal;
  }

  /*
   * Diese Methode hat package-private-visibility. Sie kann von allen Klassen des Paketes aufgerufen
   * werden. Sie greift aber auf die geschuetzte Methode zu.
   */
  void callOwnMethod(int newVal) {
    protectedMethod(newVal);
  }

  /*
   * Eine 'normale' oeffentliche Methode
   */
  public String toString() {
    return "Protected: " + protectedValue + " FriendValue: " + friendValue;
  }

  /**
   * For Test-purposes only!
   * @return Value of protectedValue
   */
  public int getProtectedValue() {
    return protectedValue;
  }

  /**
   * For Test-purposes only!
   * @return Value of friendValue
   */
  public int getFriendValue() {
    return friendValue;
  }
}
