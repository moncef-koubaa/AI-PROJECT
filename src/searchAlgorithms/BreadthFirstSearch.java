package searchAlgorithms;

import models.*;

public class BreadthFirstSearch extends GeneralSearch {
    @Override
    protected java.util.Queue<Node> makeQueue(Node initNode, Grid grid, Cell cell) {
        java.util.Queue<Node> queue = new java.util.ArrayDeque<>();
        queue.add(initNode);
        return queue;
    }

    @Override
    protected void enqueue(java.util.Queue<Node> queue, Node node) {
        queue.add(node);
    }
}
