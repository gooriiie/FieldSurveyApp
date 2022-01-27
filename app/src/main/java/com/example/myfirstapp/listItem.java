package com.example.myfirstapp;

public class listItem {
    private String name;
    private int imageResource;

    public listItem(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
