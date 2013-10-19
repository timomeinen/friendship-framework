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

package de.timomeinen.master.friendship.test;

import de.timomeinen.master.friendship.test.accessrule.AccessRuleTest;
import de.timomeinen.master.friendship.test.constructor.ConstructorTest;
import de.timomeinen.master.friendship.test.declaremethods.DeclareMethodTest;
import de.timomeinen.master.friendship.test.friendclasses.AccountTest;
import de.timomeinen.master.friendship.test.hierarchy.HierarchyCombinedTest;
import de.timomeinen.master.friendship.test.hierarchy.HierarchyEnemyTest;
import de.timomeinen.master.friendship.test.hierarchy.MultiplePackageTest;
import de.timomeinen.master.friendship.test.hierarchy.testpackage.HierarchyTest;
import de.timomeinen.master.friendship.test.inheritance.InheritanceTest;
import de.timomeinen.master.friendship.test.methods.AllMethodTest;
import de.timomeinen.master.friendship.test.packageAccess.PackageAccessTest;
import de.timomeinen.master.friendship.test.staticparts.StaticTest;
import de.timomeinen.master.friendship.test.wildcard.WildcardTest;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AllTests {
  private static Log logger = LogFactory.getLog(AllTests.class);

  public static Test suite() {
    logger.info("Starting JUnit Testsuit 'AllTests'");

    TestSuite suite = new TestSuite("Test for Friendship-Framework");

    //$JUnit-BEGIN$
    suite.addTestSuite(AccountTest.class);
    suite.addTestSuite(HierarchyTest.class);
    suite.addTestSuite(HierarchyEnemyTest.class);
    suite.addTestSuite(HierarchyCombinedTest.class);
    suite.addTestSuite(MultiplePackageTest.class);
    suite.addTestSuite(PackageAccessTest.class);
    suite.addTestSuite(WildcardTest.class);
    suite.addTestSuite(InheritanceTest.class);
    suite.addTestSuite(AccessRuleTest.class);
    suite.addTestSuite(AllMethodTest.class);
    suite.addTestSuite(ConstructorTest.class);
    suite.addTestSuite(DeclareMethodTest.class);
    suite.addTestSuite(StaticTest.class);

    //$JUnit-END$
    return suite;
  }
}
