package com.example.lab6_01;

public class Cake {

    private String title ;
    private String description ;

    private int thumbline ;
    public Cake(  String title,String description,int thumbline) {
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
