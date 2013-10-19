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

package de.timomeinen.master.user;

import de.timomeinen.master.friendship.access.AccessRule;
import de.timomeinen.master.friendship.annotations.Friendship;


public class UserAccessRule extends AccessRule {
  @Override
  public boolean hasAccess(Object caller, Object target, Friendship friends) {
    User user = (User) caller;

    switch(user.role) {
      case ADMIN:
        System.out.println("UserAccessRule: Admin access granted");

        return true;

      case USER:
        System.out.println("UserAccessRule: User access granted");

        return true;

      default:
        return false;
    }
  }

  @Override
  public boolean hasStaticAccess(Class arg0, Class arg1, Friendship arg2) {
    // NO static access allowed!
    return false;
  }
}
