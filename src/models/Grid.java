package models;

import java.util.List;

public class Grid {

    int length, width;
    List<List<Cell>> nodes;
    List<Cell> stores;
    List<Cell> clients;

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
    public List<List<Node>> getTunnels() {
        return tunnels;
    }
    public void setTunnels(List<List<Node>> tunnels) {
        this.tunnels = tunnels;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.length).append(";")
        .append(this.width).append(";")
        .append(this.clients.size()).append(";")
        .append(this.stores.size()).append(";");

        for (Node client : this.clients) {
            sb.append(client.getRow()).append(",")
            .append(client.getCol()).append(";");
        }

        for (List<Node> tunnel : this.tunnels) {
            sb.append(tunnel.get(0).getRow()).append(",")
            .append(tunnel.get(0).getCol()).append(",")
            .append(tunnel.get(1).getRow()).append(",")
            .append(tunnel.get(1).getCol()).append(";");
        }

        return sb.toString();
    }
}