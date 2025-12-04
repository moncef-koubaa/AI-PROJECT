package models;

import java.util.List;

public class Grid {

    int length, width;
    List<List<Node>> nodes;
    List<Node> stores;
    List<Node> clients;

    // Getters and Setters
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public List<List<Node>> getNodes() {
        return nodes;
    }
    public void setNodes(List<List<Node>> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getStores() {
        return stores;
    }
    public void setStores(List<Node> stores) {
        this.stores = stores;
    }

    public List<Node> getClients() {
        return clients;
    }
    public void setClients(List<Node> clients) {
        this.clients = clients;
    }
}