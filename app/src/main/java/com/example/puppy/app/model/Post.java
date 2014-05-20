package com.example.puppy.app.model;

import com.j256.ormlite.field.DatabaseField;

import org.codehaus.jackson.annotate.JsonProperty;

public class Post {
    @DatabaseField(id = true)
    private Long id;

    @DatabaseField
    private String url;

    @JsonProperty("photo-url-1280")
    @DatabaseField
    private String photoUrl1280;

    @JsonProperty("photo-url-500")
    @DatabaseField
    private String photoUrl500;

    @JsonProperty("photo-url-400")
    @DatabaseField
    private String photoUrl400;

    @JsonProperty("photo-url-250")
    @DatabaseField
    private String photoUrl250;

    @JsonProperty("photo-url-100")
    @DatabaseField
    private String photoUrl100;

    @JsonProperty("photo-url-75")
    @DatabaseField
    private String photoUrl75;

    public String getPhotoUrl500() {
        return photoUrl500;
    }

    public void setPhotoUrl500(String photoUrl500) {
        this.photoUrl500 = photoUrl500;
    }

    public String getPhotoUrl400() {
        return photoUrl400;
    }

    public void setPhotoUrl400(String photoUrl400) {
        this.photoUrl400 = photoUrl400;
    }

    public String getPhotoUrl250() {
        return photoUrl250;
    }

    public void setPhotoUrl250(String photoUrl250) {
        this.photoUrl250 = photoUrl250;
    }

    public String getPhotoUrl100() {
        return photoUrl100;
    }

    public void setPhotoUrl100(String photoUrl100) {
        this.photoUrl100 = photoUrl100;
    }

    public String getPhotoUrl75() {
        return photoUrl75;
    }

    public void setPhotoUrl75(String photoUrl75) {
        this.photoUrl75 = photoUrl75;
    }


    public String getPhotoUrl1280() {
        return photoUrl1280;
    }

    public void setPhotoUrl1280(String photoUrl1280) {
        this.photoUrl1280 = photoUrl1280;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
