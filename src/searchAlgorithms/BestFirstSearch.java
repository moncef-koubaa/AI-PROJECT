package searchAlgorithms;

import models.Node;

import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends GeneralSearch {

    @Override
    protected Queue<Node> makeQueue(Node initNode) {
        return new PriorityQueue<Node>(initNode);

    }

    @Override
    protected void enqueue(Queue<Node> queue, Node node) {

    }
}
