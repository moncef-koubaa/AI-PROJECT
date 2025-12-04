package services;

import java.util.*;
import models.*;

public class GridGenerator {

    private final Random random = new Random();

    public Grid generate() {

        Grid grid = new Grid();
        grid.setLength(randomInt(6, 10));
        grid.setWidth(randomInt(6, 10));

        grid.setNodes(new ArrayList<>());
        grid.setStores(new ArrayList<>());
        grid.setClients(new ArrayList<>());

        for (int r = 0; r < grid.getLength(); r++) {
            List<Node> row = new ArrayList<>();
            for (int c = 0; c < grid.getWidth(); c++) {
                Node n = new Node();
                n.setRow(r);
                n.setCol(c);
                n.setType(NodeTypeEnum.EMPTY);
                n.setActions(new EnumMap<>(ActionEnum.class));
                row.add(n);
            }
            grid.getNodes().add(row);
        }

        int storeCount = randomInt(1, 3);
        int clientCount = randomInt(1, 6);

        for (int i = 0; i < storeCount; i++) {
            Node n = randomEmptyNode(grid);
            n.setType(NodeTypeEnum.STORE);
            grid.getStores().add(n);
        }

        for (int i = 0; i < clientCount; i++) {
            Node n = randomEmptyNode(grid);
            n.setType(NodeTypeEnum.CLIENT);
            grid.getClients().add(n);
        }

        buildTransitions(grid);

        return grid;
    }

    private void buildTransitions(Grid grid) {
        for (int r = 0; r < grid.getLength(); r++) {
            for (int c = 0; c < grid.getWidth(); c++) {

                Node n = grid.getNodes().get(r).get(c);

                addTransition(grid, n, ActionEnum.UP, r - 1, c);
                addTransition(grid, n, ActionEnum.DOWN, r + 1, c);
                addTransition(grid, n, ActionEnum.LEFT, r, c - 1);
                addTransition(grid, n, ActionEnum.RIGHT, r, c + 1);
            }
        }
    }

    private void addTransition(Grid grid, Node from, ActionEnum action, int nr, int nc) {
        if (nr < 0 || nr >= grid.getLength()) return ;
        if (nc < 0 || nc >= grid.getWidth()) return;
        
        Transition t = new Transition();

        Node to = grid.getNodes().get(nr).get(nc);

        t.setNextNode(to);
        t.setCost(randomInt(1, 4));
        t.setBlocked(random.nextInt(10) < 2);

        if (!t.isBlocked()) {
            from.getActions().put(action, t);
        }
    }

    private Node randomEmptyNode(Grid grid) {
        while (true) {
            int r = random.nextInt(grid.getLength());
            int c = random.nextInt(grid.getWidth());
            Node n = grid.getNodes().get(r).get(c);
            if (n.getType() == NodeTypeEnum.EMPTY) return n;
        }
    }

    private int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }


    public String toString(Grid grid) {

        StringBuilder sb = new StringBuilder();

        sb.append(grid.getLength()).append(";")
        .append(grid.getWidth()).append(";")
        .append(grid.getClients().size()).append(";")
        .append(grid.getStores().size()).append(";");

        for (Node client : grid.getClients()) {
            sb.append(client.getRow()).append(",")
            .append(client.getCol()).append(";");
        }

        sb.append(";"); // empty tunnels section for now

        return sb.toString();
    }

}
