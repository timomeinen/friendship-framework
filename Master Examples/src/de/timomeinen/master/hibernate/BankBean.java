/*
 * BankBean.java
 *
 * Created on 15. Oktober 2007, 13:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.timomeinen.master.hibernate;

/**
 *
 * @author timo
 */
public class BankBean {
    private Long id;
    private int saldo;
    
    /** Creates a new instance of BankBean */
    public BankBean() {
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
