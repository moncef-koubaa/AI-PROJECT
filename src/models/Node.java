package models;

public class Node {

    public Cell cell;
    public Node parent;
    public ActionEnum incomingAction; // Action taken to reach this state from parent
    public int pathCost;
    public int depth;

    public Node(Cell cell, Node parent, ActionEnum incomingAction, int pathCost, int depth) {
        this.cell = cell;
        this.parent = parent;
        this.incomingAction = incomingAction;
        this.pathCost = pathCost;
        this.depth = depth;
    }
}
