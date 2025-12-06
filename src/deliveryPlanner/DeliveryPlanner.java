package deliveryPlanner;

import contracts.ISearch;
import models.ActionEnum;
import models.Cell;
import models.Grid;
import searchAlgorithms.GeneralSearch;
import searchAlgorithms.StrategyFactory;

public class DeliveryPlanner {
    void plan(String initialState, String strategy, Boolean visualize) {
        ISearch searchAlgorithm = StrategyFactory.getStrategy(strategy);
        Grid grid = new Grid().fromString(initialState);
        StringBuilder results = new StringBuilder();
        for(Cell client : grid.getClients()){

            int bestCost = Integer.MAX_VALUE;
            Cell bestStore = null;
            String result = "";
            String path = "";

            for(Cell store : grid.getStores()){
                result = searchAlgorithm.search(grid, store, client);
                String[] resultParts = result.split(";");
                if(bestCost > Integer.parseInt(resultParts[1])){
                    bestCost = Integer.parseInt(resultParts[1]);
                    bestStore = store;
                    path = resultParts[0];
                }

            }

            results.append(bestStore.row).append(",").append(bestStore.col).append(",")
                    .append(client.row).append(",").append(client.col).append(",")
                    .append(path).append("\n");

        }
        if(visualize){
            System.out.println("Visualization is not implemented yet.");
        }
        System.out.println(results.toString());

    }

    void Visualize(String results, Grid grid) {
        String[] deliveries = results.split("\n");


    }

    void PrintGrid(Grid grid) {
        StringBuilder[] visualization = new StringBuilder[grid.getLength() * 2];
        for (int i = 0; i < grid.getLength() * 2 ; i+=2) {
            for (int j = 0; j < grid.getWidth(); j++) {
                Cell cell = grid.getNodes().get(i/2).get(j);
                if (visualization[i] == null) {
                    visualization[i] = new StringBuilder();
                }
                switch (cell.type) {
                    case EMPTY -> visualization[i].append(".");
                    case TUNNEL -> visualization[i].append("T");
                    case STORE -> visualization[i].append("S");
                    case CLIENT -> visualization[i].append("C");
                }

                if(cell.getTransition(ActionEnum.RIGHT) != null){
                    if(cell.getTransition(ActionEnum.RIGHT).isBlocked )
                        visualization[i].append("|");
                    else
                        visualization[i].append("-");
                }


                if(cell.getTransition(ActionEnum.DOWN) != null){
                    if (visualization[i + 1] == null) {
                        visualization[i + 1] = new StringBuilder();
                    }

                    if(cell.getTransition(ActionEnum.DOWN).isBlocked ){

                        visualization[i + 1].append("- ");
                    }
                    else{

                        visualization[i + 1].append("| ");
                    }
                }

            }
            System.out.println(visualization[i].toString() + "\n" + (visualization[i + 1] != null ? visualization[i + 1].toString() + "\n" : ""));
        }
    }
}
