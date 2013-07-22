package fr.unice.i3s.wimmics.radio.model;

public class Response {
    private String text; 
    private String image; 
    private long id; 
  
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
