package fr.unice.i3s.wimmics.radio.utils;

import java.util.ArrayList;

import fr.unice.i3s.wimmics.radio.model.Feature;

public class GooglePlace {
	
	ArrayList<Feature> address_components;
	String formatted_address; 
	Geometry geometry; 
	String [] types;
	/**
	 * @return the address_components
	 */
	public ArrayList<Feature> getAddress_components() {
		return address_components;
	}
	/**
	 * @param address_components the address_components to set
	 */
	public void setAddress_components(ArrayList<Feature> address_components) {
		this.address_components = address_components;
	}
	/**
	 * @return the formatted_address
	 */
	public String getFormatted_address() {
		return formatted_address;
	}
	/**
	 * @param formatted_address the formatted_address to set
	 */
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	/**
	 * @return the geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}
	/**
	 * @param geometry the geometry to set
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	/**
	 * @return the types
	 */
	public String[] getTypes() {
		return types;
	}
	/**
	 * @param types the types to set
	 */
	public void setTypes(String[] types) {
		this.types = types;
	} 
	}
