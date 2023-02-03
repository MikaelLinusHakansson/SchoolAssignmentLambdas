package UdemyDatabase.DataBase.Repository.Enums;

public enum SizesColumns {
    TABLE_SIZES("Sizes"),
    ID("Id"),
    SIZE("Size");
    private final String sizes;
    SizesColumns(String sizes) {
        this.sizes = sizes;
    }

    public String getValue() {
        return sizes;
    }

}
