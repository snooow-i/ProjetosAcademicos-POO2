package poo2;

public class Oid {

    private String id;

    public Oid(String id) {
        this.id = id;
    }

    public String getString() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}