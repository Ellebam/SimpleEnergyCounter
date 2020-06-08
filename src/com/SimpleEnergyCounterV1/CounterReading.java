package com.SimpleEnergyCounterV1;

import java.io.Serializable;
import java.util.Date;

public class CounterReading implements Serializable, Comparable <CounterReading> {
    /* CounterReading objects are used to store information about every single reading the user might want to process.
    * They consist of a Value (counterReadingValue) and a Date (counterReadingDate). They are combined with an existing
    * counter into a CounterReadingHistory-object (see CounterReadingHistory class) */
    private double counterReadingValue;
    private Date counterReadingDate;


    //The following lines are standard Constructors, getters and setters and an overridden toString() method
    public CounterReading(){};

    public CounterReading(double counterReadingValue, Date counterReadingDate){
        this.counterReadingDate = counterReadingDate;
        this.counterReadingValue = counterReadingValue;
    }

    public double getCounterReadingValue() {
        return counterReadingValue;
    }

    public void setCounterReadingValue(double counterReadingValue) {
        this.counterReadingValue = counterReadingValue;
    }

    public Date getCounterReadingDate() {
        return counterReadingDate;
    }

    public void setCounterReadingDate(Date counterReadingDate) {
        this.counterReadingDate = counterReadingDate;
    }

    @Override
    public String toString() {
        return "CounterReading{\n" +
                "counterReadingValue=" + counterReadingValue +"\n"+
                ", counterReadingDate=" + counterReadingDate +
                '}';
    }
    @Override
    public int compareTo(CounterReading CounterReading){
        return getCounterReadingDate().compareTo(CounterReading.getCounterReadingDate());
    }
}
