package vendingmachine.domain;

import vendingmachine.view.constants.Message;

public class Products {

    private String name;
    private int price;
    private int quantity;

    public Products(String name, int price, int quantity) {
        this.name = name;
        this.price = validatePrice(price);
        this.quantity = validateQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    private int validatePrice(int price) {
        validateMinPrice(price);
        validateAmountUnit(price);
        return price;
    }

    private void validateMinPrice(int price) {
        if (price < 100) {
            throw new IllegalArgumentException(Message.ERROR_PRODUCTS_MIN_PRICE.getErrorMessage());
        }
    }

    private void validateAmountUnit(int price) {
        if (price % 10 != 0) {
            throw new IllegalArgumentException(Message.ERROR_PRODUCTS_AMOUNT_UNIT.getErrorMessage());
        }
    }

    private int validateQuantity(int price) {
        if (price < 1) {
            throw new IllegalArgumentException(Message.ERROR_PRODUCTS_QUANTITY.getErrorMessage());
        }
        return price;
    }
}
