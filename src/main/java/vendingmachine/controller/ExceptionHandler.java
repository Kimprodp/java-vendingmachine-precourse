package vendingmachine.controller;

import java.util.function.Supplier;
import vendingmachine.view.OutputView;

public class ExceptionHandler {

    public static <T> T retryInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    public static void retryInput(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
