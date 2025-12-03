package heuristics;

import contracts.IEvaluate;
import models.Cell;
import models.Node;

public class ManhattanHeuristic implements IEvaluate {
    private final Cell goal;

    public ManhattanHeuristic(Cell goal) {
        this.goal = goal;
    }

    @Override
    public Double evaluate(Node node) {
        return (double) (Math.abs(goal.row - node.cell.row) + Math.abs(goal.col - node.cell.col));
    }
}
