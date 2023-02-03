package UdemyDatabase.DataBase.Repository;

import java.util.List;

public class amountOfMoneyCustomer {
    private int orderItemId;
    private List<OrderItem> orderitem;
    private List<Shoes> shoe;
    private List<Orders> order;
    private List<CustomersNot> customer;
    private double price;
    private int customerId;
    private int orderId;
    private int quantity;
    private String customerName;
    private int shoeId;

    public amountOfMoneyCustomer(List<OrderItem> orderitem, List<Shoes> shoe, List<Orders> order, List<CustomersNot> customer) {
        this.orderitem = orderitem;
        this.shoe = shoe;
        this.order = order;
        this.customer = customer;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getShoeId() {
        return shoeId;
    }

    public void setShoeId(int shoeId) {
        this.shoeId = shoeId;
    }

    public amountOfMoneyCustomer(int orderItemId, double price, int customerId, int orderId, int quantity, String customerName, int shoeId) {
        this.orderItemId = orderItemId;
        this.price = price;
        this.customerId = customerId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.customerName = customerName;
        this.shoeId = shoeId;
    }

    public amountOfMoneyCustomer() {
    }

    public List<OrderItem> getOrderitem() {
        return orderitem;
    }

    public void setOrderitem(List<OrderItem> orderitem) {
        this.orderitem = orderitem;
    }

    public List<Shoes> getShoe() {
        return shoe;
    }

    public void setShoe(List<Shoes> shoe) {
        this.shoe = shoe;
    }

    public List<Orders> getOrder() {
        return order;
    }

    public void setOrder(List<Orders> order) {
        this.order = order;
    }

    public List<CustomersNot> getCustomer() {
        return customer;
    }

    public void setCustomer(List<CustomersNot> customer) {
        this.customer = customer;
    }














    /*public amountOfMoneyCustomer(double price, int customerId, int orderId, int quantity, String customerName, int shoeId, int orderItemId) {
        this.price = price;
        this.customerId = customerId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.customerName = customerName;
        this.shoeId = shoeId;
        this.orderItemId = orderItemId;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public amountOfMoneyCustomer() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getShoeId() {
        return shoeId;
    }

    public void setShoeId(int shoeId) {
        this.shoeId = shoeId;
    }*/
}
