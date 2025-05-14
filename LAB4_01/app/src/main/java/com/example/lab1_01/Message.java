package com.example.lab1_01;

public class Message {

    private String mobile ;

    private String message ;


    public Message(String mobile , String message){
        this.message = message;
        this.mobile = mobile ;
    }

    public String toString(){
        return message + " " + mobile ;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public String getMobile() {
        return mobile;
    }
}
