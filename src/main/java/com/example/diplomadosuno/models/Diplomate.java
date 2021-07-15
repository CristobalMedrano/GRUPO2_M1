package com.example.diplomadosuno.models;

public class Diplomate {

    private long id;
    private String title;
    private String image;
    private String description;

    public long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getImage(){
        return image;
    }
    public String getDescription(){
        return description;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setImage(String image){
        this.image = image;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
