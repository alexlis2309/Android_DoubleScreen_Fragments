package com.example.myappp32323;

public class Article {

    private long id;
    private String title;
    private String description;
    private String moreInfo;

    Article(long id, String title, String description, String moreInfo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.moreInfo = moreInfo;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return this.title + "\n" + this.description;
    }
}
