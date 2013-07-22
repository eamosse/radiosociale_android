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

public abstract class Adresse implements Serializable{
    private String street; 
    private String locality; 
    private String postalCode;
    private String country; 
    private Geo geo; 

    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    
    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
    
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

}
