package searchAlgorithms;

import contracts.IEvaluate;
import contracts.ISearch;
import models.Cell;
import models.Grid;

public class GreedySearch implements ISearch {
    private final BestFirstSearch bestFirstSearch;

    public GreedySearch(IEvaluate heuristic) {
        this.bestFirstSearch = new BestFirstSearch(heuristic);
    }

    @Override
    public String search(Grid grid, Cell startStore, Cell targetClient) {
        return bestFirstSearch.search(grid, startStore, targetClient);
    }
}
