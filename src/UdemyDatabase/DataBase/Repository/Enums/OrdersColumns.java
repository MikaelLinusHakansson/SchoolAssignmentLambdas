package UdemyDatabase.DataBase.Repository.Enums;

public enum OrdersColumns {
    TABLE_ORDER("Orders"),
    ID("Id"),
    CUSTOMERID("CustomerId"),
    CREATED("Created"),
    UPDATED("Updated");

        private String orders;
    OrdersColumns(String orders) {
        this.orders = orders;
    }

    public String getValue() {
        return orders;
    }
}
