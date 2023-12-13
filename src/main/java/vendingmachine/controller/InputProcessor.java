package vendingmachine.controller;

import java.util.Arrays;
import java.util.List;
import vendingmachine.view.constants.Message;

public class InputProcessor {

    private static final String BRACKETS = "\\[|\\]";

    public static int validateInteger(String input) {
        validateEmpty(input);
        return separateInput(input);
    }

    public static String validateString(String input) {
        validateEmpty(input);
        return input;
    }

    public static List<String> validateListSize(String input) {
        List<String> separatedValue = separateList(input);
        if (separatedValue.size() != 3) {
            throw new IllegalArgumentException(Message.ERROR_PRODUCTS_INFO_SIZE.getErrorMessage());
        }
        return separatedValue;
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(Message.ERROR_INPUT_EMPTY.getErrorMessage());
        }
    }

    private static int separateInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(Message.ERROR_NOT_INTEGER.getErrorMessage());
        }
    }

    private static List<String> separateList(String input) {
        String noBrackets =  input.replaceAll(BRACKETS, "");
        return Arrays.asList(noBrackets.split(","));
    }
}
