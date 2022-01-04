package com.example.myfirstapp;

public class Item {
    private String nickName;
    private String address;

    public Item(String nickName, String address){
        this.nickName = nickName;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getNickName() {
        return nickName;
    }
}
