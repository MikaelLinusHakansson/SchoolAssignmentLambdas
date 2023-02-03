package UdemyDatabase.DataBase.Repository;

public class Sizes {
    private int id;
    private int size;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Sizes(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public Sizes(){

    }
}
