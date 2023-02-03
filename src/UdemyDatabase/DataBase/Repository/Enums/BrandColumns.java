package UdemyDatabase.DataBase.Repository.Enums;

public enum BrandColumns {
    TABLE_BRAND("Brand"),
    ID("Id"),
    NAME("NameOfBrand");

    private String brand;
    BrandColumns(String brand) {
        this.brand = brand;
    }

    public String getValue() {
        return brand;
    }
}
