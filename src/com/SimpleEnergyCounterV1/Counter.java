package com.SimpleEnergyCounterV1;

import java.io.Serializable;

public class Counter implements Serializable {
/* This class represents the actual EnergyCounter which we'll want to track. Its instance variables are its
serialNumber  and its type. We will be using these for identification and and classification of the provided counter.*/
    private String Name;
    private int serialNumber;
    private String type;


    //The following lines are standard Constructors, getters and setters and an overridden toString() method
    public Counter(){}

    public Counter(String Name, int serialNumber, String type){
        this.Name = Name;
        this.serialNumber = serialNumber;
        this.type = type;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Counter{\n" +
                "Name='" + Name + '\'' +
                "\n serialNumber=" + serialNumber +
                "\n type='" + type + '\'' +
                '}';
    }
}
