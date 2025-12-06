package searchAlgorithms;

public class BreadthFirstSearch extends GeneralSearch {
    @Override
    protected java.util.Queue<models.Node> makeQueue(models.Node initNode) {
        java.util.Queue<models.Node> queue = new java.util.ArrayDeque<>();
        queue.add(initNode);
        return queue;
    }

    @Override
    protected void enqueue(java.util.Queue<models.Node> queue, models.Node node) {
        queue.add(node);
    }
}
