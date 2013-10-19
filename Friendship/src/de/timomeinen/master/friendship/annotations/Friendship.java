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

package de.timomeinen.master.friendship.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import de.timomeinen.master.friendship.access.AccessRule;

/**
 * Protects fields, methods and constructors from access outside this class.
 * <p>
 * Can be used to simulate <b>Friendship</b> of classes. If you want to let some
 * other classes have access, define them as friends.
 * <p>
 * Annotated class' constructors prevent the creation of new objects with this
 * special constructor for anyone else than friends. Annotated classes prevents
 * the creation of new objects with <b>any</b> constructor for anyone except 
 * friends.
 * 
 * @author Timo Meinen
 *
 */
@Target({FIELD, METHOD, TYPE, CONSTRUCTOR}) 
@Retention(RUNTIME)
@Inherited
public @interface Friendship {

	/**
	 * Friend classes have access to the elements (fields and methods)
	 * annotated by this annotation.
	 */
	Class<?>[]	friendClasses()		default {Friendship.class};
	
	/**
	 * Classes in a friendly package have access to the elements (fields and methods)
	 * annotated by this annotation.
	 * 
	 * You can use wildcards '*' and '?' to select hierarchies of packages.
	 * 
	 * Example:
	 * Friendship(friendPackages = "de.timomeinen.master.*")
	 * 
	 * If you want to declare more than one package as friendly, you have to use
	 * an array. Example:
	 * 
	 * Friendship(friendPackages = {"de.timomeinen.*", "org.timomeinen.*"})
	 */
	String[]	friendPackages() 	default "";

	/**
	 * FriendMethods within a class have access to the protected element.
	 * <p>
	 * This specializes the friendClasses attribute, because methods in a class
	 * can be selected. This behaviour is like friend-functions in C++.
	 * <p>
	 * Define the methods with the fully qualified name of the class and the
	 * method-name, followed by (). Wildcard entries with '*' and '?' are possible, too.
	 * <p>
	 * Multiple methods have to be declared in an array of strings.
	 * <p>
	 * Examples:
	 * de.timomeinen.master.ClassA.testMethod()
	 * de.timomeinen.master.ClassA.test*()
	 * de.timomeinen.*
	 */
	String[]	friendMethods()		default "";

	/**
	 * This error message is printed to the logger, if an access is denied.
	 * It is an optional feature.
	 * 
	 * You can give useful hints, e.g. which method to use instead of this
	 * protected one.
	 *
	 */
	String		errorMessage()		default "";
	
	/**
	 * Package access is like the 'normal' package-private mode (an element
	 * without any modifiers). All classes in a package can access this element.
	 * 
	 * It is an optional feature.
	 */
	boolean		packageAccess()		default false;
	
	/**
	 * Caller classes are accepted, if they are subclasses of a friendly class
	 * or if they implement a friendly interface.
	 * 
	 * Attention: This option works on all friends declared in friendClasses.
	 * 
	 * It is an optional feature.
	 */
	boolean		inheritanceAccess()	default false;
	
	/**
	 * Defines a class with an access rule. Subclass an {@link AccessRule} to
	 * build your own set of rules for accessing an object. The AccessEnforcement
	 * calls the rule and provides it with references to the caller and the target
	 * object. The subclassed {@link AccessRule} can control the access in any
	 * fine-grained sense as required.
	 */
	Class<? extends AccessRule>	accessRule() default AccessRule.class;
}