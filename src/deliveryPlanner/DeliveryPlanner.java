package deliveryPlanner;

import contracts.ISearch;
import models.*;
import searchAlgorithms.GeneralSearch;
import searchAlgorithms.StrategyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryPlanner {
    public void plan(String initialState, String strategy, Boolean visualize) {
        ISearch searchAlgorithm = StrategyFactory.getStrategy(strategy);
        Grid grid = new Grid().fromString(initialState);
        if(grid == null){
            System.out.println("Invalid grid");
            return;
        }
        StringBuilder results = new StringBuilder();
        for(Cell client : grid.getClients()){

            int bestCost = Integer.MAX_VALUE;
            Cell bestStore = null;
            String result = "";
            String path = "";

            for(Cell store : grid.getStores()){
                result = searchAlgorithm.search(grid, store, client);
                String[] resultParts = result.split(";");
                SearchResult searchResult= SearchResult.fromString(result);
                if(searchResult.success) {
                    if (bestCost > Integer.parseInt(resultParts[1])) {
                        bestCost = Integer.parseInt(resultParts[1]);
                        bestStore = store;
                        path = resultParts[0];
                    }
                }
            }
            if (bestStore == null) {
                results.append("NO_PATH").append("\n");
                continue;
            }

            results.append(bestStore.row).append(",").append(bestStore.col).append(",")
                    .append(client.row).append(",").append(client.col).append(",")
                    .append(path).append("\n");

        }

        if(visualize){
            Visualize(results.toString(),grid);
        }
        System.out.println(results.toString());

    }


    public void Visualize(String results, Grid grid) {
        if (results == null || results.isEmpty() || grid == null) {
            System.out.println("No results to visualize");
            return;
        }

        String[] deliveries = results.trim().split("\n");

        Map<String, List<Delivery>> storeDeliveries = new HashMap<>();

        for (String delivery : deliveries) {
            if (delivery.equals("NO_PATH")) {
                System.out.println("Warning: Some clients cannot be reached");
                continue;
            }

            String[] parts = delivery.split(",");
            if (parts.length < 5) continue;

            int storeRow = Integer.parseInt(parts[0]);
            int storeCol = Integer.parseInt(parts[1]);
            int clientRow = Integer.parseInt(parts[2]);
            int clientCol = Integer.parseInt(parts[3]);

            String storeKey = storeRow + "," + storeCol;
            Cell store = grid.getCells().get(storeRow).get(storeCol);
            Cell client = grid.getCells().get(clientRow).get(clientCol);

            List<ActionEnum> actions = new ArrayList<>();
            if (parts.length > 4 && !parts[4].isEmpty()) {
                String[] actionStrs = parts[4].split(",");
                for (String actionStr : actionStrs) {
                    actions.add(ActionEnum.valueOf(actionStr.trim()));
                }
            }

            Delivery del = new Delivery(client, actions);
            storeDeliveries.computeIfAbsent(storeKey, k -> new ArrayList<>()).add(del);
        }

        List<Truck> trucks = new ArrayList<>();
        int truckId = 1;
        for (Map.Entry<String, List<Delivery>> entry : storeDeliveries.entrySet()) {
            String[] coords = entry.getKey().split(",");
            int row = Integer.parseInt(coords[0]);
            int col = Integer.parseInt(coords[1]);
            Cell store = grid.getCells().get(row).get(col);

            Truck truck = new Truck(truckId++, store, entry.getValue());
            trucks.add(truck);
        }

        if (trucks.isEmpty()) {
            System.out.println("No trucks to visualize");
            return;
        }

        System.out.println("\n========== DELIVERY VISUALIZATION ==========");
        System.out.println("Stores: " + trucks.size());
        System.out.println("Total Deliveries: " + deliveries.length);
        System.out.println("===========================================\n");

        System.out.println("Step 0: (Trucks at stores, ready to depart)");
        System.out.println("-------------------------------------------");
        printGridWithTrucks(grid, trucks);
        printTruckStatus(trucks, 0);
        System.out.println("\n");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        boolean anyTruckMoving = true;
        int step = 1;

        while (anyTruckMoving) {
            System.out.println("Step " + step + ":");
            System.out.println("-------------------------------------------");

            anyTruckMoving = false;
            for (Truck truck : trucks) {
                if (truck.moveNext()) {
                    anyTruckMoving = true;
                }
            }

            printGridWithTrucks(grid, trucks);
            printTruckStatus(trucks, step);

            System.out.println("\n");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            step++;

            if (step > 1000) {
                System.out.println("Visualization limit reached");
                break;
            }
        }

        System.out.println("========== ALL DELIVERIES COMPLETE ==========\n");
    }

    private void printGridWithTrucks(Grid grid, List<Truck> trucks) {
        int length = grid.getLength();
        int width = grid.getWidth();

        Map<String, List<Integer>> truckPositions = new HashMap<>();
        for (Truck truck : trucks) {
            if (!truck.isAtStore()) {
                String key = truck.currentPos.row + "," + truck.currentPos.col;
                truckPositions.computeIfAbsent(key, k -> new ArrayList<>()).add(truck.id);
            }
        }

        System.out.print("  ");
        for (int j = 0; j < width; j++) {
            System.out.print(" " + j + " ");
        }
        System.out.println();

        System.out.print("  +");
        for (int j = 0; j < width; j++) {
            System.out.print("---+");
        }
        System.out.println();

        for (int i = 0; i < length; i++) {
            System.out.print(i + " |");

            for (int j = 0; j < width; j++) {
                Cell cell = grid.getCells().get(i).get(j);
                String key = i + "," + j;

                String cellChar;

                if (truckPositions.containsKey(key)) {
                    List<Integer> truckIds = truckPositions.get(key);
                    if (truckIds.size() == 1) {
                        cellChar = String.valueOf(truckIds.get(0));
                    } else {
                        cellChar = "*";
                    }
                } else {
                    cellChar = getCellSymbol(cell);
                }

                System.out.print(" " + cellChar + " ");

                if (j < width - 1) {
                    if (cell.getTransition(ActionEnum.RIGHT) != null) {
                        System.out.print(cell.getTransition(ActionEnum.RIGHT).isBlocked ? "X" : "-");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print("|");
                }
            }
            System.out.println();

            if (i < length - 1) {
                System.out.print("  |");
                for (int j = 0; j < width; j++) {
                    Cell cell = grid.getCells().get(i).get(j);

                    if (cell.getTransition(ActionEnum.DOWN) != null) {
                        System.out.print(cell.getTransition(ActionEnum.DOWN).isBlocked ? " X " : " | ");
                    } else {
                        System.out.print("   ");
                    }

                    System.out.print(j < width - 1 ? " " : "|");
                }
                System.out.println();
            }
        }

        System.out.print("  +");
        for (int j = 0; j < width; j++) {
            System.out.print("---+");
        }
        System.out.println();
        System.out.println("Legend: S=Store, C=Client, T=Tunnel, .=Empty, Numbers=Trucks, *=Multiple Trucks");
    }

    private void printTruckStatus(List<Truck> trucks, int step) {
        System.out.println("\nTruck Status:");
        for (Truck truck : trucks) {
            String status = truck.getStatus();
            System.out.println("  Truck " + truck.id + " [Store " +
                    truck.home.row + "," + truck.home.col + "]: " + status);
        }
    }

    private String getCellSymbol(Cell cell) {
        if (cell == null || cell.type == null) return "?";

        switch (cell.type) {
            case STORE: return "S";
            case CLIENT: return "C";
            case TUNNEL: return "T";
            case EMPTY: return ".";
            default: return "?";
        }
    }



    public void PrintGrid(Grid grid) {
        if (grid == null || grid.getCells() == null) {
            System.out.println("Grid is null or empty");
            return;
        }

        int length = grid.getLength();
        int width = grid.getWidth();

        System.out.println("Grid " + length + "x" + width);
        System.out.println("Stores: " + grid.getStores().size() +
                ", Clients: " + grid.getClients().size() +
                ", Tunnels: " + grid.getTunnels().size());
        System.out.println();

        // Print top border
        System.out.print("  ");
        for (int j = 0; j < width; j++) {
            System.out.print(" " + j + " ");
        }
        System.out.println();
        System.out.print("  +");
        for (int j = 0; j < width; j++) {
            System.out.print("---+");
        }
        System.out.println();

        // Print grid with connections
        for (int i = 0; i < length; i++) {
            // Print row number and left border
            System.out.print(i + " |");

            // Print cells and horizontal connections
            for (int j = 0; j < width; j++) {
                Cell cell = grid.getCells().get(i).get(j);

                // Print cell content
                String cellChar = getCellSymbol(cell);
                System.out.print(" " + cellChar + " ");

                // Print right connection or border
                if (j < width - 1) {
                    if (cell.getTransition(ActionEnum.RIGHT) != null) {
                        if (cell.getTransition(ActionEnum.RIGHT).isBlocked) {
                            System.out.print("X");
                        } else {
                            System.out.print("-");
                        }
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print("|");
                }
            }
            System.out.println();

            // Print vertical connections row
            if (i < length - 1) {
                System.out.print("  |");
                for (int j = 0; j < width; j++) {
                    Cell cell = grid.getCells().get(i).get(j);

                    if (cell.getTransition(ActionEnum.DOWN) != null) {
                        if (cell.getTransition(ActionEnum.DOWN).isBlocked) {
                            System.out.print(" X ");
                        } else {
                            System.out.print(" | ");
                        }
                    } else {
                        System.out.print("   ");
                    }

                    if (j < width - 1) {
                        System.out.print(" ");
                    } else {
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
        }

        // Print bottom border
        System.out.print("  +");
        for (int j = 0; j < width; j++) {
            System.out.print("---+");
        }
        System.out.println();

        // Print legend
        System.out.println("\nLegend: S=Store, C=Client, T=Tunnel, .=Empty, X=Blocked, -/|=Connected");
    }


}
