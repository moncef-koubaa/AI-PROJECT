package models;

import java.util.Map;

public class Cell {

    public int row, col;
    Map<ActionEnum, Transition> actions;
    public CellTypeEnum type;

    public Transition getTransition(ActionEnum action) {
        return actions.get(action);
    }

    public boolean equals(Cell other) {
        return other.row == row && other.col == col;
    }

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
    public CellTypeEnum getType() {
        return type;
    }
    public void setType(CellTypeEnum type) {
        this.type = type;
    }
}
