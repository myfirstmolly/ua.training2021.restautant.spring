package ua.training.restaurant.entities;

public enum Status {

    OPENED(0),
    PENDING(1),
    COOKING(2),
    DELIVERING(3),
    DONE(4);

    int id;

    Status(int id) {
        this.id = id;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public int getId() {
        return id;
    }

}
