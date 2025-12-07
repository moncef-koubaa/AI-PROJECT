package services;

import java.util.*;
import models.*;

public class GridGenerator {

    private final Random random = new Random();

    public Grid generate() {

        Grid grid = new Grid();
        grid.setLength(randomInt(6, 10));
//        grid.setLength(1000);
        grid.setWidth(randomInt(6, 10));
//        grid.setWidth(1000);

        grid.setCells(new ArrayList<>());
        grid.setStores(new ArrayList<>());
        grid.setClients(new ArrayList<>());
        grid.setTunnels(new ArrayList<>());

        for (int r = 0; r < grid.getLength(); r++) {
            List<Cell> row = new ArrayList<>();
            for (int c = 0; c < grid.getWidth(); c++) {
                Cell n = new Cell();
                n.setRow(r);
                n.setCol(c);
                n.setType(CellTypeEnum.EMPTY);
                n.setActions(new EnumMap<>(ActionEnum.class));
                row.add(n);
            }
            grid.getCells().add(row);
        }

        int storeCount = randomInt(1, 3);
        int clientCount = randomInt(1, 6);
        int tunnelsCount = randomInt(1, 4);

        for (int i = 0; i < storeCount; i++) {
            Cell n = randomEmptyNode(grid);
            n.setType(CellTypeEnum.STORE);
            grid.getStores().add(n);
        }

        for (int i = 0; i < clientCount; i++) {
            Cell n = randomEmptyNode(grid);
            n.setType(CellTypeEnum.CLIENT);
            grid.getClients().add(n);
        }

        buildTransitions(grid);
        buildTunnels(grid, tunnelsCount);

        return grid;
    }

    private void buildTransitions(Grid grid) {
        for (int r = 0; r < grid.getLength(); r++) {
            for (int c = 0; c < grid.getWidth(); c++) {

                Cell n = grid.getCells().get(r).get(c);

                addTransition(grid, n, ActionEnum.UP, r - 1, c);
                addTransition(grid, n, ActionEnum.DOWN, r + 1, c);
                addTransition(grid, n, ActionEnum.LEFT, r, c - 1);
                addTransition(grid, n, ActionEnum.RIGHT, r, c + 1);
            }
        }
    }

    private void addTransition(Grid grid, Cell from, ActionEnum action, int nr, int nc) {
        if (nr < 0 || nr >= grid.getLength()) return ;
        if (nc < 0 || nc >= grid.getWidth()) return;
        
        Transition t = new Transition();

        Cell to = grid.getCells().get(nr).get(nc);

        t.setNextCell(to);
        t.setCost(randomInt(1, 4));
        t.setBlocked(random.nextInt(10) < 2);

        if (!t.isBlocked()) {
            from.getActions().put(action, t);
        }
    }

    private void buildTunnels(Grid grid, int tunnelsCount) {
        for (int i = 0; i < tunnelsCount; i++) {
            Cell a = randomEmptyNode(grid);
            Cell b;
            do {
                b = randomEmptyNode(grid);
            } while (a == b);
            a.setType(CellTypeEnum.TUNNEL);
            b.setType(CellTypeEnum.TUNNEL);
            
            int cost = Math.abs(a.getRow() - b.getRow()) + Math.abs(a.getCol() - b.getCol());

            Transition tAB = new Transition();
            tAB.setNextCell(b);
            tAB.setCost(cost);
            tAB.setBlocked(false);

            Transition tBA = new Transition();
            tBA.setNextCell(a);
            tBA.setCost(cost); 
            tBA.setBlocked(false);

            a.getActions().put(ActionEnum.ENTER_TUNNEL, tAB);
            b.getActions().put(ActionEnum.ENTER_TUNNEL, tBA);
        
            List<Cell> tunnelPair = new ArrayList<>();
            tunnelPair.add(a);
            tunnelPair.add(b);
            grid.getTunnels().add(tunnelPair);
        }
    }

    private Cell randomEmptyNode(Grid grid) {
        while (true) {
            int r = random.nextInt(grid.getLength() - 1);
            int c = random.nextInt(grid.getWidth() - 1);
            Cell n = grid.getCells().get(r).get(c);
            if (n.getType() == CellTypeEnum.EMPTY) return n;
        }
    }

    private int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }


    

}
