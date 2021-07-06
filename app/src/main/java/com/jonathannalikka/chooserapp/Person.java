package com.jonathannalikka.chooserapp;

import android.graphics.Bitmap;

public class Person {
    //instance variables for first and last name

    private String fname;
    private String lname;
    private Bitmap bitmap;

    //constructor methods

    public Person(){
        this.fname = "John";
        this.lname = "Doe";
    }

    public Person(String fname, String lname){

        this.fname = fname;
        this.lname = lname;

    }

    public Person(String fname, String lname, Bitmap bitmap) {
        this.fname = fname;
        this.lname = lname;
        this.bitmap = bitmap;
    }
    //getters and setters


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
