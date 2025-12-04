package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grid {

    int length, width;
    List<List<Cell>> nodes;
    List<Cell> stores;
    List<Cell> clients;

    public Grid() {
    }

    public Grid(int length, int width, List<List<Cell>> nodes, List<Cell> stores, List<Cell> clients) {
        this.length = length;
        this.width = width;
        this.nodes = nodes;
        this.stores = stores;
        this.clients = clients;

    }

    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
    }
    public List<List<Cell>> getNodes() {
        return nodes;
    }
    public List<Cell> getStores() {
        return stores;
    }
    public List<Cell> getClients() {
        return clients;
    }

    public static Grid fromString(String gridString) {
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
        CellTypeEnum[][] gridMap = new CellTypeEnum[length][width];


        for(int i = 0 ; i < numClient * 2 ; i += 2) {
            int x = Integer.parseInt(CustomerPositions[i]);
            int y = Integer.parseInt(CustomerPositions[i + 1]);
            gridMap[x][y] = CellTypeEnum.CLIENT;
        }

        for(int i = 0 ; i < TunnelPositions.length * 2 ; i += 2) {
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
        grid.length = length;
        grid.width = width;
        grid.nodes = grid.generateEmptyNodeList(length, width);
        grid.stores = new ArrayList<>();
        grid.clients = new ArrayList<>();

        for(int i = 7 ; i < parts.length ; i++) {
            String traffic = parts[i];
            String[] trafficParts = traffic.split(",");
            int fromX = Integer.parseInt(trafficParts[0]);
            int fromY = Integer.parseInt(trafficParts[1]);
            int toX = Integer.parseInt(trafficParts[2]);
            int toY = Integer.parseInt(trafficParts[3]);
            int distance = Integer.parseInt(trafficParts[4]);

            Cell fromCell = grid.nodes.get(fromX).get(fromY);
            fromCell.type = gridMap[fromX][fromY];

            Cell toCell = grid.nodes.get(toX).get(toY);
            toCell.type = gridMap[toX][toY];

            if(fromCell.type == CellTypeEnum.STORE) {
                grid.stores.add(fromCell);
            } else if(fromCell.type == CellTypeEnum.CLIENT) {
                grid.clients.add(fromCell);
            }

            if(toCell.type == CellTypeEnum.STORE) {
                grid.stores.add(toCell);
            } else if(toCell.type == CellTypeEnum.CLIENT) {
                grid.clients.add(toCell);
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
                toCell.actions.put(ActionEnum.UP, transitionTo);
            } else if(toX == fromX - 1 && toY == fromY) {
                fromCell.actions.put(ActionEnum.UP, transitionFrom);
                toCell.actions.put(ActionEnum.DOWN, transitionTo);
            } else if(toX == fromX && toY == fromY + 1) {
                fromCell.actions.put(ActionEnum.RIGHT, transitionFrom);
                toCell.actions.put(ActionEnum.LEFT, transitionTo);
            } else if(toX == fromX && toY == fromY - 1) {
                fromCell.actions.put(ActionEnum.LEFT, transitionFrom);
                toCell.actions.put(ActionEnum.RIGHT, transitionTo);
            }


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

}

