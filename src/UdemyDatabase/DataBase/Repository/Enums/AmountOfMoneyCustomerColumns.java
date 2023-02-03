package UdemyDatabase.DataBase.Repository.Enums;

public enum AmountOfMoneyCustomerColumns {
    /*private int orderItemId;
    private double price;
    private int customerId;
    private int orderId;
    private int quantity;
    private String customerName;
    private int shoeId;*/

    ORDERITEMID("OrderId"),
    PRICE("Price"),
    CUSTOMERID("CustomerId"),
    QUANTITY("Quantity"),
    CUSTOMERNAME("CustomerName"),
    SHOEID("ShoesId"),
    ORDERID("Id");
    private String amountOfMoney;
    AmountOfMoneyCustomerColumns(String amount) {
        this.amountOfMoney = amount;
    }

    public String getValue() {
        return amountOfMoney;
    }
}
