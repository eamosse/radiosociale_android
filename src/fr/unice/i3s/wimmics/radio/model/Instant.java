/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.i3s.wimmics.radio.model;


import java.util.Date;

/**
 *
 * @author eamosse
 */


public class Instant extends Temporal {
    
    private Date date; 

    /**
     * @return the date
     */

    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
