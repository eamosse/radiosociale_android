package fr.unice.i3s.wimmics.radio.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuContent{
	public static ArrayList<MenuItem> ITEMS = new ArrayList<MenuItem> (); 
	public static Map<String, MenuItem> ITEM_MAP = new HashMap<String, MenuItem>();

	static {
		// Add 3 sample items.
		addItem(new MenuItem("1", "Listen to a frequency"));
		addItem(new MenuItem("2", "Send message to a frequency"));
	}

	private static void addItem(MenuItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.title, item);
	}
	
	
	public static class MenuItem{
		public String title; 
		public String description; 
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		public MenuItem(String title, String description){
			this.title= title; 
			this.description= description;
		}
		
		public String toString(){
			return this.description; 
		}
		
	}
}
