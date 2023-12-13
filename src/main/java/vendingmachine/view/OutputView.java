package vendingmachine.view;

import vendingmachine.view.constants.Message;

public class OutputView {

    public static void printMessage(Message message) {
        System.out.println(message.getMessage());
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printBalance(Message message, int coin, int quantity) {
        System.out.println(String.format(message.getMessage(), coin, quantity));
    }

    public static void printAmount(Message message, int amount) {
        System.out.println(String.format(message.getMessage(), amount));
    }
}
