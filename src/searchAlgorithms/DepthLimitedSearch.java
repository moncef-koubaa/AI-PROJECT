package searchAlgorithms;

import models.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class DepthLimitedSearch extends GeneralSearch {
    private final int depthLimit;

    public DepthLimitedSearch(int depthLimit) {
        this.depthLimit = depthLimit;
    }

    @Override
    protected Queue<Node> makeQueue(Node initNode) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(initNode);
        return queue;
    }

    @Override
    protected void enqueue(Queue<Node> queue, Node node) {
        Deque<Node> stack = (Deque<Node>) queue;
        if (node.depth <= depthLimit) {
            stack.push(node);
        }
    }
}
