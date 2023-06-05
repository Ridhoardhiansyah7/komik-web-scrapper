package com.ridh.belajarRest.spring.model;

public class ModelHome {

    private String endpoint;
    private String title;
    private String image;
    private String chapter;
    private String date;

    public ModelHome(){

    }
    public ModelHome(String endpoint, String title, String image, String chapter, String date) {
        super();
        this.endpoint = endpoint;
        this.title = title;
        this.image = image;
        this.chapter = chapter;
        this.date = date;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
