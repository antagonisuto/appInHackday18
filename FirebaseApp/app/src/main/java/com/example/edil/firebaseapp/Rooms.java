package com.example.edil.firebaseapp;

import java.util.ArrayList;

public class Rooms {

    private int id;
    private int capacity,raiting;
    private String name,description,facilities,location;
    private ArrayList<String> arr;

    public Rooms(int id, int capacity, int raiting, String name, String description, String facilities, String location, ArrayList<String> arr) {
        this.id = id;
        this.capacity = capacity;
        this.raiting = raiting;
        this.name = name;
        this.description = description;
        this.facilities = facilities;
        this.location = location;
        this.arr = arr;
    }

    public Rooms() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getArr() {
        return arr;
    }

    public void setArr(ArrayList<String> arr) {
        this.arr = arr;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", raiting=" + raiting +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", facilities='" + facilities + '\'' +
                ", location='" + location + '\'' +
                ", arr=" + arr +
                '}';
    }
}
