package searchAlgorithms;

public class DepthFirstSearch extends GeneralSearch {
    @Override
    protected java.util.Queue<models.Node> makeQueue(models.Node initNode) {
        java.util.Deque<models.Node> stack = new java.util.ArrayDeque<>();
        stack.push(initNode);
        return stack;
    }

    @Override
    protected void enqueue(java.util.Queue<models.Node> queue, models.Node node) {
        java.util.Deque<models.Node> stack = (java.util.Deque<models.Node>) queue;
        stack.push(node);
    }
}
