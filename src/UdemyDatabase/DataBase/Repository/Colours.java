package UdemyDatabase.DataBase.Repository;

public class Colours {
    private int id;
    private String name;

    public Colours(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Colours(){

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
}
