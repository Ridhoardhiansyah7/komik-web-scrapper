package com.ridh.belajarRest.spring.model;

public class ModelDetail {
    private String title;
    private String image;
    private String synopsis;
    private String type;
    private String author;
    private String status;
    private String rating;
    private String postedBy;
    private String postedOn;
    private String updatedOn;

    private String endpointGenre;
    private String genreName;

    private String chapterEndpoint;
    private String chapterName;
    private String chapterDate;
    private String downloadChapter;

    public ModelDetail( String title, String image, String type, String author,String status, String rating, String postedBy, String postedOn, String updatedOn,String synopsis) {
        this.title = title;
        this.image = image;
        this.type = type;
        this.author = author;
        this.status  =status;
        this.rating = rating;
        this.postedBy = postedBy;
        this.postedOn = postedOn;
        this.updatedOn = updatedOn;
        this.synopsis = synopsis;
    }

    public ModelDetail(String endpointGenre, String genreName) {
        this.endpointGenre = endpointGenre;
        this.genreName = genreName;
    }

    public ModelDetail(String chapterEndpoint, String chapterName, String chapterDate, String downloadChapter) {
        this.chapterEndpoint = chapterEndpoint;
        this.chapterName = chapterName;
        this.chapterDate = chapterDate;
        this.downloadChapter = downloadChapter;
    }

    public ModelDetail(){}
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(String postedOn) {
        this.postedOn = postedOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getEndpointGenre() {
        return endpointGenre;
    }

    public void setEndpointGenre(String endpointGenre) {
        this.endpointGenre = endpointGenre;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getChapterEndpoint() {
        return chapterEndpoint;
    }

    public void setChapterEndpoint(String chapterEndpoint) {
        this.chapterEndpoint = chapterEndpoint;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterDate() {
        return chapterDate;
    }

    public void setChapterDate(String chapterDate) {
        this.chapterDate = chapterDate;
    }

    public String getDownloadChapter() {
        return downloadChapter;
    }

    public void setDownloadChapter(String downloadChapter) {
        this.downloadChapter = downloadChapter;
    }
}
