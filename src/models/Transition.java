package models;

public class Transition {

    public Cell nextCell;
    public int cost;
    public boolean isBlocked;

    //Getters and Setters
    public Cell getNextCell() {
        return nextCell;
    }

    public void setNextCell(Cell nextCell) {
        this.nextCell = nextCell;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}