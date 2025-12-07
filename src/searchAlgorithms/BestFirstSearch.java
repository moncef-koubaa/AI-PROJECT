package searchAlgorithms;

import contracts.IEvaluate;
import models.Cell;
import models.Grid;
import models.Node;

import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends GeneralSearch {
    private final IEvaluate evaluator;

    public BestFirstSearch(IEvaluate evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    protected Queue<Node> makeQueue(Node initNode, Grid grid, Cell goal) {
        PriorityQueue<Node> queue = new PriorityQueue<>(
                (a, b) -> Double.compare(
                        evaluator.evaluate(a, grid, goal),
                        evaluator.evaluate(b, grid, goal)
                )
        );
        queue.add(initNode);
        return queue;
    }

    @Override
    protected void enqueue(Queue<Node> queue, Node node) {
        queue.add(node);
    }
}
