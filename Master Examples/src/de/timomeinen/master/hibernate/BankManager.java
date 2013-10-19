/*
 * BankManager.java
 *
 * Created on 15. Oktober 2007, 13:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.hibernate;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author timo
 */
public class BankManager {
    
    public static void main(String[] args) {
        BankManager mgr = new BankManager();
        
        System.out.println("Speichere ein Bankkonto");
        mgr.createAndStoreBankAccount("My Bank Account", 1500);
        
        
        System.out.println("Lade alle Bankkonten");
        List accounts = mgr.listAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            BankBeanReflection theAccount = (BankBeanReflection) accounts.get(i);
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
        
        session.save(account);
        
        session.getTransaction().commit();
    }
}
