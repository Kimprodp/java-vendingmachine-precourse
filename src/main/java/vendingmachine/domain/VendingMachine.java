package vendingmachine.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import vendingmachine.view.constants.Message;

public class VendingMachine {

    private CoinBalance coinBalance;
    private List<Products> items;
    private int inputAmount;

    public VendingMachine(CoinBalance coinBalance) {
        this.coinBalance = coinBalance;
        this.items = new ArrayList<>();
        this.inputAmount = 0;
    }

    public void addItems(List<Products> products) {
        items.addAll(products);
    }

    public void putMoney(int inputAmount) {
        this.inputAmount = inputAmount;
    }

    public LinkedHashMap<Coin, Integer> getBalance() {
        return coinBalance.getBalance();
    }

    public int getInputAmount() {
        return inputAmount;
    }

    public boolean canBuyProducts() {
        OptionalInt minPrice = items.stream()
                .mapToInt(Products::getPrice)
                .min();

        return minPrice.isPresent() && minPrice.getAsInt() <= inputAmount && items.stream()
                .anyMatch(item -> item.getQuantity() > 1);
    }

    public void buyProducts(String productName) {
        int productPrice = items.stream()
                .filter(item -> item.getName().equals(productName))
                .mapToInt(Products::getPrice)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(Message.ERROR_PRODUCTS_NONE.getErrorMessage()));

        if (productPrice > inputAmount) {
            throw new IllegalArgumentException(Message.ERROR_PRODUCTS_CANNOT_BUY.getErrorMessage());
        }
        inputAmount -= productPrice;
    }

    public LinkedHashMap<Coin, Integer> returnTheChange() {
        int returnAmount = inputAmount;
        LinkedHashMap<Coin, Integer> balance = new LinkedHashMap<>();

        for (Coin coin : coinBalance.getBalance().keySet()) {
            int remainQuantity = coinBalance.getBalance().get(coin);
            int remainAmount = remainQuantity * coin.getAmount();

            if (remainQuantity == 0) { // 해당 동전이 없으면 다음 동전으로 넘어감
                continue;
            }

            if (remainAmount < returnAmount) {
                balance.put(coin, remainQuantity);
                returnAmount -= remainAmount;
                coinBalance.useCoin(coin, remainQuantity);
            }
            if (remainAmount > returnAmount) {
                int returnQuantity = returnAmount / coin.getAmount();
                balance.put(coin, returnQuantity);
                returnAmount -= returnQuantity * coin.getAmount();
                coinBalance.useCoin(coin, returnQuantity);
            }
        }

        return balance;
    }
}
