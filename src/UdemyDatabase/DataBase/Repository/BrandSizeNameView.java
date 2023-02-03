package UdemyDatabase.DataBase.Repository;

import java.util.List;

public class BrandSizeNameView {
    private List<CustomersNot> customer;
    private List<Colours> colours;
    private List<Category> category;
    private List<Brand> brand;
    private List<Orders> orders;
    private List<Shoes> shoe;
    private List<OrderItem> orderItem;


    public BrandSizeNameView(List<CustomersNot> customer, List<Colours> colours, List<Category> category, List<Brand> brand, List<Orders> orders, List<Shoes> shoe, List<OrderItem> orderItem) {
        this.customer = customer;
        this.colours = colours;
        this.category = category;
        this.brand = brand;
        this.orders = orders;
        this.shoe = shoe;
        this.orderItem = orderItem;
    }

    public List<CustomersNot> getCustomer() {
        return customer;
    }

    public void setCustomer(List<CustomersNot> customer) {
        this.customer = customer;
    }

    public List<Colours> getColours() {
        return colours;
    }

    public void setColours(List<Colours> colours) {
        this.colours = colours;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public List<Shoes> getShoe() {
        return shoe;
    }

    public void setShoe(List<Shoes> shoe) {
        this.shoe = shoe;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public BrandSizeNameView() {
    }


}
