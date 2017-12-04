package com.slava.reddittop.model.top;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("subreddit")
    @Expose
    private String subreddit;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("created")
    @Expose
    private Double created;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("created_utc")
    @Expose
    private Double createdUtc;
    @SerializedName("ups")
    @Expose
    private Integer ups;

    @SerializedName("num_comments")
    @Expose
    private Integer numComments;

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Double getCreated() {
        return created;
    }

    public void setCreated(Double created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(Double createdUtc) {
        this.createdUtc = createdUtc;
    }

    public Integer getUps() {
        return ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }


    public Integer getNumComments() {
        return numComments;
    }

    public void setNumComments(Integer numComments) {
        this.numComments = numComments;
    }

}
