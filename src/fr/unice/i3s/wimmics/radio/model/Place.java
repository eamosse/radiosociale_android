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

public class Place implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type; 
    private Feature feature; 


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
