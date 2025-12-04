package models;

import java.util.Map;

public class Node {

    int row, col;
    Map<ActionEnum, Transition> actions;
    NodeTypeEnum type;

    // Getters and Setters
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public Map<ActionEnum, Transition> getActions() {
        return actions;
    }
    public void setActions(Map<ActionEnum, Transition> actions) {
        this.actions = actions;
    }
    public NodeTypeEnum getType() {
        return type;
    }
    public void setType(NodeTypeEnum type) {
        this.type = type;
    }
}