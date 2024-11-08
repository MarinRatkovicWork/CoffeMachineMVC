// src/model/CoffeeMachine.java
package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;
    private boolean loggedIn = false;
    private String adminUsername;
    private String adminPassword;
    private Map<Integer, CoffeeCup> coffeeMenu;

    public CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
        initializeCoffeeMenu();
    }

    private void initializeCoffeeMenu() {
        coffeeMenu = new HashMap<>();
        coffeeMenu.put(1, new CoffeeCup("Espresso", 250, 0, 16, 4));
        coffeeMenu.put(2, new CoffeeCup("Latte", 350, 75, 20, 7));
        coffeeMenu.put(3, new CoffeeCup("Cappuccino", 200, 100, 12, 6));
    }

    public Map<Integer, CoffeeCup> getCoffeeMenu() {
        return coffeeMenu;
    }

    public void setAdminCredentials(String username, String password) {
        this.adminUsername = username;
        this.adminPassword = password;
    }

    public void login(String username, String password) {
        loggedIn = username.equals(adminUsername) && password.equals(adminPassword);
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void logout() {
        loggedIn = false;
    }

    public void fill(int water, int milk, int coffeeBeans, int cups) {
        this.water += water;
        this.milk += milk;
        this.coffeeBeans += coffeeBeans;
        this.cups += cups;
    }

    public String remaining() {
        return "CoffeeMachine{ water=" + water + ", milk=" + milk + ", coffeeBeans=" + coffeeBeans + ", cups=" + cups + ", money=" + money + " }";
    }

    public int take() {
        int moneyTaken = money;
        money = 0;
        return moneyTaken;
    }

    public String buy(int choice) {
        CoffeeCup cup = coffeeMenu.get(choice);
        if (cup == null) {
            return "Invalid choice.";
        }
        return makeCoffee(cup);
    }

    private String makeCoffee(CoffeeCup cup) {
        if (water >= cup.getWaterNeeded() && milk >= cup.getMilkNeeded() && coffeeBeans >= cup.getCoffeeBeansNeeded() && cups > 0) {
            water -= cup.getWaterNeeded();
            milk -= cup.getMilkNeeded();
            coffeeBeans -= cup.getCoffeeBeansNeeded();
            cups--;
            money += cup.getPrice();
            return "I have enough resources, making you a " + cup.getType();
        } else {
            return "Sorry, not enough resources";
        }
    }

    public void saveStatus(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(water + "; " + milk + "; " + coffeeBeans + "; " + cups + "; " + money);
            writer.newLine();
            writer.write(adminUsername + "; " + adminPassword);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving status: " + e.getMessage());
        }
    }

    public static CoffeeMachine loadStatus(String filename) {
        CoffeeMachine coffeeMachine;
        if (!Files.exists(Paths.get(filename))) {
            // Create a new machine with default resources and admin credentials if file does not exist
            coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
            coffeeMachine.setAdminCredentials("admin", "password123");
            coffeeMachine.saveStatus(filename);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String[] resources = reader.readLine().split("; ");
                int water = Integer.parseInt(resources[0]);
                int milk = Integer.parseInt(resources[1]);
                int coffeeBeans = Integer.parseInt(resources[2]);
                int cups = Integer.parseInt(resources[3]);
                int money = Integer.parseInt(resources[4]);

                coffeeMachine = new CoffeeMachine(water, milk, coffeeBeans, cups, money);

                String[] credentials = reader.readLine().split("; ");
                String username = credentials[0];
                String password = credentials[1];
                coffeeMachine.setAdminCredentials(username, password);
            } catch (IOException e) {
                System.out.println("Error loading status: " + e.getMessage());
                coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);  // Default values if loading fails
                coffeeMachine.setAdminCredentials("admin", "password123");
            }
        }
        return coffeeMachine;
    }
}
