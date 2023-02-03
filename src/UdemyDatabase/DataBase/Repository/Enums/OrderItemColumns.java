package UdemyDatabase.DataBase.Repository.Enums;

public enum OrderItemColumns {
    TABLE_ORDERITEM("OrderItem"),
    ID("Id"),
    SHOESID("ShoesId"),
    ORDERID("OrderId"),
    QUANTITY("Quantity");

    private String orderItem;

    OrderItemColumns(String orderItem) {
        this.orderItem = orderItem;
    }

    public String getValue() {
        return orderItem;
    }
}
