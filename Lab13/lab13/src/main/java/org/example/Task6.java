package org.example;

import java.util.ArrayList;
import java.util.List;

public class Task6 {
    public static void main(String[] args) {
        CoffeeDirector director = new CoffeeDirector();
        Coffee.CoffeeBuilder espressoBuilder = new Coffee.EspressoBuilder();
        director.constructCoffee(espressoBuilder, "Small", List.of("Extra Shot"));
        Coffee espresso = espressoBuilder.getCoffee();
        System.out.println(espresso);
        Coffee.CoffeeBuilder latteBuilder = new Coffee.LatteBuilder();
        director.constructCoffee(latteBuilder, "Medium", List.of("Vanilla Syrup", "Whipped Cream"));
        Coffee latte = latteBuilder.getCoffee();
        System.out.println(latte);
        Coffee.CoffeeBuilder cappuccinoBuilder = new Coffee.CappuccinoBuilder();
        director.constructCoffee(cappuccinoBuilder, "Large", List.of("Cinnamon"));
        Coffee cappuccino = cappuccinoBuilder.getCoffee();
        System.out.println(cappuccino);
    }
}
class Coffee {
    private String type;
    private String size;
    private List<String> toppings;
    private Coffee(String type, String size, List<String> toppings) {
        this.type = type;
        this.size = size;
        this.toppings = toppings;
    }
    public String getType() {
        return type;
    }
    public String getSize() {
        return size;
    }
    public List<String> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", toppings=" + toppings +
                '}';
    }
    public interface CoffeeBuilder{
        void buildType();
        void buildSize(String size);
        void buildToppings(List<String> toppings);
        Coffee getCoffee();
    }
    static class EspressoBuilder implements CoffeeBuilder {
        private String type;
        private String size;
        private List<String> toppings = new ArrayList<>();
        @Override
        public void buildType() {
            this.type = "Espresso";
        }
        @Override
        public void buildSize(String size) {
            this.size = size;
        }
        @Override
        public void buildToppings(List<String> toppings) {
            this.toppings.addAll(toppings);
        }
        @Override
        public Coffee getCoffee() {
            return new Coffee(type, size, toppings);
        }
    }
    static class LatteBuilder implements CoffeeBuilder {
        private String type;
        private String size;
        private List<String> toppings = new ArrayList<>();
        @Override
        public void buildType() {
            this.type = "Latte";
        }
        @Override
        public void buildSize(String size) {
            this.size = size;
        }
        @Override
        public void buildToppings(List<String> toppings) {
            this.toppings.addAll(toppings);
        }
        @Override
        public Coffee getCoffee() {
            return new Coffee(type, size, toppings);
        }
    }
    static class CappuccinoBuilder implements CoffeeBuilder {
        private String type;
        private String size;
        private List<String> toppings = new ArrayList<>();
        @Override
        public void buildType() {
            this.type = "Cappuccino";
        }
        @Override
        public void buildSize(String size) {
            this.size = size;
        }
        @Override
        public void buildToppings(List<String> toppings) {
            this.toppings.addAll(toppings);
        }
        @Override
        public Coffee getCoffee() {
            return new Coffee(type, size, toppings);
        }
    }
}
class CoffeeDirector {
    public void constructCoffee(Coffee.CoffeeBuilder builder, String size, List<String> toppings) {
        builder.buildType();
        builder.buildSize(size);
        builder.buildToppings(toppings);
    }
}