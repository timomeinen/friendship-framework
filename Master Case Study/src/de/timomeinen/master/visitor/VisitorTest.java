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

package de.timomeinen.master.visitor;

import de.timomeinen.master.visitor.hierarchy.BankKunde;
import de.timomeinen.master.visitor.methods.FeeVisitor;
import de.timomeinen.master.visitor.methods.InterestVisitor;
import de.timomeinen.master.visitor.processes.Cashpoint;


/**
 * Das Visitor-Pattern kapselt Operationen, die auf Elemente einer
 * Objektstruktur angewendet werden können. Weiter Informationen finden
 * sich u.a. in der Wikipedia unter http://de.wikipedia.org/wiki/Visitor_pattern
 * <p>
 * Dieses Beispiel zeigt die Möglichkeit Methoden und Felder für Visitor
 * zugreifbar zu machen, obwohl diese <code>private</code> deklariert sind.
 * Die Sichtbarkeitsmodifikatoren werden durch Bearbeitung des abstrakten
 * Syntax Baums (AST) verändert und anschliessend mit dem Friendship-Framework
 * verknüpft.
 * <p>
 * Desweiteren zeigt das Beispiel, wie öffentliche Methoden vor falschem Zugriff
 * geschützt werden können. Die Klasse {@link BankKonto} bietet öffentliche
 * Accessormethoden zum Zugriff auf das Feld 'saldo', das im Normalfall nicht
 * direkt verändert werden soll, sondern nur über die korrekten Geschäftsmethoden.
 * Die Accessormethoden sind jedoch für die Visitor wichtig und müssen für diese
 * zur Verfügung stehen.
 * <p>
 * Im Beispiel hebt der {@link FeeVisitor} Kontoführungsgebühren vom Konto ab und
 * kann damit das Konto auch ins Minus bringen. Da es sich um ein Studentenkonto
 * handelt, wird selbstverständlich kein Dispokredit gewährt, wesehalb der
 * Geldautomat ({@link CashPoint}) nur dann Geld abheben kann, wenn der Saldo
 * positiv ist. Daher <b>muss</b> der Geldautomat auf die Geschäftsmethoden
 * zugreifen.
 * <p>
 * Das Friendship-Framework stellt in diesem Beispiel genau dieses Verhalten
 * sicher.
 *
 * @author Timo Meinen
 */
public class VisitorTest {
  public static void main(String[] args) {
    BankKunde kunde = new BankKunde("Timo", 100);
    System.out.println(kunde);

    FeeVisitor fee = new FeeVisitor();
    kunde.accept(fee);
    System.out.println(kunde);

    InterestVisitor intVis = new InterestVisitor();
    kunde.accept(intVis);
    System.out.println(kunde);

    new Cashpoint().doSomeBankingBusinessProcesses(kunde);
    System.out.println(kunde);

    new Cashpoint().doSomeBankingBusinessProcesses(kunde);
    System.out.println(kunde);
  }
}
