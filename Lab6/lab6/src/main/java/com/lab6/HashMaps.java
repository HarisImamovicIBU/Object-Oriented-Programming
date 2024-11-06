package com.lab6;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class HashMaps {
    public static void main(String[] args) {
        Map <String,String> mapa = new HashMap<>();
        mapa.put("matti", "mage");
        mapa.put("mikael", "mixu");
        mapa.put("arto", "arppa");
        //Printing mikaels nickname
        System.out.println(mapa.get("mikael"));
        //PromissoryNote task
        PromissoryNote mattisNote = new PromissoryNote();
        mattisNote.setLoan("Arto", 51.5);
        mattisNote.setLoan("Mikael", 30);
        System.out.println(mattisNote.howMuchIsTheDebt("Arto"));
        System.out.println(mattisNote.howMuchIsTheDebt("Joel"));
        //Overwriting Artos Loan
        mattisNote.setLoan("Arto", 10.5);
        System.out.println(mattisNote.howMuchIsTheDebt("Arto"));
        System.out.println("\nLET'S TEST OUR DICTIONARY CLASS!\n");

        Dictionary dictionary = new Dictionary();
        dictionary.add("apina", "monkey");
        dictionary.add("banaani", "banana");
        dictionary.add("cembalo", "harpsichord");
        ArrayList<String> translations = dictionary.translationList();
        for(String translation: translations) {
            System.out.println(translation);
        }
        System.out.println(dictionary.amountOfWords());
        System.out.println("\n TextUserInterface: ");
        Dictionary dictionary2 = new Dictionary();
        Scanner reader = new Scanner(System.in);
        TextUserInterface ui = new TextUserInterface(reader, dictionary2);
        ui.start();
    }
}
class PromissoryNote {
    private HashMap<String, Double> loans;
    public PromissoryNote(){
        this.loans=new HashMap<>();
    }
    public void setLoan(String toWhom, double value){
        this.loans.put(toWhom, value);
    }
    public double howMuchIsTheDebt(String whose){
        return this.loans.getOrDefault(whose, 0.0);
    }
    public HashMap<String, Double> getLoans() {
        return loans;
    }
    public void setLoans(HashMap<String, Double> loans) {
        this.loans = loans;
    }
}
class Dictionary{
    private HashMap<String,String> wordAndTranslation;
    public Dictionary(){
        this.wordAndTranslation=new HashMap<String,String>();
    }
    public String translate(String word){
        return this.wordAndTranslation.get(word);
    }
    public void add(String word, String translation){
        this.wordAndTranslation.put(word, translation);
    }
    public HashMap<String,String> getWordAndTranslation(){
        return wordAndTranslation;
    }
    public int amountOfWords(){
        int counter=0;
        for(String key : wordAndTranslation.keySet()){
            counter++;
        }
        return counter;
    }
    public ArrayList<String> translationList() {
        ArrayList<String> translations=new ArrayList<>();
        for (String key : this.wordAndTranslation.keySet()) {
            String value = this.wordAndTranslation.get(key);
            translations.add(key+" = "+value);
        }
        return translations;
    }    
}
class TextUserInterface {
    private Scanner reader;
    private Dictionary dictionary;
    public TextUserInterface(Scanner reader, Dictionary dictionary) {
        this.reader=reader;
        this.dictionary=dictionary;
    }

    public void add(){
        System.out.print("In Finnish: ");
        String word=reader.nextLine();
        System.out.print("Translation: ");
        String translation=reader.nextLine();
        dictionary.add(word, translation);
    }
    public void translate() {
        System.out.print("Give a word: ");
        String word = reader.nextLine();
        String translation = dictionary.translate(word);
        if (translation == null) {
            System.out.println("Word not found");
        } else {
            System.out.println("Translation: " + translation);
        }
    }
    public void start() {
        while (true) {
            System.out.print("Statement: ");
            String command = reader.nextLine();
            if (command.equals("quit")) {
                System.out.println("Cheers");
                break;
            } else if (command.equals("add")) {
                add();
            } else if (command.equals("translate")) {
                translate();
            } else {
                System.out.println("Unknown statement");
            }
        }
    }
}