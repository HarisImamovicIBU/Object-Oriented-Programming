package com.lab6;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


class BinarySearch{
    public static boolean search(int[] array, int parameter){
        int left=0;
        int right=array.length-1;
        while(left<=right){
            int middle=left+(right-left)/2;
            if(array[middle]==parameter){
                return true;
            }
            if(array[middle]<parameter){
                left=middle+1;
            }
            else{
                right=middle-1;
            }
        }
        return false;
    }
}
class Main {
    public static int smallest(int[] array){
        int min=array[0];
        for(int element : array){
            if(element<min){
                min=element;
            }
        }
        return min;
    }
    public static int indexOfSmallest(int[] array){
        int smallest=smallest(array);
        for(int i=0;i<array.length;i++){
            if(array[i]==smallest){
                return i;
            }
        }
        return -1;
    }
    public static int indexOfTheSmallestStartingFrom(int[] array, int index){
        int minimumValue=array[index]; 
        int minimumIndex=index;
        for(int i=index;i<array.length;i++){
            if(array[i]<minimumValue){
                minimumValue=array[i];
                minimumIndex=i;      
            }
        }
        return minimumIndex;
    }
    public static void swap(int[] array, int index1, int index2){
        int temp=array[index1];
        array[index1]=array[index2];
        array[index2]=temp;
    }
    public static void printElegantly(int[]array) {
        for(int i=0;i<array.length;i++){
            if(i==array.length-1){
                System.out.print(array[i]+"");
            }
            else{
                System.out.print(array[i]+", ");
            }
        }
    }

    
    public static void main(String[] args) {
        //First task

        int[] values = {6, 5, 8, 6, 11};
        System.out.println("smallest: " + smallest(values));

        //Second task

        System.out.println("Index of smallest: " + indexOfSmallest(values));

        //Third task

        int[] values2 = {-1, 6, 9, 8, 12};
        System.out.println(indexOfTheSmallestStartingFrom(values2, 1));
        System.out.println(indexOfTheSmallestStartingFrom(values2, 2));
        System.out.println(indexOfTheSmallestStartingFrom(values2, 4));

        //Fourth task

        int[] values3 = {3, 2, 5, 4, 8};
        System.out.println(Arrays.toString(values3));
        swap(values3, 1, 0);
        System.out.println(Arrays.toString(values3));
        swap(values3, 0, 3);
        System.out.println(Arrays.toString(values3));

        //Fifth task

        int[] array = {-3, 2, 3, 4, 7, 8, 12};
        Scanner reader = new Scanner(System.in);
        System.out.println("Values of the array: " + Arrays.toString(array));
        System.out.println();
        System.out.println("Enter searched number: ");
        String searchedValue = reader.nextLine();
        System.out.println();
        boolean result = BinarySearch.search(array, Integer.parseInt(searchedValue));
        if(result){
            System.out.println("Value "+searchedValue+" is in the array!\n");
        }
        else{
            System.out.println("Value "+searchedValue+" is not in the array!\n");
        }

        //Sixth task

        int[] array2 = {5, 1, 3, 4, 2};
        printElegantly(array2);
        System.out.println("\n");

        //PART 1
        NightSky nightSky = new NightSky(0.1, 40, 10);
        nightSky.printLine();
        System.out.println("Number of stars: "+nightSky.starsInLastPrint());
        NightSky nightSky2 = new NightSky(8, 4);
        nightSky2.print();
        System.out.println("Number of stars: "+nightSky2.starsInLastPrint());

    }
}
class NightSky{
    private double density;
    private int width;
    private int height;
    private int starsInLastPrint;
    public NightSky(double density){
        this.density=density;
        this.width=20;
        this.height=10;
        this.starsInLastPrint=0;
    }
    public NightSky(int width, int height){
        this.width=width;
        this.height=height;
        this.density=0.1;
        this.starsInLastPrint=0;
    }
    public NightSky(double density, int width, int height){
        this.density=density;
        this.width=width;
        this.height=height;
        this.starsInLastPrint=0;
    }
    public int starsInLastPrint(){
        return this.starsInLastPrint;
    }
    public void printLine(){
        Random random = new Random();
        for(int i=0;i<this.width;i++){
            if (random.nextDouble()<=this.density){
                System.out.print("*");
                this.starsInLastPrint++;
            }
            else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    public void print(){
        for(int i=0;i<this.height;i++){
            printLine();
        }
    }
    public double getDensity() {
        return density;
    }
    public void setDensity(double density) {
        this.density = density;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getStarsInLastPrint() {
        return starsInLastPrint;
    }
    public void setStarsInLastPrint(int starsInLastPrint) {
        this.starsInLastPrint = starsInLastPrint;
    }
}