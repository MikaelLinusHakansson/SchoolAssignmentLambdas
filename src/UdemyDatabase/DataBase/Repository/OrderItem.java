package UdemyDatabase.DataBase.Repository;

import Databaser.Objmodel.Customer;

public class OrderItem {
    private int id;
    private int quantity;
    private int orderId;
    private int shoesId;
    private Orders order = new Orders();
    private Shoes shoes = new Shoes();

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public OrderItem(int id, int quantity, int orderId, int shoesId) {
        this.id = id;
        this.quantity = quantity;
        this.orderId = orderId;
        this.shoesId = shoesId;
    }

    public OrderItem() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getShoesId() {
        return shoesId;
    }

    public void setShoesId(int shoesId) {
        this.shoesId = shoesId;
    }
}
