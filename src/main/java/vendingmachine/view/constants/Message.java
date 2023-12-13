package vendingmachine.view.constants;

public enum Message {

    INPUT_VENDING_MACHINE_AMOUNT("자판기가 보유하고 있는 금액을 입력해 주세요."),
    INPUT_VENDING_MACHINE_ITEMS("\n" + "상품명과 가격, 수량을 입력해 주세요."),
    INPUT_AMOUNT("\n" + "투입 금액을 입력해 주세요."),
    INPUT_ITEMS("구매할 상품명을 입력해 주세요."),

    OUTPUT_VENDING_MACHINE_COIN("\n" + "자판기가 보유한 동전"),
    OUTPUT_VENDING_MACHINE_COIN_LIST("%d원 - %d개"),
    OUTPUT_INPUT_AMOUNT("\n" + "투입 금액: %d원"),
    OUTPUT_REMAIN_AMOUNT("잔돈"),


    ERROR_INPUT_EMPTY("입력값이 없습니다."),
    ERROR_NOT_INTEGER("금액과 수량은 숫자만 입력 가능합니다."),
    ERROR_VENDING_MACHINE_AMOUNT_UNIT("투입 금액은 10원 단위로 입력해주세요."),
    ERROR_PRODUCTS_MIN_PRICE("상품 가격은 100원 이상부터 설정 가능합니다."),
    ERROR_PRODUCTS_AMOUNT_UNIT("상품 가격은 10원 단위로 설정 가능합니다."),
    ERROR_PRODUCTS_QUANTITY("상품 수량은 1개 이상 설정해 주세요."),
    ERROR_PRODUCTS_INFO_SIZE("상품명, 상품 가격, 상품 수량을 모두 입력해주세요."),
    ERROR_PRODUCTS_NONE("존재하지 않는 상품 입니다."),
    ERROR_PRODUCTS_CANNOT_BUY("투입 금액이 부족하여 상품을 구매할 수 없습니다.");

    public static final String ERROR_PREFIX = "[ERROR] ";
    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return ERROR_PREFIX + message;
    }


}
