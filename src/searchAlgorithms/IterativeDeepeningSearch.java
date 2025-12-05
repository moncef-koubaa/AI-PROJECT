package searchAlgorithms;

import contracts.ISearch;
import models.Cell;
import models.Grid;

public class IterativeDeepeningSearch implements ISearch {
    private static final int MAX_DEPTH = 1000;

    @Override
    public String search(Grid grid, Cell startStore, Cell targetClient) {
        for (int depth = 0; depth <= MAX_DEPTH; depth++) {
            DepthLimitedSearch dls = new DepthLimitedSearch(depth);
            // needs converter from string to search state
            String result = dls.search(grid, startStore, targetClient);
            if (result.charAt(0) != ';') {
                return result;
            }
        }
        return "fail";
    }
}
