package com.example.edil.firebaseapp;

public class Users {

    private String tel;
    private String name;

    public Users(String tel, String name) {
        this.tel = tel;
        this.name = name;
    }

    public Users() {
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
