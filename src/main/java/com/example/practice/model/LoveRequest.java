package com.example.practice.model;

public class LoveRequest {
    private String name1;
    private String name2;

    // Empty constructor
    public LoveRequest() {}

    // Constructor with parameters
    public LoveRequest(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    // Getters
    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    // Setters
    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}