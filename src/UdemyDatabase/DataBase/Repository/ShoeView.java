package UdemyDatabase.DataBase.Repository;


public class ShoeView {
    private String nameOfShoe;
    private String nameOfBrand;
    private int brandId;
    private String nameOfColour;
    private int colourId;
    private int size;
    private int sizeId;
    private int price;
    private int stock;
    private int numberChoice;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getColourId() {
        return colourId;
    }

    public void setColourId(int colourId) {
        this.colourId = colourId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getNumberChoice() {
        return numberChoice;
    }

    public void setNumberChoice(int numberChoice) {
        this.numberChoice = numberChoice;
    }

    public ShoeView(String nameOfShoe, String nameOfBrand, String nameOfColour, int size, int price, int stock, int choice, int brandId, int colourId, int sizeId) {
        this.nameOfShoe = nameOfShoe;
        this.nameOfBrand = nameOfBrand;
        this.nameOfColour = nameOfColour;
        this.size = size;
        this.price = price;
        this.stock = stock;
        this.numberChoice = choice;
        this.brandId = brandId;
        this.colourId = colourId;
        this.sizeId = sizeId;

    }

    public String getNameOfShoe() {
        return nameOfShoe;
    }

    public void setNameOfShoe(String nameOfShoe) {
        this.nameOfShoe = nameOfShoe;
    }

    public String getNameOfBrand() {
        return nameOfBrand;
    }

    public void setNameOfBrand(String nameOfBrand) {
        this.nameOfBrand = nameOfBrand;
    }

    public String getNameOfColour() {
        return nameOfColour;
    }

    public void setNameOfColour(String nameOfColour) {
        this.nameOfColour = nameOfColour;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ShoeView() {
    }
}
