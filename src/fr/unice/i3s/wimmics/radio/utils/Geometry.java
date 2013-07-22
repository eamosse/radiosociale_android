package fr.unice.i3s.wimmics.radio.utils;

import fr.unice.i3s.wimmics.radio.model.Point;

public class Geometry {
	Bound bound; 
	Bound viewport; 
	Point location; 
	String location_type;
	String[] types; 
	public Bound getBound() {
		return bound;
	}
	public void setBound(Bound bound) {
		this.bound = bound;
	}
	public Bound getViewport() {
		return viewport;
	}
	public void setViewport(Bound viewport) {
		this.viewport = viewport;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public String getLocation_type() {
		return location_type;
	}
	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	
}
