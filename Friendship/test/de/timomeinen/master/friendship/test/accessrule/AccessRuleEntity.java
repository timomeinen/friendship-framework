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

package de.timomeinen.master.friendship.test.accessrule;

import de.timomeinen.master.friendship.annotations.Friendship;
import de.timomeinen.master.friendship.test.accessrule.OwnAccessRule.MemberAccessRule;
import de.timomeinen.master.friendship.test.accessrule.mix.MixTest;


public class AccessRuleEntity {
  @Friendship(accessRule = OwnAccessRule.class, errorMessage = "Nur 'OwnAccessRule' darf darauf zugreifen.")
  public int fieldWithOwnAccessRule;
  boolean access = false;

  public class MemberClass {
    @Friendship(accessRule = MemberAccessRule.class, errorMessage = "Eigene Regel umgesetzt!", friendClasses = MixTest.class)
    public int timoval;
  }
}
