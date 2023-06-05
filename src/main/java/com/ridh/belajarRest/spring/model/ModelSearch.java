package com.ridh.belajarRest.spring.model;

public class ModelSearch {

    private String endpoint;
    private String title;
    private String image;

    public ModelSearch(String endpoint, String title, String image) {
        this.endpoint = endpoint;
        this.title = title;
        this.image = image;
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
}
