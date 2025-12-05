package models;

import java.util.List;

public class Grid {

    int length, width;
    List<List<Cell>> cells;
    List<Cell> stores;
    List<Cell> clients;
    List<List<Cell>> tunnels;

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }
    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public List<Cell> getStores() {
        return stores;
    }
    public void setStores(List<Cell> stores) {
        this.stores = stores;
    }

    public List<Cell> getClients() {
        return clients;
    }
    public void setClients(List<Cell> clients) {
        this.clients = clients;
    }
    public List<List<Cell>> getTunnels() {
        return tunnels;
    }
    public void setTunnels(List<List<Cell>> tunnels) {
        this.tunnels = tunnels;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.length).append(";")
        .append(this.width).append(";")
        .append(this.clients.size()).append(";")
        .append(this.stores.size()).append(";");

        for (Cell client : this.clients) {
            sb.append(client.getRow()).append(",")
            .append(client.getCol()).append(";");
        }

        for (List<Cell> tunnel : this.tunnels) {
            sb.append(tunnel.get(0).getRow()).append(",")
            .append(tunnel.get(0).getCol()).append(",")
            .append(tunnel.get(1).getRow()).append(",")
            .append(tunnel.get(1).getCol()).append(";");
        }

        return sb.toString();
    }
}