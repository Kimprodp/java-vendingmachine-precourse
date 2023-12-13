package vendingmachine.domain;

import java.util.LinkedHashMap;
import vendingmachine.view.constants.Message;

public class CoinBalance {

    private LinkedHashMap<Coin, Integer> balance;

    public CoinBalance(int inputAmount) {
        this.balance = new LinkedHashMap<>();
        setBalance(validateInputAmount(inputAmount));
    }

    public LinkedHashMap<Coin, Integer> getBalance() {
        return balance;
    }

    public void useCoin(Coin coin, int quantity) {
        //validateQuantity(coin, quantity);
        balance.replace(coin, balance.get(coin) - quantity);
    }

    private void setBalance(int inputAmount) {
        int remainAmount = inputAmount;
        for (Coin coin : Coin.values()) {
            int coinCount = remainAmount / coin.getAmount();
            balance.put(coin, coinCount);
            remainAmount %= coin.getAmount();
        }
    }

    private int validateInputAmount(int inputAmount) {
        if (inputAmount % Coin.COIN_10.getAmount() != 0) {
            throw new IllegalArgumentException(Message.ERROR_VENDING_MACHINE_AMOUNT_UNIT.getErrorMessage());
        }
        return inputAmount;
    }

    /*
    private void validateQuantity(Coin coin, int quantity) {
        if (balance.get(coin) < quantity) {
            throw new IllegalArgumentException(Message.ERROR_COIN_QUANTITY_NONE.getErrorMessage());
        }
    }

     */


}
