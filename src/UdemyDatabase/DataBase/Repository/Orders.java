package UdemyDatabase.DataBase.Repository;

import Databaser.Objmodel.Customer;

public class Orders {
    private int id;
    private int customerId;

    private String created;
    private String updated;
    private CustomersNot customer = new CustomersNot();

    public Orders(int id, int customerId, String created, String updated) {
        this.id = id;
        this.customerId = customerId;
        this.created = created;
        this.updated = updated;
    }

    public CustomersNot getCustomer() {
        return customer;
    }

    public void setCustomer(CustomersNot customer) {
        this.customer = customer;
    }

    public Orders() {
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
