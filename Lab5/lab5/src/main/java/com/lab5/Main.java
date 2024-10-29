package com.lab5;

import java.util.ArrayList;
import java.util.List;

interface Repairable {
    public String repair();
}
abstract class Vehicle {
    private String modelName;
    private int mileage;
    private int health;
    public Vehicle(String modelName) {
        this.modelName=modelName;
        this.mileage=0;
        this.health=100;
    }
    public Vehicle(String modelName, int mileage, int health) {
        this.modelName=modelName;
        this.mileage=mileage;
        if(health>100 || health<0){
            this.health=100;
        } 
        else{
            this.health=health;
        }
    }
    public String getModelName() {
        return modelName;
    }
    public int getMileage() {
        return mileage;
    }
    public int getHealth() {
        return health;
    }
    public void setModelName(String modelName) {
        this.modelName=modelName;
    }
    public void setMileage(int mileage) {
        this.mileage=mileage;
    }
    public void setHealth(int health) {
        this.health=health;
    }
    abstract public String service();
    abstract public int expectedLifespan();
    public boolean needsMaintenance() {
        return this.health<70;
    }
    public int calculateRemainingLifespan() {
        int expectedLifespan=expectedLifespan();
        if(this.health<50){
            return expectedLifespan*this.health/100;
        }
        return expectedLifespan-this.mileage;
    }
    public void simulateYear() {
        if(this.mileage>expectedLifespan()/2) {
            this.health-=5;
        }
        calculateRemainingLifespan();
    }
    public void performMaintenance(Vehicle vehicle) {
        if(vehicle instanceof Car) {
            Car car=(Car) vehicle;
            car.repair();
            car.drive(100);
        } else if(vehicle instanceof Truck) {
            Truck truck=(Truck) vehicle;
            truck.repair();
            truck.haul(2000);
        } else if(vehicle instanceof Motorcycle) {
            Motorcycle motorcycle=(Motorcycle) vehicle;
            motorcycle.race(50);
        }
    }
}

class Car extends Vehicle implements Repairable {
    public Car(String modelName) {
        super(modelName);
    }
    public Car(String modelName, int mileage, int health) {
        super(modelName, mileage, health);
    }
    @Override
    public String repair() {
        return "Engine tuned and oil changed for " +this.getModelName();
    }
    @Override
    public String service() {
        return "Checking engine oil and changing oil for "+this.getModelName()+" "+this.getMileage()+" "+this.getHealth();
    }
    @Override
    public int expectedLifespan() {
        return 150000;
    }
    public void drive(int miles) {
        this.setMileage(this.getMileage()+miles);
        this.setHealth(this.getHealth()-5);
        if(this.getHealth()<30) {
            this.setMileage(this.getMileage()+(this.expectedLifespan()-10000));
        }
    }
}

class Truck extends Vehicle implements Repairable {
    public Truck(String modelName) {
        super(modelName);
    }
    public Truck(String modelName, int mileage, int health) {
        super(modelName, mileage, health);
    }
    @Override
    public String repair() {
        return "Engine overhauled and tires replaced for "+this.getModelName();
    }
    @Override
    public String service() {
        return "Overhauling engine and replacing tires for "+this.getModelName()+" "+this.getMileage()+" "+this.getHealth();
    }
    @Override
    public int expectedLifespan() {
        return 300000;
    }
    public void haul(int loadWeight) {
        if(loadWeight>5000) {
            this.setHealth(this.getHealth()-15);
        } 
        else{
            this.setHealth(this.getHealth()-10);
        }
        if(this.getHealth()<30) {
            this.setMileage(this.getMileage()-10000);
        }
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String modelName) {
        super(modelName);
    }
    public Motorcycle(String modelName, int mileage, int health) {
        super(modelName, mileage, health);
    }
    @Override
    public String service() {
        return "Motorcycle service: Lubricate chain and tune engine for "+this.getModelName()+" "+this.getMileage()+" "+this.getHealth();
    }

    @Override
    public int expectedLifespan() {
        return 50000;
    }
    public void race(int raceMiles) {
        this.setMileage(this.getMileage()+raceMiles);
        this.setHealth(this.getHealth()-10);
        if (this.getHealth()<40) {
            this.setMileage(this.getMileage()+(this.expectedLifespan()-5000));
        }
    }
}

class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicles=new ArrayList<>();
        vehicles.add(new Car("Skoda", 80000, 80));
        vehicles.add(new Truck("Volvo", 150000, 85));
        vehicles.add(new Motorcycle("Yamaha", 25000, 70));
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.service());
            vehicle.simulateYear();
            if (vehicle instanceof Car) {
                Car car=(Car) vehicle;
                car.drive(500);
            } else if (vehicle instanceof Truck) {
                Truck truck=(Truck) vehicle;
                truck.haul(6000);
            } else if (vehicle instanceof Motorcycle) {
                Motorcycle motorcycle=(Motorcycle) vehicle;
                motorcycle.race(200);
            }
        }
        /* testing
        System.out.println(vehicles.get(0).getMileage());
        if (vehicles.get(0) instanceof Car) {
            ((Car)vehicles.get(0)).drive(100);
        }
        System.out.println("New mileage: "+vehicles.get(0).getMileage());*/
    }
}
