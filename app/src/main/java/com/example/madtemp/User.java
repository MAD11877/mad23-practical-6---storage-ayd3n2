package com.example.madtemp;

public class User {
    private String name;
    private String description;
    private int id;
    private boolean followed;

    public User(String n,String d,int i,boolean f){
        name = n;
        description = d;
        id = i;
        followed = f;
    }

    public String getName(){
        return name;
    }

    public void setName(String na){
        name = na;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String des){
        description = des;
    }

    public int getId(){
        return id;
    }

    public void setId(int di){
        id = di;
    }

    public boolean getFollowed(){
        return followed;
    }

    public void setFollowed(boolean fol){
        followed = fol;
    }


}