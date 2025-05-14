package com.example.lec_listview;

public class Cake {
    public Cake(int thumbline, String description, String title) {
        this.thumbline = thumbline;
        this.description = description;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title ;
    private String description ;

    private int thumbline ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getThumbline() {
        return thumbline;
    }

    public void setThumbline(int thumbline) {
        this.thumbline = thumbline;
    }
}
