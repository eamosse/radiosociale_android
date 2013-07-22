/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.i3s.wimmics.radio.model;


import java.io.Serializable;
import java.util.List;


/**
 *
 * @author eamosse
 */

public class Administrator extends Person implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private List<Frequency> frequencies;
    private Contact contact; 
    
    @Override
    public String toString() {
        return "fr.unice.i3s.wimmics.entity.Administrator";
    }
   
    public List<Frequency> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(List<Frequency> frequencies) {
        this.frequencies = frequencies;
    }
    
    
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    

    
}
