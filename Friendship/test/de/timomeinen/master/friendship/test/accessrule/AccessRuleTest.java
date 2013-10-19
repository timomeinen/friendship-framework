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

import de.timomeinen.master.friendship.exception.FriendshipAccessException;
import de.timomeinen.master.friendship.test.accessrule.AccessRuleEntity.MemberClass;
import de.timomeinen.master.friendship.test.accessrule.mix.MixTest;

import junit.framework.TestCase;


public class AccessRuleTest extends TestCase {
  public void testAccessRule() {
    AccessRuleEntity ent = new AccessRuleEntity();

    try {
      ent.fieldWithOwnAccessRule = 100;
      fail("Zugriff muss abgefangen werden.");
    } catch(FriendshipAccessException e) {
    }
  }

  public void testAccessRuleAllowed() {
    AccessRuleEntity ent = new AccessRuleEntity();
    ent.access = true;
    ent.fieldWithOwnAccessRule = 100;
  }

  public void testMemberClasses() {
    AccessRuleEntity ent = new AccessRuleEntity();
    MemberClass mc = ent.new MemberClass();
    mc.timoval = 100;
    System.out.println(mc.timoval);
  }

  public void testMix() {
    MixTest mt = new MixTest();
    AccessRuleEntity ent = new AccessRuleEntity();
    MemberClass mc = ent.new MemberClass();

    mt.run(mc);
  }
}
