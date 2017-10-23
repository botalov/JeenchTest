package com.developer.sixfingers.jeenchtest.models;


public class PostModel {
    int userId;
    int id;
    String title;
    String body;

    public String getTitle(){return title;}
    public String getBody(){return body;}

    public void setUserId(int userId){
        this.userId = userId;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setBody(String body){
        this.body = body;
    }
}
