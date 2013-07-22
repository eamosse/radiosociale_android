/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.i3s.wimmics.radio.model;

import java.io.Serializable;



/**
 *
 * @author eamosse
 */

public class OnlineAccount implements Serializable {

    private Person person;
    private static final long serialVersionUID = 1L;
    private Long id;
    private AccountService accountServiceHomePage; 
    private String accountName; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OnlineAccount)) {
            return false;
        }
        OnlineAccount other = (OnlineAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.unice.i3s.wimmics.entity.OnlineAccount[ id=" + id + " ]";
    }
    
    public AccountService getAccountServiceHomePage() {
        return accountServiceHomePage;
    }

    public void setAccountServiceHomePage(AccountService accountServiceHomePage) {
        this.accountServiceHomePage = accountServiceHomePage;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    
}
