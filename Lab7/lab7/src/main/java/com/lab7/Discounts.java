package com.lab7;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@FunctionalInterface
interface DiscountStrategy{
    public double applyDiscount(double price);
}
public class Discounts {
    public static void main(String[] args) {
        DiscountStrategy percentageDiscount = (x)->x*20/100;
        DiscountStrategy fixedDiscount = (x)->{
            if(x>5){
                return x-5;
            }
            return x;
        };
        DiscountStrategy bulkDiscount = (x)->{
            int quantity=5; 
            if(quantity>10){
                return x*0.7;
            }  
            else{
                return x;
            } 
        };
        /*Testing*/
        List<Product> listOfProducts = new ArrayList<>(Arrays.asList(new Product("Lemon", 10, 5), new Product("Bread", 2, 1)));
        double totalAsPercentageDiscount = applyTotalDiscount(listOfProducts, percentageDiscount);
        double totalAsFixedDiscount = applyTotalDiscount(listOfProducts, fixedDiscount);
        double totalAsBulkDiscount = applyTotalDiscount(listOfProducts, bulkDiscount);
        System.out.println("Percentage discount: "+totalAsPercentageDiscount+"\nFixed discount: "+totalAsFixedDiscount+"\nBulk discount: "+totalAsBulkDiscount);
    }
    public static double applyTotalDiscount(List<Product> listOfProducts, DiscountStrategy strategy) {
        double totalDiscountedPrice=0;
        for(Product product : listOfProducts){
            double discountedPrice=strategy.applyDiscount(product.getPrice());
            totalDiscountedPrice+=discountedPrice*product.getQuantity();
        }

        return totalDiscountedPrice;
    }
}
class Product implements DiscountStrategy{
    private String name;
    private int price;
    private int quantity;
    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public double applyDiscount(double price){
        return price;
    }
}