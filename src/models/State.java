package models;

public class State {

    Node gridNode;
    State parent;
    ActionEnum incomingAction; // Action taken to reach this state from parent
    int pathCost;

    // Getters and Setters
    public Node getGridNode() {
        return gridNode;
    }
    public void setGridNode(Node gridNode) {
        this.gridNode = gridNode;
    }
    public State getParent() {
        return parent;
    }
    public void setParent(State parent) {
        this.parent = parent;
    }
    public ActionEnum getIncomingAction() {
        return incomingAction;
    }
    public void setIncomingAction(ActionEnum incomingAction) {
        this.incomingAction = incomingAction;
    }
    public int getPathCost() {
        return pathCost;
    }
    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }
}
