package com.SimpleEnergyCounterV1;

import java.io.Serializable;
import java.util.ArrayList;

public class CounterTracking implements Serializable {


    private ArrayList<CounterReadingHistory> listOfCounters;
    private String name;

    public CounterTracking(){};

    public CounterTracking (ArrayList<CounterReadingHistory> listOfCounters, String name){

        this.listOfCounters = listOfCounters;
        this.name = name;
    }





    public ArrayList<CounterReadingHistory> getListOfCounters() {
        return listOfCounters;
    }

    public void setListOfCounters(ArrayList<CounterReadingHistory> listOfCounters) {
        this.listOfCounters = listOfCounters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CounterTracking{\n" +
                "listOfCounters=" + listOfCounters + "\n" +
                "name=" + name +
                '}';
    }
}
