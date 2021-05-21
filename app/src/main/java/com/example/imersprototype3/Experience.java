package com.example.imersprototype3;

public class Experience {
    private static String title;
    private String description;

    public Experience(){
        //empty constructor needed
    }

    public Experience(String title, String description){
        Experience.title = title;
        this.description = description;
    }

    public static String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
