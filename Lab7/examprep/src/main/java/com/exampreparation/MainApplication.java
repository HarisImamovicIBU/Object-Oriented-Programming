package com.exampreparation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApplication {
    public static void main(String[] args) {
        /*I'll use the Example Scenario for testing purposes */
        /*1. Create instances of Dish and Drink with unique attributes 
        (e.g., cuisine type and main ingredient for Dish, volume for Drink). */
        
        Dish dish1 = new Dish("123", "Burek", 10, "Meat", CuisineType.BOSNIAN);
        Drink drink1 = new Drink("321", "Tea", 3, 500);
        Dish dish2 = new Dish("124", "French Fries", 5, "Potato", CuisineType.FRENCH);
        Drink drink2 = new Drink("421", "Coffe", 2, 100);
        
        /*2. Add items to the restaurant menu */

        Restaurant restaurant = new Restaurant("Buregdzinica kod Hareta");
        restaurant.addMenuItem(dish1);
        restaurant.addMenuItem(dish2);
        restaurant.addMenuItem(drink1);
        restaurant.addMenuItem(drink2);

        /*3. Create reservations for both Drink and Dish items */

        TableReservation reservation = new TableReservation("1", new Date(), 7);
        reservation.addItemToOrder(dish1, 5);
        reservation.addItemToOrder(drink1, 10);
        reservation.addItemToOrder(dish2, 3);
        reservation.addItemToOrder(drink2, 8);

        /*4. Create a customer who makes multiple reservations with both dish and drink items */

        Customer customer = new Customer("123", "Mujo", "033555333");
        customer.addReservation(reservation);

        /*5. Add the customer to the restaurant's system and display their reservation history */

        restaurant.addCustomer(customer);
        System.out.println("Reservation history: ");
        for(Customer c : restaurant.getCustomers()){
            System.out.println("Name: "+c.getName()+", ID: "+c.getCustomerId()+", Number: "+c.getPhoneNumber());
        }

        /*6. Calculate total sales for the restuarant, considering all reservations */
        System.out.println("Total sales of the restaurant " + restaurant.getRestaurantName()+": "+restaurant.calculateTotalSales());
    }
}
enum CuisineType{
    ITALIAN, CHINESE, MEXICAN, INDIAN, FRENCH, BOSNIAN
}
interface Billable{
    public double applyDiscount(double discountRate);
    public String getDescription();
}
abstract class MenuItem implements Billable{
    private String code;
    private String name;
    private int price;
    public MenuItem(String code, String name, int price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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
    @Override
    abstract public double applyDiscount(double discountRate);
    @Override
    abstract public String getDescription();
}
class Dish extends MenuItem{
    private CuisineType type;
    private String mainIngredient;

    public Dish(String code, String name, int price, String mainIngredient, CuisineType type) {
        super(code, name, price);
        this.mainIngredient = mainIngredient;
        this.type = type;
    }
    public CuisineType getType() {
        return type;
    }

    public void setType(CuisineType type) {
        this.type = type;
    }

    public String getMainIngredient() {
        return mainIngredient;
    }

    public void setMainIngredient(String mainIngredient) {
        this.mainIngredient = mainIngredient;
    }
    @Override
    public String getDescription(){
        return "Main ingredient: "+this.mainIngredient+", Cuisine type: "+this.type;
    }
    @Override
    public double applyDiscount(double discountRate){
        return discountRate;
    }
}
class Drink extends MenuItem{
    private double volume;
    public Drink(String code, String name, int price, double volume){
        super(code,name,price);
        this.volume=volume;
    }
    @Override
    public String getDescription(){
        return "Volume: "+this.volume;
    }
    @Override
    public double applyDiscount(double discountRate){
        return discountRate;
    }
    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
}
class TableReservation<T extends MenuItem & Billable>{
    private String reservationId;
    private Date reservationDate;
    private HashMap<T, Integer> orderedItems; //Items and quantity
    private int tableNumber;
    public TableReservation(String reservationId, Date reservationDate, int tableNumber){
        this.reservationId=reservationId;
        this.reservationDate=reservationDate;
        this.tableNumber=tableNumber;
        this.orderedItems=new HashMap<>();
    }
    public void addItemToOrder(T item, int quantity){
        /*In case we don't want to override already existing quantity.
         *If we did want to do that we will just say: this.orderedItem.put(item,quantity);
        */

        this.orderedItems.put(item,this.orderedItems.getOrDefault(item, 0)+quantity);
    }
    public double calculateTotalAmount(){
        double totalAmount=0.0;
        for(Map.Entry<T, Integer> entry : this.orderedItems.entrySet()){
            totalAmount+=entry.getKey().getPrice()*entry.getValue()*entry.getKey().applyDiscount(20)/100;
            //If dicount rate is for example 20
        }
        return totalAmount;
    }
    public String getReservationId() {
        return reservationId;
    }
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
    public Date getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
    public HashMap<T, Integer> getOrderedItems() {
        return orderedItems;
    }
    public void setOrderedItems(HashMap<T, Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }
    public int getTableNumber() {
        return tableNumber;
    }
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
class Customer{
    private String customerId;
    private String name;
    private String phoneNumber;
    private List<TableReservation<? extends MenuItem>> reservations;
    public Customer(String customerId, String name, String phoneNumber){
        this.customerId=customerId;
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.reservations=new ArrayList<>();
    }
    public void addReservation(TableReservation<? extends MenuItem> reservation){
        reservations.add(reservation);
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public List<TableReservation<? extends MenuItem>> getReservations() {
        return reservations;
    }
    public void setReservations(List<TableReservation<? extends MenuItem>> reservations) {
        this.reservations = reservations;
    }
}
class Restaurant{
    private String restaurantName;
    private List<Customer> customers;
    private Map<String, MenuItem> menuItems;
    public Restaurant(String restaurantName){
        this.restaurantName=restaurantName;
        this.customers=new ArrayList<>();
        this.menuItems=new HashMap<>();
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void addMenuItem(MenuItem item){
        if(item instanceof Dish){
            Dish newDishItem = (Dish) item;
            menuItems.put(newDishItem.getName(), newDishItem);
        }
        else if(item instanceof Drink){
            Drink newDrinkItem = (Drink) item;
            menuItems.put(newDrinkItem.getName(), newDrinkItem);
        }
        else{
            System.out.println("The type you've provided is not of MenuItem.");
        }
    }
    public Customer getCustomer(String customerId){
        for(Customer customer : customers){
            if(customerId.equals(customer.getCustomerId())){
                return customer;
            }
        }
        return null; //In case there is no customer with such id
    }
    public void displayMenu(){
        for(Map.Entry<String, MenuItem> item : this.menuItems.entrySet()){
            System.out.println(item.getKey()+item.getValue()+item.getValue().getDescription());
        }
    }
    public double calculateTotalSales(){
        double totalSales=0.0;
        for(Customer customer : this.customers){
            for(TableReservation tableReservation : customer.getReservations()){
                totalSales+=tableReservation.calculateTotalAmount();
            }
        }
        return totalSales;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public Map<String, MenuItem> getMenuItems() {
        return menuItems;
    }
    public void setMenuItems(Map<String, MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}