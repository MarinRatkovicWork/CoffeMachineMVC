// src/model/CoffeeCup.java
package model;

public class CoffeeCup {
    private String type;
    private int waterNeeded;
    private int milkNeeded;
    private int coffeeBeansNeeded;
    private int price;

    public CoffeeCup(String type, int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int price) {
        this.type = type;
        this.waterNeeded = waterNeeded;
        this.milkNeeded = milkNeeded;
        this.coffeeBeansNeeded = coffeeBeansNeeded;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getMilkNeeded() {
        return milkNeeded;
    }

    public int getCoffeeBeansNeeded() {
        return coffeeBeansNeeded;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return type + " (Water: " + waterNeeded + "ml, Milk: " + milkNeeded + "ml, Coffee Beans: " + coffeeBeansNeeded + "g, Price: $" + price + ")";
    }
}
