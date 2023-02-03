package UdemyDatabase.DataBase.Repository;

public class CustomersNot {
    private int id;
    private String name;
    private String adress;
    private String region;
    private String SSA;
    private int zipCode;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomersNot(int id, String name, String adress, String region, String SSA, int Zipcode, String password) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.region = region;
        this.SSA = SSA;
        this.zipCode = Zipcode;
        this.password = password;
    }

    public CustomersNot() {

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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSSA() {
        return SSA;
    }

    public void setSSA(String SSA) {
        this.SSA = SSA;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
