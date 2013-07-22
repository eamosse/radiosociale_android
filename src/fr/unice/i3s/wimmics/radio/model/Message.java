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

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private Long id;
    private Response content; 
   
    private Frequency frequency; 
    private Feature feature; 

    private Person person;
    private List<MetaData> metadatas;
    private Instant publishDate; 
    
    
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.unice.i3s.wimmics.entity.Message[ id=" + id + " ]";
    }

    public Response getContent() {
        return content;
    }

    public void setContent(Response content) {
        this.content = content;
    }

   
    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    
   

    
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

     
    public List<MetaData> getMetadatas() {
        return metadatas;
    }

    public void setMetadatas(List<MetaData> metadatas) {
        this.metadatas = metadatas;
    }

    
    public Instant getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Instant publishDate) {
        this.publishDate = publishDate;
    }

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}
    

    
}
