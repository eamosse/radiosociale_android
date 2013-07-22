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

public class Category implements Serializable {
   
    private List<Frequency> frequencys;
    private static final long serialVersionUID = 1L;
   
    private Long id;
    private String name;  
    private String description; 
    
    private Category parent;
	private String image;
    
 
    public Long getId() {
        return id;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override

    public String toString() {
        return String.valueOf(id);
    }

    /**
     * @return the name
     */

    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    public List<Frequency> getFrequencys() {
        return frequencys;
    }

    public void setFrequencys(List<Frequency> frequencys) {
        this.frequencys = frequencys;
    }
    
  
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }


	public String getImage() {
		// TODO Auto-generated method stub
		return image;
	}
	
	public void setImage(String image) {
		// TODO Auto-generated method stub
		 this.image = image;
	}
    
    
}
