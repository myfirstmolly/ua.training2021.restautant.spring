package ua.training.restaurant.entities;

import java.util.Arrays;
import java.util.List;

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

    public static List<Status> getSublist(int from) {
        return Arrays.asList(Status.values()).subList(from, Status.values().length);
    }

}
