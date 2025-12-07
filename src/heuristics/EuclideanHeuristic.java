package heuristics;

import contracts.IEvaluate;
import models.Cell;
import models.Grid;
import models.Node;

public class EuclideanHeuristic implements IEvaluate {

    @Override
    public Double evaluate(Node node, Grid grid, Cell goal) {
        return Math.sqrt(Math.pow(goal.row - node.cell.row, 2) +
                Math.pow(goal.col - node.cell.col, 2));
    }
}
