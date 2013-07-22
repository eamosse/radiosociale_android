package fr.unice.i3s.wimmics.radio.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.*;


/**
 *
 * @author eamosse
 */

public class Frequency {

    private Long id;
    
    @Expose(serialize=false)
    private Category category;
    @Expose(serialize=false)
    private String name;
    @Expose(serialize=false)
    private String description; 
    @Expose(serialize=false)
    private String topic; 
    @Expose(serialize=false)
    private ResponseType responseType; 
    @Expose(serialize=false)
    private AccessRight publish;
    @Expose(serialize=false)
    private AccessRight listen; 
    @Expose(serialize=false)
    private ArrayList<Response> tags; 
    @Expose(serialize=false)
    private Administrator administrator; 
    @Expose(serialize=false)
    private List<Person> authorized; 
    @Expose(serialize=false)
    private ArrayList<String> metaKeys;

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
        if (!(object instanceof Frequency)) {
            return false;
        }
        Frequency other = (Frequency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    
    public String toString() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    
    public AccessRight getPublish() {
        return publish;
    }
     
     

    public void setPublish(AccessRight publish) {
         System.out.println("Value for publish " + publish.name() + " " + publish.getClass().getName());
        this.publish = publish;
    }

    
    public AccessRight getListen() {
        return listen;
    }
    

    public void setListen(AccessRight listen) {
        System.out.println("Value for listen " + listen.name() + " " + listen.getClass().getName());
        this.listen = listen;
    }
    
    public ArrayList<Response> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Response> tags) {
        this.tags = tags;
    }

    /**
     * @return the responseType
     */
    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     * @param responseType the responseType to set
     */
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }
    
    
    public List<Person> getAuthorized() {
        return authorized;
    }

    public void setAuthorized(List<Person> authorized) {
        this.authorized = authorized;
    }
    
    
    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
    
     
    public ArrayList<String> getMetaKeys() {
        return metaKeys;
    }

    public void setMetaKeys(ArrayList<String> metaKeys) {
        this.metaKeys = metaKeys;
    }
    
    /**
     * FOR RDF library 
     */
    
     public String getAccessPublish() {
        return publish.toString();
    }
    
    public void setAccessPublish(String publish){
       this.publish= AccessRight.valueOf(publish);
    }
    
    
     public String getAccessListen() {
        return listen.toString();
    }
    
    public void setAccessListen(String listen){
       this.listen= AccessRight.valueOf(listen);
    }
    
    
     public String getResponse() {
        return responseType.name();
    }
    
    public void setResponse(String response){
       this.responseType= ResponseType.valueOf(response);
    }

    public void setrdfSubject(String rdfSubject){
        
    }
     
     public String getrdfSubject(){
      return String.valueOf(id);
    }

	public String getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}
	
	public void setImage(String image){
		this.image = image; 
	}


     
     /**
      * End of RDF properties
      */
    
}
