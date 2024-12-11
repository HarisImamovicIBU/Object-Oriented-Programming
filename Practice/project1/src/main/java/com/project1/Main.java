package com.project1;
/*Create a Martial Arts hierarhy system where you will demonstrate key concepts of OOP.
 *Create an abstract class MartialArtist which will have the following attributes and methods:
 *Attributes: name, age, skill, numOfMatchesWon, isChampion. Create get/set methods and 2 constructors, 
  one where all values will be set (NOTE: A MartialArtist can be a champion if and only if he has 10 or more matches won.)
  another where skill, numOfMatchesWon and isChampion will be set to 0, 0 and false.
 *Methods: fightingStyle() which will be print out different styles for each Marital Artist,
           train() which will increace a martial artists skill by 1 
           fight(MaritalArtist martialArtist) which will compare the skill of two martial artists,
           and increase/decrease numOfMatchesWon for the one with more/less skill. NOTE: Only martial artists
           of the same type can fight. Example: Karateka vs Karateka VALID   Boxer vs Jiujitsuka INVALID
 
 *Create also an Interface called Fightable which will have the method canYouFight. Implement the method inside of MartialArtist
  If a martial artist has skill at 0, they should be forbidden from fighting at all."
  
 *Create a subclass of MartialArtist named Boxer which will have the following attributes and methods: 
 *Attributes: colorOfGloves of ENUM type gloveColor (BLUE, RED)
 *Methods: Override the fightingStyle() method to print out "[name] is a boxer who throws perfect jabs!"
   
 *Create a subclass of MartialArtist named Karateka which will have the following attributes and methods:
 *Attributes: beltColor of ENUM type beltColor (WHITE, YELLOW, ORANGE, GREEN, BLUE, BROWN, BLACK)
 *Methods: Override the fightingStyle() method to print out "[name] is a karateka who performs stunning mawashi geri!" 
 
 *Create a subclass of MartialArtist named Jiujiteiro which will have the following attributes and methods:
 *Attributes: beltColor of ENUM type beltColor
 *Methods: Override the fightingStyle method to print out "[name] is a jiujiteiro who suprises their oponnent with a rear naked choke!"

 *Let's create a better implementation of the fight method. Since we needed to downcast MartialArtist each and every time, that means
  that we can create a Generic method of type T which will extend MartialArtist and Figihtable.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

interface Fightable{
    public boolean canYouFight();
}
enum gloveColor{
    BLUE, RED
}
enum beltColor{
    WHITE, YELLOW, ORANGE, GREEN, BLUE, BROWN, BLACK
}
abstract class MartialArtist implements Fightable{
    private String name;
    private int age;
    private int skill;
    private int numOfMatchesWon;
    private boolean isChampion;
    public MartialArtist(String name, int age, int skill, int numOfMatchesWon, boolean isChampion) {
        this.name=name;
        this.age=age;
        this.skill=skill;
        this.numOfMatchesWon=numOfMatchesWon;
        if(this.numOfMatchesWon<10){
            this.isChampion=false;
        }
        else{
            this.isChampion=isChampion;
        }
    }
    public MartialArtist(String name, int age){
        this(name, age, 0,0,false);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSkill() {
        return skill;
    }
    public void setSkill(int skill) {
        this.skill = skill;
    }
    public int getNumOfMatchesWon() {
        return numOfMatchesWon;
    }
    public void setNumOfMatchesWon(int numOfMatchesWon) {
        this.numOfMatchesWon = numOfMatchesWon;
    }
    public boolean isChampion() {
        return isChampion;
    }
    public void setIsChampion(boolean isChampion) {
        this.isChampion = isChampion;
    }
    public void train(){
        this.skill++;
    }
    public <T extends MartialArtist & Fightable> void fight(T martialArtist){
        if(this.getClass()!=martialArtist.getClass()){
            System.out.println(this.getName()+" and "+martialArtist.getName()+" cannot fight because they don't train the same sport.");
        }
        else if(!this.canYouFight() || !martialArtist.canYouFight()){
            System.out.println(this.getName()+" and "+martialArtist.getName()+" cannot fight because their skill is low. Train first!");
        }
        else{
            if(this.getSkill()>martialArtist.getSkill()){
            System.out.println(this.getName()+" won the battle against "+martialArtist.getName()+"!");
            this.setNumOfMatchesWon(this.getNumOfMatchesWon()+1);
            this.setSkill(this.getSkill()+1);
        }
        else if(this.getSkill()<martialArtist.getSkill()){
            System.out.println(martialArtist.getName()+" won the battle against "+this.getName()+"!");
            martialArtist.setNumOfMatchesWon(martialArtist.getNumOfMatchesWon()+1);
            martialArtist.setSkill(martialArtist.getSkill()+1);
        }
        else{
            System.out.println("It's a draw between "+this.getName()+" and "+martialArtist.getName()+"!");
        }
    }
    }
    
    /* fight() without generics
    
    public void fight(MartialArtist martialArtist){
        if(this instanceof Boxer){
            if(martialArtist instanceof Boxer){
                if(this.canYouFight() && martialArtist.canYouFight()){
                    Boxer myLocalBoxer = (Boxer) martialArtist;
                    if(this.getSkill()>myLocalBoxer.getSkill()){
                        System.out.println(this.getName()+" won the fight!");
                        this.setSkill(this.getSkill()+1);
                    }
                    else if(myLocalBoxer.getSkill()>this.getSkill()){
                        System.out.println(myLocalBoxer.getName()+" won the fight!");
                        myLocalBoxer.setSkill(myLocalBoxer.getSkill()+1);
                    }
                    else{
                    System.out.println("A draw between "+this.getName()+" and "+myLocalBoxer.getName()+"!");
                    }
                }
                else{
                    System.out.println(this.getName()+" has "+this.getSkill()+" skill, and "+martialArtist.getName()+" has "+martialArtist.getSkill()+" skill. So they cannot fight. We advise you to train!");
                }
            }
            else{
                System.out.println(this.getName()+" cannot fight "+ martialArtist.getName()+" because they are not the same type.");
            }
    }
        else if(this instanceof Karateka){
            if(martialArtist instanceof Karateka){
                if(this.canYouFight() && martialArtist.canYouFight()){
                    Karateka myLocalKarateka = (Karateka) martialArtist;
                        if(this.getSkill()>myLocalKarateka.getSkill()){
                            System.out.println(this.getName()+" won the fight!");
                            this.setSkill(this.getSkill()+1);
                        }
                        else if(myLocalKarateka.getSkill()>this.getSkill()){
                            System.out.println(myLocalKarateka.getName()+" won the fight!");
                            myLocalKarateka.setSkill(myLocalKarateka.getSkill()+1);
                        }
                        else{
                            System.out.println("A draw between "+this.getName()+" and "+myLocalKarateka.getName()+"!");
                        }
                }
                else{
                    System.out.println(this.getName()+" has "+this.getSkill()+" skill, and "+martialArtist.getName()+" has "+martialArtist.getSkill()+" skill. So they cannot fight. We advise you to train!");
                }
            }
            else{
                System.out.println(this.getName()+" cannot fight "+ martialArtist.getName()+" because they are not the same type.");
            }
        }
        else if(this instanceof Jiujiteiro){
            if(martialArtist instanceof Jiujiteiro){
                if(this.canYouFight() && martialArtist.canYouFight()){
                    Jiujiteiro myLocalJiujiteiro = (Jiujiteiro) martialArtist;
                        if(this.getSkill()>myLocalJiujiteiro.getSkill()){
                            System.out.println(this.getName()+" won the fight!");
                            this.setSkill(this.getSkill()+1);
                        }
                        else if(myLocalJiujiteiro.getSkill()>this.getSkill()){
                            System.out.println(myLocalJiujiteiro.getName()+" won the fight!");
                            myLocalJiujiteiro.setSkill(myLocalJiujiteiro.getSkill()+1);
                        }
                        else{
                            System.out.println("A draw between "+this.getName()+" and "+myLocalJiujiteiro.getName()+"!");
                        }
                }
                else{
                    System.out.println(this.getName()+" has "+this.getSkill()+" skill, and "+martialArtist.getName()+" has "+martialArtist.getSkill()+" skill. So they cannot fight. We advise you to train!");
                }
            }
            else{
                System.out.println(this.getName()+" cannot fight "+ martialArtist.getName()+" because they are not the same type.");
            }
        }
    }*/

    @Override
    public boolean canYouFight(){
        if(this.skill<=0){
            return false;
        }
        else{
            return true;
        }
    }

    public static Optional<MartialArtist> findMartialArtistByName(List<MartialArtist> martialArtists, String name) {
        return martialArtists.stream()
                            .filter(ma -> ma.getName().equals(name))
                            .findFirst();
    }

    @Override
    public String toString(){
        return "Name: "+this.name+", Age: "+this.age+", Skill: "+this.skill+", Wins: "+this.numOfMatchesWon+", Champ: "+this.isChampion;
    }

    public abstract void fightingStyle();
}
class Boxer extends MartialArtist{
    private gloveColor colorOfGloves;
    public Boxer(String name, int age, int skill, int numOfMatchesWon, boolean isChampion, gloveColor colorOfGloves){
        super(name, age, skill, numOfMatchesWon, isChampion);
        this.colorOfGloves=colorOfGloves;
    }
    public Boxer(String name, int age, gloveColor colorOfGloves){
        this(name, age, 0, 0, false, colorOfGloves);
    }
    public gloveColor getColorOfGloves() {
        return colorOfGloves;
    }
    public void setColorOfGloves(gloveColor colorOfGloves) {
        this.colorOfGloves = colorOfGloves;
    }
    @Override
    public void fightingStyle(){
        System.out.println(this.getName()+" is a boxer who throws perfect jabs!");
    }
    @Override
    public String toString(){
        return super.toString()+", Gloves: "+this.colorOfGloves;
    }
}
class Karateka extends MartialArtist{
    private beltColor colorOfBelt;
    public Karateka(String name, int age, int skill, int numOfMatchesWon, boolean isChampion, beltColor colorOfBelt) {
        super(name, age, skill, numOfMatchesWon, isChampion);
        this.colorOfBelt=colorOfBelt;
    }
    public Karateka(String name, int age) {
        this(name, age, 0, 0, false, beltColor.WHITE);
    }
    @Override
    public void fightingStyle(){
        System.out.println(this.getName()+" is a karateka who performs stunning mawashi geri!");
    }
    @Override
    public String toString(){
        return super.toString()+", Belt: "+this.colorOfBelt;
    }
    public beltColor getColorOfBelt() {
        return colorOfBelt;
    }
    public void setColorOfBelt(beltColor colorOfBelt) {
        this.colorOfBelt=colorOfBelt;
    }
}
class Jiujiteiro extends MartialArtist{
    private beltColor colorOfBelt;

    public Jiujiteiro(beltColor colorOfBelt, String name, int age) {
        super(name, age);
        this.colorOfBelt = colorOfBelt;
    }

    public Jiujiteiro(beltColor colorOfBelt, String name, int age, int skill, int numOfMatchesWon, boolean isChampion) {
        super(name, age, skill, numOfMatchesWon, isChampion);
        this.colorOfBelt = colorOfBelt;
    }

    public beltColor getColorOfBelt() {
        return colorOfBelt;
    }

    public void setColorOfBelt(beltColor colorOfBelt) {
        this.colorOfBelt = colorOfBelt;
    }
    @Override
    public void fightingStyle(){
        System.out.println(this.getName()+" is a jiujiteior who suprises his opponent with a rear naked choke!");

    }
}
public class Main {
    public static void main(String[] args) {
        /*Let's create two boxers first. We will use the main constructor with all attributes, display their info, and make them fight!*/
        MartialArtist boxer1 = new Boxer("Mike Tyson", 40, 10, 10, true, gloveColor.BLUE);
        MartialArtist boxer2 = new Boxer("Floyd Mayweather", 36, 9, 9, false, gloveColor.RED);
        System.out.println(boxer1);
        System.out.println(boxer2);
        boxer1.fight(boxer2);
        /*Let's now create an instantiation of Karateka and make him fight a boxer, to demonstrate that two different types of
          martial artists cannot fight!*/
        MartialArtist karateka1 = new Karateka("Haris Imamovic", 20, 8, 10, true, beltColor.BROWN);
        System.out.println(karateka1);
        karateka1.fight(boxer2);
        /*Let's now create a new Karateka who will by default have a skill of 0, and we will demonstrate that he cannot
         fight because of that. After that we will make him train and increase his skill, and finally let him fight.*/
        Karateka karateka2 = new Karateka("Bruce Lee", 70);
        karateka1.fight(karateka2);
        karateka2.train();
        karateka1.fight(karateka2);
        /*Let's find a Martial Artist by his name. */
        List<MartialArtist> listOfMartialArtists = new ArrayList<>(Arrays.asList(boxer1,boxer2,karateka1,karateka2));
        Optional<MartialArtist> foundMartialArtist = MartialArtist.findMartialArtistByName(listOfMartialArtists, "Mike Tyson");
        foundMartialArtist.ifPresentOrElse(
            ma -> System.out.println("Found: " + ma),
            () -> System.out.println("Martial Artist not found")
        );
    }
}