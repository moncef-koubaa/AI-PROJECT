package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grid {

    public int length, width;
    public List<List<Cell>> cells;
    public List<Cell> stores;
    public List<Cell> clients;
    public List<List<Cell>> tunnels;

    public Grid() {
        this.length = 0;
        this.width = 0;
        this.cells = new ArrayList<>();
        this.stores = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.tunnels = new ArrayList<>();
    }

    public Grid(int length, int width, List<List<Cell>> nodes, List<Cell> stores, List<Cell> clients) {
        this.length = length;
        this.width = width;
        this.cells = nodes;
        this.stores = stores;
        this.clients = clients;

    }

    public List<List<Cell>> getNodes() {
        return cells;
    }

    public Grid fromString(String gridString) {
        String[] parts = gridString.split(";");
        int width = Integer.parseInt(parts[0]);
        int length = Integer.parseInt(parts[1]);
        int numClient = Integer.parseInt(parts[2]);
        int numStore = Integer.parseInt(parts[3]);
        String CustomersPart = parts[4];
        String TunnelsPart = parts[5];
        String StoresPart = parts[6];

        String[] CustomerPositions = CustomersPart.split(",");
        String[] TunnelPositions = TunnelsPart.split(",");
        String[] StorePositions = StoresPart.split(",");
        CellTypeEnum[][] gridMap = new CellTypeEnum[length*10][width*10];


        for(int i = 0 ; i < numClient * 2 ; i += 2) {
            System.out.println("Client Position: " + CustomerPositions[i] + "," + CustomerPositions[i + 1]);
            System.out.println("number of client : " + numClient + "length of the array : " + CustomerPositions.length);
            int x = Integer.parseInt(CustomerPositions[i]);
            int y = Integer.parseInt(CustomerPositions[i + 1]);
            gridMap[x][y] = CellTypeEnum.CLIENT;
        }

        for(int i = 0 ; i < TunnelPositions.length ; i += 2) {

            int x = Integer.parseInt(TunnelPositions[i]);
            int y = Integer.parseInt(TunnelPositions[i + 1]);
            gridMap[x][y] = CellTypeEnum.TUNNEL;
        }

        for(int i = 0 ; i < numStore * 2 ; i += 2) {
            int x = Integer.parseInt(StorePositions[i]);
            int y = Integer.parseInt(StorePositions[i + 1]);
            gridMap[x][y] = CellTypeEnum.STORE;
        }

        Grid grid = new Grid();
        grid.setLength(length);
        grid.setWidth(width);
        grid.setCells(grid.generateEmptyNodeList(length, width));
        int index = 7;

        try{
            for(int i = 7 ; i < parts.length ; i++) {
                String traffic = parts[i];
                String[] trafficParts = traffic.split(",");
                int fromX = Integer.parseInt(trafficParts[0]);
                int fromY = Integer.parseInt(trafficParts[1]);
                int toX = Integer.parseInt(trafficParts[2]);
                int toY = Integer.parseInt(trafficParts[3]);
                int distance = Integer.parseInt(trafficParts[4]);

                Cell fromCell = grid.cells.get(fromX).get(fromY);
                fromCell.type = gridMap[fromX][fromY];

                Cell toCell = grid.cells.get(toX).get(toY);
                toCell.type = gridMap[toX][toY];

                if(fromCell.type == CellTypeEnum.STORE && !grid.stores.contains(fromCell)) {
                    grid.stores.add(fromCell);
                } else if(fromCell.type == CellTypeEnum.CLIENT && !grid.clients.contains(fromCell)) {
                    grid.clients.add(fromCell);
                }


                Transition transitionFrom = new Transition();
                transitionFrom.cost = distance;
                transitionFrom.isBlocked = (0 == distance);
                transitionFrom.nextCell = toCell;

                Transition transitionTo = new Transition();
                transitionTo.cost = distance;
                transitionTo.isBlocked = (0 == distance);
                transitionTo.nextCell = fromCell;

                if(fromCell.actions == null) {
                    fromCell.actions = new HashMap<ActionEnum, Transition>();
                }

                if(toCell.actions == null) {
                    toCell.actions = new HashMap<ActionEnum, Transition>();
                }

                if(toX == fromX + 1 && toY == fromY) {
                    fromCell.actions.put(ActionEnum.DOWN, transitionFrom);
                } else if(toX == fromX - 1 && toY == fromY) {
                    fromCell.actions.put(ActionEnum.UP, transitionFrom);
                } else if(toX == fromX && toY == fromY + 1) {
                    fromCell.actions.put(ActionEnum.RIGHT, transitionFrom);
                } else if(toX == fromX && toY == fromY - 1) {
                    fromCell.actions.put(ActionEnum.LEFT, transitionFrom);
                }
                index++;

            }
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println("Error parsing grid string: " + e.getMessage());
        }


        return grid;
    }
    private List<List<Cell>> generateEmptyNodeList(int length, int width) {

        List<List<Cell>> grid = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell();
                cell.row = i;
                cell.col = j;
                cell.type = CellTypeEnum.EMPTY;
                row.add(cell);
            }
            grid.add(row);
        }
        return grid;
    }

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
                    .append(client.getCol()).append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(";");

        for (List<Cell> tunnel : this.tunnels) {
            sb.append(tunnel.get(0).getRow()).append(",")
                    .append(tunnel.get(0).getCol()).append(",")
                    .append(tunnel.get(1).getRow()).append(",")
                    .append(tunnel.get(1).getCol()).append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(";");
        for (Cell store : this.stores) {
            sb.append(store.getRow()).append(",")
                    .append(store.getCol()).append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append(";");
        for (int r = 0; r < this.length; r++) {
            for (int c = 0; c < this.width; c++) {
                Cell cell = this.cells.get(r).get(c);
                for (ActionEnum action : cell.actions.keySet()) {
                    Transition transition = cell.actions.get(action);
                    if (transition != null) {
                        Cell nextCell = transition.nextCell;
                        sb.append(cell.getRow()).append(",")
                                .append(cell.getCol()).append(",")
                                .append(nextCell.getRow()).append(",")
                                .append(nextCell.getCol()).append(",")
                                .append(transition.cost).append(";");
                    }
                }
            }
        }


        return sb.toString();
    }
}


