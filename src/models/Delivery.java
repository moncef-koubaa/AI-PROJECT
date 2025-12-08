package models;

import java.util.List;

public class Delivery {
    public Cell targetClient;
    public List<ActionEnum> path;

    public Delivery(Cell targetClient, List<ActionEnum> path) {
        this.targetClient = targetClient;
        this.path = path;
    }
}
