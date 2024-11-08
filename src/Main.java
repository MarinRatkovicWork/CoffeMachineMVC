// src/Main.java
import controller.CoffeeMachineController;
import model.CoffeeMachine;
import view.CoffeeMachineView;

public class Main {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = CoffeeMachine.loadStatus("coffee_machine_status.txt");
        CoffeeMachineView view = new CoffeeMachineView();
        CoffeeMachineController controller = new CoffeeMachineController(coffeeMachine, view);
        controller.start();
    }
}
