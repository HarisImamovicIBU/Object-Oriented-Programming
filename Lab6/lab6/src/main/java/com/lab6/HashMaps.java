package com.lab6;
import java.util.HashMap;
import java.util.Map;
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
        mattisNote.setLoan("Arto", 10.5);
        System.out.println(mattisNote.howMuchIsTheDebt("Arto"));

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
