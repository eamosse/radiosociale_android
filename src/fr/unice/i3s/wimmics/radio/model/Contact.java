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

public class Contact implements Serializable{
   private Adresse adresse; 
   private Telephone telephone; 

   
   public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

   
    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }
   
}
