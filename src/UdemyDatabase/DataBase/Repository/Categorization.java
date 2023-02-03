package UdemyDatabase.DataBase.Repository;

public class Categorization {
    private int id;
    private int shoesId;
    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShoesId() {
        return shoesId;
    }

    public void setShoesId(int shoesId) {
        this.shoesId = shoesId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Categorization(int id, int shoesId, int categoryId) {
        this.id = id;
        this.shoesId = shoesId;
        this.categoryId = categoryId;
    }
}
