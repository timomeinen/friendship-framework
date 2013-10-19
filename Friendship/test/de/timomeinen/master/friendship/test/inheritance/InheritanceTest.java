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

package de.timomeinen.master.friendship.test.inheritance;

import de.timomeinen.master.friendship.test.inheritance.visitor.AnotherVisitor;
import de.timomeinen.master.friendship.test.inheritance.visitor.YetAnotherVisitor;

import junit.framework.TestCase;


public class InheritanceTest extends TestCase {
  Visitor visitor;
  Entity entity;

  protected void setUp() throws Exception {
    super.setUp();
    entity = new Entity();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testInheritanceTestVisitor() {
    visitor = new TestVisitor();
    visitor.run(entity);
  }

  public void testInheritanceAnotherVisitor() {
    visitor = new AnotherVisitor();
    visitor.run(entity);
  }

  public void testInheritanceYetAnotherVisitor() {
    visitor = new YetAnotherVisitor();
    visitor.run(entity);
  }

  public void testInterface() {
    VisitableClass clazz = new VisitableClass();

    clazz.run(entity);
  }
}
