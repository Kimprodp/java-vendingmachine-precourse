package vendingmachine.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinBalance;
import vendingmachine.domain.Products;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;
import vendingmachine.view.constants.Message;

public class Controller {

    public void run() {
        VendingMachine vendingMachine = setVendingMachine();
        printVendingMachineCoinBalance(vendingMachine);
        registerProducts(vendingMachine);
        requestMoney(vendingMachine);
        while (vendingMachine.canBuyProducts()) {
            ExceptionHandler.retryInput(() -> requestPurchaseProducts(vendingMachine));
        }
        requestChangeReturn(vendingMachine);
    }

    private VendingMachine setVendingMachine() {
        OutputView.printMessage(Message.INPUT_VENDING_MACHINE_AMOUNT);
        return new VendingMachine(ExceptionHandler.retryInput(this::setCoinBalance));
    }

    private void printVendingMachineCoinBalance(VendingMachine vendingMachine) {
        OutputView.printMessage(Message.OUTPUT_VENDING_MACHINE_COIN);
        LinkedHashMap<Coin, Integer> balance = vendingMachine.getBalance();
        for (Coin coin : balance.keySet()) {
            OutputView.printBalance(Message.OUTPUT_VENDING_MACHINE_COIN_LIST, coin.getAmount(), balance.get(coin));
        }
    }

    private void registerProducts(VendingMachine vendingMachine) {
        OutputView.printMessage(Message.INPUT_VENDING_MACHINE_ITEMS);
        List<Products> products = ExceptionHandler.retryInput(this::inputVendingMachineProducts);
        vendingMachine.addItems(products);
    }

    private void requestMoney(VendingMachine vendingMachine) {
        OutputView.printMessage(Message.INPUT_AMOUNT);
        vendingMachine.putMoney(
                ExceptionHandler.retryInput(() -> InputProcessor.validateInteger(InputView.readLine())));
    }

    private void requestPurchaseProducts(VendingMachine vendingMachine) {
        OutputView.printAmount(Message.OUTPUT_INPUT_AMOUNT, vendingMachine.getInputAmount());
        OutputView.printMessage(Message.INPUT_ITEMS);
        vendingMachine.buyProducts(InputProcessor.validateString(InputView.readLine()));
    }

    private void requestChangeReturn(VendingMachine vendingMachine) {
        OutputView.printAmount(Message.OUTPUT_INPUT_AMOUNT, vendingMachine.getInputAmount());
        LinkedHashMap<Coin, Integer> balance = vendingMachine.returnTheChange();
        for (Coin coin : balance.keySet()) {
            if (balance.get(coin) > 0) {
                OutputView.printBalance(Message.OUTPUT_VENDING_MACHINE_COIN_LIST, coin.getAmount(), balance.get(coin));
            }
        }
    }

    private CoinBalance setCoinBalance() {
        return new CoinBalance(inputVendingMachineAmount());
    }

    private int inputVendingMachineAmount() {
        return InputProcessor.validateInteger(InputView.readLine());
    }

    private List<Products> inputVendingMachineProducts() {
        List<String> separateInput = Arrays.asList(InputView.readLine().split(";"));

        List<Products> products = new ArrayList<>();
        for (String separateValue : separateInput) {
            List<String> productsInfo = InputProcessor.validateListSize(separateValue);
            String name = InputProcessor.validateString(productsInfo.get(0));
            int price = InputProcessor.validateInteger(productsInfo.get(1));
            int quantity = InputProcessor.validateInteger(productsInfo.get(2));
            products.add(new Products(name, price, quantity));
        }
        return products;
    }
}
