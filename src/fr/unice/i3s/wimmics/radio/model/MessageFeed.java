/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.i3s.wimmics.radio.model;



/**
 *
 * @author eamosse
 */

public class MessageFeed {
    private String content; 
    private Frequency frequency;
    private Place place;
    private Duration validity; 
    private Interval interval; 

   
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }


    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }


    public Duration getValidity() {
        return validity;
    }

    public void setValidity(Duration validity) {
        this.validity = validity;
    }

 
    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }
    
    
}
