package models;

public class State {

    Node gridNode;
    State parent;
    ActionEnum incomingAction; // Action taken to reach this state from parent
    int pathCost;
}
