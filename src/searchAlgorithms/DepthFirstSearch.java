package searchAlgorithms;

import models.Node;

import java.util.ArrayDeque;
import java.util.Queue;

public class DepthFirstSearch extends GeneralSearch {
    @Override
    protected java.util.Queue<models.Node> makeQueue(models.Node initNode) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(initNode);
        return queue;
    }

    @Override
    protected void enqueue(java.util.Queue<models.Node> queue, models.Node node) {
        java.util.Deque<models.Node> stack = (java.util.Deque<models.Node>) queue;
        stack.push(node);
    }
}
