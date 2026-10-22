package model;

public class Property {
    private final int id;
    private final String address;

    public Property(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}
