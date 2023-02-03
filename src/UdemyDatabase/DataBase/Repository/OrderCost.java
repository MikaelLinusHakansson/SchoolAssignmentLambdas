package UdemyDatabase.DataBase.Repository;

import java.util.List;

public class OrderCost {
    private String custoemrName;
    private List<CustomersNot> customer;
    private double price;
    private List<Shoes> shoe;

    public OrderCost(String custoemrName, double price) {
        this.custoemrName = custoemrName;
        this.price = price;
    }

    public OrderCost(List<CustomersNot> customer, List<Shoes> shoe) {
        this.customer = customer;
        this.shoe = shoe;
    }

    public OrderCost() {
    }

    public List<CustomersNot> getCustomer() {
        return customer;
    }

    public void setCustomer(List<CustomersNot> customer) {
        this.customer = customer;
    }

    public List<Shoes> getShoe() {
        return shoe;
    }

    public void setShoe(List<Shoes> shoe) {
        this.shoe = shoe;
    }

    public String getCustoemrName() {
        return custoemrName;
    }

    public void setCustoemrName(String custoemrName) {
        this.custoemrName = custoemrName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
