package com.example.hw1;

public class Liste {
    private int photo;
    private String name;

    public Liste(String name, int photo){
        this.name = name;
        this.photo = photo;
    }

    public int getPhoto(){
        return photo;
    }

    public void setPhoto(){
        this.photo = photo;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }
}
