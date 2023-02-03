package UdemyDatabase.DataBase.Repository.Enums;

public enum ColoursColumns {
    TABLE_COLOURS("Colours"),
    ID("Id"),
    NAME("NameOfColour");

    private String colours;

    ColoursColumns(String colours) {
        this.colours = colours;
    }

    public String getValue() {
        return colours;
    }
}
