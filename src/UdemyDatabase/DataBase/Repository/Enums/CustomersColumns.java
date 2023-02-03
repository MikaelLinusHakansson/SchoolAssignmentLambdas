package UdemyDatabase.DataBase.Repository.Enums;

public enum CustomersColumns {
    TABLE_NAME("Customer"),
    ID("Id"),
    SSA("SSA"),
    NAME("CustomerName"),
    ADRESS("Adress"),
    ZIPCODE("Zipcode"),
    REGION("Region"),
    PASSWORD("UserPassWord");

    public String name;
    CustomersColumns(String customers) {
        this.name = customers;
    }

    public String getValue() {
        return name;
    }
}
