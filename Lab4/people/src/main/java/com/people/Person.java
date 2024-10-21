package com.people;
public class Person {
    private String name;
    private String address;
    private int age;
    private String country;
    /*Constructor before the third task, but I will leave it in case
    a person fails to input age and country*/
    public Person(String name, String address){
        this.name=name;
        this.address=address;
    }
    public Person(String name, String address, int age, String country){
        this.name=name;
        this.address=address;
        this.age=age;
        this.country=country;
    }
    @Override
    public String toString(){
        return this.name+"\n "+this.address;
    }
    public int getAge(){
        return this.age;
    }
    public String getCountry(){
        return this.country;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void setCountry(String country){
        this.country=country;
    }
    public static void main(String[] args) {
        Person pekka = new Person("Pekka Mikkola", "Korsontie Street 1 03100 Vantaa");
        Person esko = new Person("Esko Ukkonen", "Mannerheimintie Street 15 00100 Helsinki");
        System.out.println(pekka);
        System.out.println(esko);
     }
     
}
