package UdemyDatabase.DataBase.Repository.Enums;

public enum printFilterChoices {
    BRAND("Brand"),
    COLOUR("Colour"),
    SIZE("Size");
    private String searchForEnum;
    printFilterChoices(String values) {
        searchForEnum = values;
    }

    public String getSearchForEnum() {
        return searchForEnum;
    }
}
