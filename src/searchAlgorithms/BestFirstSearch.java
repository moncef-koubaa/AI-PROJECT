package searchAlgorithms;

import contracts.IEvaluate;
import models.Node;

import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends GeneralSearch {
    private final IEvaluate evaluator;

    public BestFirstSearch(IEvaluate evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    protected Queue<Node> makeQueue(Node initNode) {
        PriorityQueue<Node> queue = new PriorityQueue<>(
                (a, b) -> Double.compare(
                        evaluator.evaluate(a),
                        evaluator.evaluate(b)
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
