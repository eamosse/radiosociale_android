/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.i3s.wimmics.radio.model;

import com.google.gson.annotations.SerializedName;



/**
 * 
 * @author eamosse
 */

public class Point {
    //Beacause in http://www.w3.org/2003/01/geo/wgs84_pos# longitude in latitude are defined as string  
    @SerializedName("lng")
	private String longitude;
    @SerializedName("lat")
    private String latitude; 
    
       /**
     * @return the longitude
     */

    public String getLongitude() {
        return longitude;
    }

    /**
     * @return the latitude
     */
  
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
      public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
