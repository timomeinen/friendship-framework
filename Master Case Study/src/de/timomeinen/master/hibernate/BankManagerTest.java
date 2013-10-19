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

package de.timomeinen.master.hibernate;

import org.hibernate.Session;

import java.util.List;


/**
 * Diese Hibernate Beispiel zeigt die Verwendung des Friendship Frameworks
 * zusammen mit der Persistenzlösung von Hibernate. Hibernate verlangt für
 * jedes (persistente) Attribut/Feld eine enstprechnede getter- und setter-
 * Methode. Da diese Accessor-Methoden öffentlich sind, können leicht andere
 * Programmierer diese Schnittstelle (mit-)verwenden, statt der vorgesehenen
 * Geschäftsmethoden der Objekte zu benutzen.
 * <p>
 * Das Friendship-Framework ist jedoch in der Lage die Aufrufe von anderen
 * Klassen als Hibernate zu unterbinden und Hinweise auf die korrekte
 * Verwendung zu geben.
 * <p>
 * Dieses Beispiel zeigt daher die Verwendung von mehreren Frameworks zur
 * selben zeigt und verdeutlicht, wie sie sich gegenseitig unterstützen können.
 * Der Test legt eine Objekt der Klasse {@link BankBeanReflection} an und
 * lässt sie von Hibernate in eine Memory-Datenbank speichern. Anschliessend
 * werden die Entitäten mit Hibernate aus der DB neu erzeugt und ausgegeben.
 * Hibernate kann problemlos auf die Methoden der Entitäten zugreifen, andere
 * Klassen, wie diese Test-Klasse, haben dagegen keinen Zugriff.
 *
 * @author Timo Meinen
 */
public class BankManagerTest {
  public static void main(String[] args) {
    BankManagerTest mgr = new BankManagerTest();

    System.out.println("Speichere ein Bankkonto");
    mgr.createAndStoreBankAccount("My Bank Account", 1500);

    System.out.println("Lade alle Bankkonten");

    List accounts = mgr.listAccounts();

    for(int i = 0; i < accounts.size(); i++) {
      BankBeanReflection theAccount = (BankBeanReflection) accounts.get(i);

      // Aufruf wird verhindert!
      System.out.println("Saldo: " + theAccount.getSaldo());
      System.out.println(theAccount);
    }

    HibernateUtil.getSessionFactory().close();
  }

  private List listAccounts() {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    session.beginTransaction();

    List result = session.createQuery("from BankBeanReflection").list();

    session.getTransaction().commit();

    return result;
  }

  private void createAndStoreBankAccount(String title, int firstSaldo) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    session.beginTransaction();

    BankBeanReflection account = new BankBeanReflection(firstSaldo);

    account.getSaldo();
    session.save(account);

    session.getTransaction().commit();
  }
}
