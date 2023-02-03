package UdemyDatabase.DataBase.Repository.Enums;

public enum ShoesColumns {
    SHOES("Shoes"),
    ID("Id"),
    NAME("NameOfShoe"),
    BRAND("brandId"),
    SIZE("SizeId"),
    COLOUR("ColourId"),
    PRICE("Price"),
    STOCK("Stock");

    private final String shoes;

    ShoesColumns(String shoes) {
        this.shoes = shoes;
    }

    public String getValue() {
        return shoes;
    }
}
