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

package de.timomeinen.master.factory;

import de.timomeinen.master.factory.factories.FirstFactory;
import de.timomeinen.master.factory.factories.SecondFactory;
import de.timomeinen.master.factory.product.Product;


public abstract class Factory {
  public abstract Product createProduct();

  public static Factory getFactory(int auswahl) {
    switch(auswahl) {
      case 1:
        return new FirstFactory();

      case 2:
        return new SecondFactory();

      default:
        System.out.println("Choose factory 1 or 2.");

        return null;
    }
  }
}
