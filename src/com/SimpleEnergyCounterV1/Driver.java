package com.SimpleEnergyCounterV1;

import javax.swing.*;
import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;



public class Driver {

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.createGui();
        //this is a testcomment for Github

    }

    public void createGui(){
        OpeningFrame Gui = new OpeningFrame();
    }

    public double calculateCounterReadingValueMeanForSingleDays(CounterTracking CounterGroup, int ListOfCountersIndex){
        /* this method takes a CounterGroup object and an Index for the ListOfCounters and calculates the Mean of the
        * Values for full days. For better accurracy the Dates are converted into minutes.*/
        double sumOfCounterReadingValues =0.00;
        double numberOfMinutesBetweenFirstAndLastReading;
        Date firstEntryDate;
        Date lastEntryDate;
        Double MeanOfReadingsPerDay =0.00;

        if(CounterGroup.getListOfCounters().get(ListOfCountersIndex).getListOfReadings().size()>1){
            double totalConsumption = CounterGroup.getListOfCounters().get(ListOfCountersIndex).getListOfReadings().
                    get(CounterGroup.getListOfCounters().get(ListOfCountersIndex).getListOfReadings().size()-1).
                    getCounterReadingValue() - CounterGroup.getListOfCounters().get(ListOfCountersIndex).getListOfReadings().
                    get(0).getCounterReadingValue();

            firstEntryDate =CounterGroup.getListOfCounters().
                    get(ListOfCountersIndex).getListOfReadings().get(0).getCounterReadingDate();
            lastEntryDate = CounterGroup.getListOfCounters().get(ListOfCountersIndex).getListOfReadings().get(CounterGroup.
                    getListOfCounters().get(ListOfCountersIndex).getListOfReadings().size()-1).getCounterReadingDate();
            numberOfMinutesBetweenFirstAndLastReading= ChronoUnit.MINUTES.between(firstEntryDate.toInstant(),lastEntryDate.toInstant());
            //getting the number of HOURs between the first and the last entry in our ListOfCOunterReadings
            MeanOfReadingsPerDay= totalConsumption/numberOfMinutesBetweenFirstAndLastReading*60*24;


        }else if (CounterGroup.getListOfCounters().get(ListOfCountersIndex).getListOfReadings().size()>0) {
            MeanOfReadingsPerDay=0.00;
        }

        return MeanOfReadingsPerDay;


    }


    public CounterTracking addCountersToListOfCounters(CounterTracking CounterGroup, CounterReadingHistory CounterHistory){
        //this method enables you to add new CounterReadingHistory Objects to existing CounterTracking Objects
        CounterGroup.getListOfCounters().add(CounterHistory);
        return CounterGroup;
    }

    public CounterTracking createCounterTracking(ArrayList<CounterReadingHistory> listOfCounters, String name){
        //creation of new CounterTracking Objects to store an ArrayList of type CounterReadingHistory to store CounterReadngHistory Objects
        CounterTracking CounterGroup = new CounterTracking (listOfCounters, name);
        return CounterGroup;
    }


    public ArrayList<CounterReadingHistory> createListOfCounters(){
        //simple method to create an ArrayList which will hold CounterReadingHistory Objects
        ArrayList<CounterReadingHistory> listOfCounters = new ArrayList<>();
        return listOfCounters;
    }


    public void addCounterReading(CounterReadingHistory CounterHistory,CounterReading counterReading) {
        /* this metod takes an existing CounterReadingHistory object and an CounterReading object and adds that
        CounterReading object to the CounterReadingHistory Objects listOfReadings Arraylist. The Method will also sort the List by date*/

        CounterHistory.getListOfReadings().add(counterReading);
        Collections.sort(CounterHistory.getListOfReadings());
    }

    public void removeCounterReading (CounterReadingHistory CounterHistory, int counterReadingListEntry){
        /* method to remove single Readings (CounterReading Objects) from its listOfReadings. The position of the Entry
        * (ArrayList index) has to be provided*/
        CounterHistory.getListOfReadings().remove(counterReadingListEntry);
    }

    public void editCounterReading (CounterReadingHistory CounterHistory, int counterReadingListEntry){
        CounterHistory.getListOfReadings().get(counterReadingListEntry).
                setCounterReadingValue(Double.parseDouble(JOptionPane.showInputDialog("Please enter a new value")));
    }

    public ArrayList<CounterReading> createListOfReadings(){
        /* create an ArrayList of type CounterReading for storing individual CounterReading objects. Is used in the
        * creation of CounterReadingHistory objects*/
        ArrayList<CounterReading> listOfReadings = new ArrayList<>();
        return listOfReadings;
    }

    public CounterReadingHistory createCounterReadingHistory(Counter counter, ArrayList<CounterReading> listOfReadings){
        //create a new CounterReadingHistory object
        CounterReadingHistory counterReadingHistory = new CounterReadingHistory(counter, listOfReadings);
        return counterReadingHistory;
    }

    public void removeCounterReadingHistory(CounterTracking CounterGroup, int CounterHistoryEntry){
        /* method to remove whole Counters (CounterReadingHistory Objects) from its listOfCounters. The position of the Entry
         * (ArrayList index) has to be provided*/
        CounterGroup.getListOfCounters().remove(CounterHistoryEntry);
    }


    public Counter createNewCounter(String Name, int serialNumber, String type) {
        //create a new Counter object
        Counter counter = new Counter(Name, serialNumber, type);
        return counter;
    }

    public void editCounterName(CounterTracking CounterGroup, int ListOfCountersIndex, String NewCounterName){
        CounterGroup.getListOfCounters().get(ListOfCountersIndex).getCounter().setName(NewCounterName);
    }

    public void editCounterType(CounterTracking CounterGroup, int ListOfCountersIndex, String NewCounterType){
        CounterGroup.getListOfCounters().get(ListOfCountersIndex).getCounter().setType(NewCounterType);
    }

    public void editCounterSerialNumber(CounterTracking CounterGroup, int ListOfCountersIndex, int NewCounterSerialNumber){
        CounterGroup.getListOfCounters().get(ListOfCountersIndex).getCounter().setSerialNumber(NewCounterSerialNumber);
    }

    public void saveCounterGroup(CounterTracking CounterGroup, File file){
        //This method is used to save a CounterReadingHistory Object to the provided path by the JFileChooser

        try{FileOutputStream saveFile = new FileOutputStream(file);
            ObjectOutputStream saveObject = new ObjectOutputStream(saveFile);
            saveObject.writeObject(CounterGroup);
            saveObject.close();
            saveFile.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public CounterTracking loadCounterGroup(File file) {
        /* this method is used for loading existing CounterReadingHistory objects. It will use the provided file to get
        * the object needed*/


        CounterTracking CounterGroup = new CounterTracking();
            try {
                FileInputStream loadFile = new FileInputStream(file);
                ObjectInputStream loadObject = new ObjectInputStream(loadFile);
                CounterGroup = (CounterTracking) loadObject.readObject();
                loadObject.close();
                loadFile.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        return CounterGroup;
    }
}
