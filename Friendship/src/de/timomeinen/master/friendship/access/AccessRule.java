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

package de.timomeinen.master.friendship.access;

import de.timomeinen.master.friendship.annotations.Friendship;


/**
 * AccessRules defines rules for accesses to elements (fields and methods)
 * annotated with the @Friendship-Annotation. Users have the ability to define
 * fine-grained access rules by subclassing {@link AccessRule}.
 *
 * The AccessEnforcer will call the hasAccess(..) method and provide it with
 * references to the caller and to the target objects. The subclassed AccessRule
 * is called for every access to a class with elements annotated with the Friendship
 * annotation and an defined attribute accessRule.
 *
 * Subclass an {@link AccessRule} to build your own set of rules for accessing
 * an object. The {@link AccessEnforcement} calls the rule and provides it with
 * references to the caller and the target object. The subclassed {@link AccessRule}
 * can control the access in any fine-grained sense as required.
 *
 * @author timo
 *
 */
public abstract class AccessRule {
  /**
   * The AccessEnforcer will call the hasAccess(..) method and provide it with
   * references to the caller and to the target objects. The subclassed AccessRule
   * is called for every access to a class with elements annotated with the Friendship
   * annotation and an defined attribute accessRule.
   *
   * @param caller Reference to the caller object
   * @param target Reference to the target object
   * @param friend The annotation of the guarded element
   * @return true if the access is granted; false otherwise.
   */
  public abstract boolean hasAccess(Object caller, Object target,
                                    Friendship friend);

  /**
   * The AccessEnforcer will call the hasAccess(..) method, if it is called
   * from or to a static context. As the context is static the AccessEnforcer
   * cannot provide references to objects, but it will give references to the
   * appropriate class-objects, so that a static access control is possible.
   *
   * @param caller Reference to the callers class object
   * @param target Reference to the targets class object
   * @param friend The annotation of the guarded element
   * @return true if the access should be granted; false otherwise.
   */
  public abstract boolean hasStaticAccess(Class caller, Class target,
                                          Friendship friend);
}
