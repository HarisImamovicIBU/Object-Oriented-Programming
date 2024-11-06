package com.lab6;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Generics {
    //First task
    public static <T extends Number> void sumOfEvenAndOdd(ArrayList<T> list){
        int sumEven=0;
        int sumOdd=0;
        for(T value : list){
            if((int)value%2==0){
                sumEven+=(int)value;
            }
            else{
                sumOdd+=(int)value;
            }
        }
        System.out.println("Sum of evens: "+sumEven+"\nSum of odds: "+sumOdd);
    }
    //Second task
    public static <T extends Object> ArrayList<T> reverseListOfAnyType(ArrayList<T> lista){
        Collections.reverse(lista);
        return lista;
    }
    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        sumOfEvenAndOdd(list);
        ArrayList <String> list2=new ArrayList<>(Arrays.asList("Bellingham","Vinicius","Rodrygo"));
        System.out.println(reverseListOfAnyType(list2));
    }
}
