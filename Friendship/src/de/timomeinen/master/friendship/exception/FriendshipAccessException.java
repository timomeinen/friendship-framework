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

package de.timomeinen.master.friendship.exception;

import de.timomeinen.master.friendship.annotations.Friendship;


/**
 * FriendshipAccessExceptions are thrown if an access to a protected element
 * is denied by the Friendship-Framework.
 * <p>
 * The Friendship-Framework generates Exceptions with information about the
 * caller and the callee of the current access. Additionally users are able
 * to extend the exception with an own error message. Therefore it's necessary
 * to annotate the protected element with the {@link Friendship}-annotation and
 * the attribute <code>errorMessage</code> filled with an error message or a
 * useful hint.
 * <p>
 * FriendshipAccessExceptions are unchecked exceptions, which are subclassed
 * {@link RuntimeException}.
 *
 * @author Timo Meinen (master@TimoMeinen.de)
 * @see RuntimeException
 */
public class FriendshipAccessException extends RuntimeException {
  private static final long serialVersionUID = 1131989014329524571L;

  /**
   * Generates an empty exception.
   */
  public FriendshipAccessException() {
    super();
  }

  /**
   * Generates an exception with an error message.
   *
   * @param errorMessage The error message to display before the stack trace
   */
  public FriendshipAccessException(String errorMessage) {
    super(errorMessage);
  }

  /**
   * Generates an exception with an error message, which is found at
   * the {@link Friendship} annotation.
   *
   * @param f The annotation with the error message
   */
  public FriendshipAccessException(Friendship f) {
    super(f.errorMessage());
  }

  /**
   * Generates an exception with detailed error messages.
   *
   * @param errorMessage The general error message
   * @param f The annotation with the detailed error message
   */
  public FriendshipAccessException(String errorMessage, Friendship f) {
    super("\n " + errorMessage + "\n " + f.errorMessage());
  }
}
