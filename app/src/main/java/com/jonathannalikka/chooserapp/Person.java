package com.jonathannalikka.chooserapp;

public class Person {
    //instance variables for first and last name

    private String fname;
    private String lname;

    //constructor methods

    public Person(){
        this.fname = "John";
        this.lname = "Doe";
    }

    public Person(String fname, String lname){

        this.fname = fname;
        this.lname = lname;

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
}
