// src/view/CoffeeMachineView.java
package view;

import model.CoffeeMachine;
import model.CoffeeCup;

import java.util.Scanner;

public class CoffeeMachineView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMainMenu(boolean isLoggedIn) {
        if (isLoggedIn) {
            System.out.println("Write action: fill, remaining, take, exit");
        } else {
            System.out.println("Write action: buy, login, exit");
        }
    }

    public void displayCoffeeMenu(CoffeeMachine machine) {
        System.out.println("What do you want to buy?");
        for (int i = 1; i <= 3; i++) {
            CoffeeCup cup = machine.getCoffeeMenu().get(i);
            if (cup != null) {
                System.out.println(i + "- " + cup);
            }
        }
    }

    public String getUserAction() {
        return scanner.next();
    }

    public int getIntInput() {
        return scanner.nextInt();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
