package com.example.slot4;

public class Student {
    private String nameString;
    private String ageString;
    private int itemview;

    public Student() {

    }

    public Student(String nameString, String ageString, int itemview) {
        this.nameString = nameString;
        this.ageString = ageString;
        this.itemview = itemview;
    }


    public String getNameString() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public String ageString() {
        return ageString;
    }

    public void ageString(String ageString) {
        this.ageString = ageString;
    }

    public int getItemview() {
        return itemview;
    }

    public void setItemview(int itemview) {
        this.itemview = itemview;
    }
}
