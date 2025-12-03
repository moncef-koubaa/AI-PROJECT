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
}
