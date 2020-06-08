package com.SimpleEnergyCounterV1;

import java.io.Serializable;
import java.util.ArrayList;

public class CounterReadingHistory implements Serializable {
    /* This Class is used to combine a created Counter to an ArrayList (listOfReadings), which will be used to store the
    values of each single reading of current counter state (physical). The ArrayList holds Objects of type
     CounterReading (see CounterReading class)*/
    private Counter counter;
    private ArrayList<CounterReading> listOfReadings;


    //The following lines are standard Constructors, getters and setters and an overridden toString() method


    public CounterReadingHistory(Counter counter, ArrayList<CounterReading> listOfReadings){
        this.counter = counter;
        this.listOfReadings = listOfReadings;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public ArrayList<CounterReading> getListOfReadings() {
        return listOfReadings;
    }

    public void setListOfReadings(ArrayList<CounterReading> listOfReadings) {
        this.listOfReadings = listOfReadings;
    }


    @Override
    public String toString() {
        return "CounterReadingHistory{\n" +
                "counter=" + counter +
                "\n listOfReadings=" + listOfReadings +
                '}';
    }
}
