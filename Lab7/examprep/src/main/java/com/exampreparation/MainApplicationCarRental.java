package com.exampreparation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum CarType{
    SEDAN, SUV, TRUCK, VAN, SPORTS
}
interface Rentable{
    public double applyDiscount(double discountRate);
    public String getDescription();
}
public class MainApplicationCarRental {
    
}
abstract class Vehicle implements Rentable{
    private String vehicleId;
    private String name;
    private double rentalRate;
    private HashMap<Date, Integer> kilometersRecord;
    public Vehicle(String vehicleId, String name, double rentalRate) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.rentalRate = rentalRate;
        this.kilometersRecord= new HashMap<>();
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getRentalRate() {
        return rentalRate;
    }
    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }
    public HashMap<Date, Integer> getKilometersRecord() {
        return kilometersRecord;
    }
    public void setKilometersRecord(HashMap<Date, Integer> kilometersRecord) {
        this.kilometersRecord = kilometersRecord;
    }
    public void addKilometers(Date rentalDate, int kilometers){
        this.kilometersRecord.put(rentalDate, this.kilometersRecord.getOrDefault(rentalDate,0)+kilometers);
    }
    public int getTotalKilometers(){
        int totalKilometersTraveled=0;
        for(Map.Entry<Date, Integer> entry : this.kilometersRecord.entrySet()){
            totalKilometersTraveled+=entry.getValue();
        }
        return totalKilometersTraveled;
    }
    @Override
    abstract public String getDescription();
    @Override
    abstract public double applyDiscount(double discountRate);
    @Override
    public String toString(){
        return "Name: "+this.name+", ID: "+this.vehicleId+", Rental Rate: "+this.rentalRate; 
    }
}

class Car extends Vehicle{
    private CarType carType;
    private String engineType;
    public Car(String vehicleId, String name, double rentalRate, CarType carType, String engineType){
        super(vehicleId, name, rentalRate);
        this.carType=carType;
        this.engineType=engineType;
    }
    public CarType getCarType() {
        return carType;
    }
    public void setCarType(CarType carType) {
        this.carType = carType;
    }
    public String getEngineType() {
        return engineType;
    }
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
    @Override
    public String getDescription(){
        return "Car type: "+this.carType+", Engine type: "+this.engineType;
    }
}

class Motorcycle extends Vehicle{
    private double engineCapacity;
    public Motorcycle(String vehicleId, String name, double rentalRate, double engineCapacity) {
        super(vehicleId, name, rentalRate);
        this.engineCapacity = engineCapacity;
    }
    public double getEngineCapacity() {
        return engineCapacity;
    }
    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
    @Override
    public String getDescription(){
        return "Engine capacity: "+this.engineCapacity;
    }
}

class RentalTransaction <T extends Vehicle & Rentable>{
    private String transactionId;
    private Date rentalDate;
    private HashMap<T, Integer> rentedVehicles; //Map of vehicles and their rental days
    private int customerId;
    public RentalTransaction(String transactionId, Date rentalDate, int customerId) {
        this.transactionId = transactionId;
        this.rentalDate = rentalDate;
        this.customerId = customerId;
        this.rentedVehicles=new HashMap<>();
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public Date getRentalDate() {
        return rentalDate;
    }
    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }
    public HashMap<T, Integer> getRentedVehicles() {
        return rentedVehicles;
    }
    public void setRentedVehicles(HashMap<T, Integer> rentedVehicles) {
        this.rentedVehicles = rentedVehicles;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void addVehicleToRental(T vehicle, int days){
        this.rentedVehicles.put(vehicle, days);
    }
    public double calculateTotalAmount(){
        double totalAmount=0.0;
        for(Map.Entry<T, Integer> entry : this.rentedVehicles.entrySet()){
            totalAmount+=entry.getValue()*entry.getKey().getRentalRate()*(entry.getKey().applyDiscount(20)/100);
        }
        return totalAmount;
 
    }
}

class Customer{
    private String customerId;
    private String name;
    private String phoneNumber;
    private List<RentalTransaction<? extends Vehicle>> rentalHistory;
    public Customer(String customerId, String name, String phoneNumber){
        this.customerId=customerId;
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.rentalHistory=new ArrayList<>();
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
    public List<RentalTransaction<? extends Vehicle>> getRentalHistory() {
        return rentalHistory;
    }
    public void setRentalHistory(List<RentalTransaction<? extends Vehicle>> rentalHistory) {
        this.rentalHistory = rentalHistory;
    }
    public void addRentalTransaction(RentalTransaction<? extends Vehicle> transaction){
        this.rentalHistory.add(transaction);
    }
    @Override
    public String toString(){
        return "Customer name: "+this.name+", ID: "+this.customerId+", Number: "+this.phoneNumber;
    }
}

class RentalCompany{
    private String companyName;
    private List<Customer> customers;
    private Map<String, Vehicle> vehicleInventory;
    public RentalCompany(String companyName){
        this.companyName=companyName;
        this.customers=new ArrayList<>();
        this.vehicleInventory=new HashMap<>();
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public Map<String, Vehicle> getVehicleInventory() {
        return vehicleInventory;
    }
    public void setVehicleInventory(Map<String, Vehicle> vehicleInventory) {
        this.vehicleInventory = vehicleInventory;
    }
    public void addCustomer(Customer customer){
        this.customers.add(customer);
    }
    public Customer getCustomer(String customerId){
        for(Customer customer : this.customers){
            if(customerId.equals(customer.getCustomerId())){
                return customer;
            }
        }
        return null;
    }
    public void addVehicle(Vehicle vehicle){
        if(vehicle instanceof Car){
            Car myNewCar = (Car) vehicle;
            this.vehicleInventory.put(vehicle.getVehicleId(), myNewCar);
        }
        else if(vehicle instanceof Motorcycle){
            Motorcycle myNewMotorcycle = (Motorcycle) vehicle;
            this.vehicleInventory.put(vehicle.getVehicleId(), myNewMotorcycle);
        }
        else{
            System.out.println("The type you've provided is not of vehicle.");
        }
    }
    public void displayInventory(){
        for(Map.Entry<String, Vehicle> entry : this.vehicleInventory.entrySet()){
            System.out.println(entry.getValue());
        }
    }
    public double calculateTotalRevenue(){
        double totalRevenue=0.0;
        for(Customer customer : this.customers){
            for(RentalTransaction<? extends Vehicle> transaction : customer.getRentalHistory()){
                totalRevenue+=transaction.calculateTotalAmount();
            }
        }
        return totalRevenue;
    }
}