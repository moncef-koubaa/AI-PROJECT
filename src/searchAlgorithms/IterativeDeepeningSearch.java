package searchAlgorithms;

import contracts.ISearch;
import models.Cell;
import models.Grid;
import models.SearchResult;

public class IterativeDeepeningSearch implements ISearch {
    private static final int MAX_DEPTH = 20000;

    @Override
    public String search(Grid grid, Cell startStore, Cell targetClient) {
        int expandedNodes = 0;
        for (int depth = 0; depth <= MAX_DEPTH; depth++) {
            DepthLimitedSearch dls = new DepthLimitedSearch(depth);
            SearchResult result = SearchResult.fromString(dls.search(grid, startStore, targetClient));
            if (result.success) {
                result.expandedNodes += expandedNodes;
                return result.toString();
            }
            expandedNodes += result.expandedNodes;
        }
        return ";;" + expandedNodes;
    }
}
