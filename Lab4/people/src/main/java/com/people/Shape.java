package com.people;

enum FillType{
    FILLED, NOT_FILLED
}
public class Shape {
    private String color;
    private FillType filled;
    public Shape(String color, FillType filled){
        this.color=color;
        this.filled=filled;
    }
    public void displayInfo(){
        System.out.println("\nColor: "+this.color+"\nFilled: "+this.filled);
    }
    public double getArea(){
        return 0;
    }
    public static void main(String[] args) {
        Circle circle1=new Circle("red", FillType.FILLED, 10);
        Rectangle rect1 = new Rectangle("blue", FillType.FILLED, 5, 4);
        circle1.displayInfo();
        System.out.println(circle1.getArea());
        rect1.displayInfo();
        System.out.println(rect1.getArea());
    }
}
class Circle extends Shape{
    private double radius;
    public Circle(String color, FillType filled, double radius){
        super(color, filled);
        this.radius=radius;
    }
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Radius: "+this.radius);
    }
    @Override
    public double getArea(){
        return this.radius*this.radius*3.14;
    }
    public double calculateCircumference(double pi, double r){
        return 2*pi*r;
    }
    public double calculateCircumference(double r){
        return 2*3.14*r;
    }
}
class Rectangle extends Shape{
    private double width;
    private double height;
    public Rectangle(String color, FillType filled, double width, double height){
        super(color, filled);
        this.width=width;
        this.height=height;
    }
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Width: "+this.width+"\nHeight: "+this.height);
    }
    @Override
    public double getArea(){
        return this.height*this.width;
    }
}