// src/controller/CoffeeMachineController.java
package controller;

import model.CoffeeMachine;
import view.CoffeeMachineView;

public class CoffeeMachineController {
    private final CoffeeMachine coffeeMachine;
    private final CoffeeMachineView view;

    public CoffeeMachineController(CoffeeMachine coffeeMachine, CoffeeMachineView view) {
        this.coffeeMachine = coffeeMachine;
        this.view = view;
    }

    public void start() {
        while (true) {
            view.displayMainMenu(coffeeMachine.isLoggedIn());
            String action = view.getUserAction();

            if (coffeeMachine.isLoggedIn()) {
                handleAdminActions(action);
            } else {
                handleUserActions(action);
            }
        }
    }

    private void handleUserActions(String action) {
        switch (action) {
            case "buy" -> {
                view.displayCoffeeMenu(coffeeMachine);
                int choice = view.getIntInput();
                view.displayMessage(coffeeMachine.buy(choice));
            }
            case "login" -> {
                view.displayMessage("Enter username:");
                String username = view.getUserAction();
                view.displayMessage("Enter password:");
                String password = view.getUserAction();
                coffeeMachine.login(username, password);
                view.displayMessage(coffeeMachine.isLoggedIn() ? "Login successful!" : "Wrong username or password");
            }
            case "exit" -> {
                coffeeMachine.saveStatus("coffee_machine_status.txt");
                view.displayMessage("Exiting and saving current status.");
                System.exit(0);
            }
            default -> view.displayMessage("Invalid action.");
        }
    }

    private void handleAdminActions(String action) {
        switch (action) {
            case "fill" -> {
                view.displayMessage("Write how many ml of water you want to add:");
                int water = view.getIntInput();
                view.displayMessage("Write how many ml of milk you want to add:");
                int milk = view.getIntInput();
                view.displayMessage("Write how many grams of coffee beans you want to add:");
                int coffeeBeans = view.getIntInput();
                view.displayMessage("Write how many disposable cups you want to add:");
                int cups = view.getIntInput();
                coffeeMachine.fill(water, milk, coffeeBeans, cups);
            }
            case "remaining" -> view.displayMessage(coffeeMachine.remaining());
            case "take" -> view.displayMessage("Money taken: $" + coffeeMachine.take());
            case "exit" -> {
                coffeeMachine.saveStatus("coffee_machine_status.txt");
                view.displayMessage("Exiting and saving current status.");
                System.exit(0);
            }
            default -> view.displayMessage("Invalid action.");
        }
    }
}
