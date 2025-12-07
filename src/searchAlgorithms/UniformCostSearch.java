package searchAlgorithms;

import java.util.PriorityQueue;
import java.util.Queue;
import models.Cell;
import models.Grid;
import models.Node;

public class UniformCostSearch extends GeneralSearch {

    @Override
    protected Queue<Node> makeQueue(Node initNode, Grid grid, Cell target) {
        PriorityQueue<Node> queue = new PriorityQueue<>(
                (a, b) -> Double.compare(a.pathCost, b.pathCost)
        );
        queue.add(initNode);
        return queue;
    }

    @Override
    protected void enqueue(Queue<Node> queue, Node node) {
        queue.add(node);
    }
}
