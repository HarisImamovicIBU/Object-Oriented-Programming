package com.lab10;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
class Task3{
    public static void main(String[] args) throws IOException{
        Events events = new Events();
        String filename = "events.txt";
        events.generateEventsFile(filename, 10);
        events.printEventsFromFile(filename);
        ArrayList<String> eventRecords = events.readEventsFromFile(filename);
    }
}
class Events{
    private static final String[] EVENT_TYPES = {"Login", "Logout", "Purchase", "ViewPage", "Error"};
    private static final Random randomizer = new Random();

    public void generateEventsFile(String filename, int numberOfRecords) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for(int i=0;i<numberOfRecords;i++){
            Date date = new Date();
            String timestamp = date.toString();
            String eventType = EVENT_TYPES[randomizer.nextInt(EVENT_TYPES.length)];
            int userId = randomizer.nextInt(1000);
            writer.write(timestamp + " | Event Type: " + eventType + " | User ID: " + userId);
            writer.newLine();
        }
        writer.close();
    }
    public void printEventsFromFile(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while((line=reader.readLine())!=null){
            System.out.println(line);
        }
        reader.close();
    }
    public ArrayList<String> readEventsFromFile(String filename) throws IOException{
        ArrayList<String> listOfEvents = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while((line=reader.readLine())!=null){
            listOfEvents.add(line);
        }
        reader.close();
        return listOfEvents;
    }
}