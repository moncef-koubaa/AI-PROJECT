package models;

import java.util.Map;

public class Node {

    int row, col;
    Map<ActionEnum, Transition> actions;
    NodeTypeEnum type;
}
