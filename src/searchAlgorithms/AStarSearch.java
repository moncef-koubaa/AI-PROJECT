package searchAlgorithms;

import contracts.IEvaluate;
import java.util.PriorityQueue;
import java.util.Queue;
import models.Cell;
import models.Grid;
import models.Node;

public class AStarSearch extends GeneralSearch {

    private final IEvaluate heuristic;

    public AStarSearch(IEvaluate heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    protected Queue<Node> makeQueue(Node initNode, Grid grid, Cell goal) {
        PriorityQueue<Node> queue = new PriorityQueue<>(
                (a, b) -> Double.compare(
                        f(a, grid, goal),
                        f(b, grid, goal)
                )
        );
        queue.add(initNode);
        return queue;
    }

    @Override
    protected void enqueue(Queue<Node> queue, Node node) {
        queue.add(node);
    }

    private double f(Node node, Grid grid, Cell goal) {
        return (double)node.pathCost + heuristic.evaluate(node, grid, goal);
    }
}
