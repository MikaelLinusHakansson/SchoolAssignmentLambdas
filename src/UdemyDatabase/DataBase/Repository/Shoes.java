package UdemyDatabase.DataBase.Repository;

public class Shoes {
    private int id;
    private String name;
    private int brandId;
    private int SizeId;
    private int colourId;
    private double price;
    private int stock;
    private int numberChoice;
    private Brand brand = new Brand();
    private Colours colour = new Colours();
    private Sizes size = new Sizes();

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Colours getColour() {
        return colour;
    }

    public void setColour(Colours colour) {
        this.colour = colour;
    }

    public Sizes getSize() {
        return size;
    }

    public void setSize(Sizes size) {
        this.size = size;
    }

    public int getNumberChoice() {
        return numberChoice;
    }

    public void setNumberChoice(int numberChoice) {
        this.numberChoice = numberChoice;
    }

    public Shoes(int id, String name, int brandId, int sizeId, int colourId, double price, int stock, int numberChoice) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.SizeId = sizeId;
        this.colourId = colourId;
        this.price = price;
        this.stock = stock;
        this.numberChoice = numberChoice;
    }



    public Shoes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getSizeId() {
        return SizeId;
    }

    public void setSizeId(int sizeId) {
        SizeId = sizeId;
    }

    public int getColourId() {
        return colourId;
    }

    public void setColourId(int colourId) {
        this.colourId = colourId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
