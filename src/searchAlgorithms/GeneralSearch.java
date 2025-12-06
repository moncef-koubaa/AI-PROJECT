package searchAlgorithms;

import contracts.ISearch;
import models.*;

import java.util.*;

abstract public class GeneralSearch implements ISearch {

    private boolean reachedTarget(Node node, Cell target) {
        return target.equals(node.cell);
    }

    private Node next(Node oldNode, ActionEnum action) {
        Transition transition =  oldNode.cell.getTransition(action);
        return new Node(
                transition.nextCell,
                oldNode,
                action,
                oldNode.pathCost + transition.cost,
                oldNode.depth + 1
        );
    }

    private List<ActionEnum> actionSequence(Node leafNode) {
        List<ActionEnum> actions = new ArrayList<>();
        Node currentNode = leafNode;
        while (currentNode.parent != null) {
            actions.addFirst(currentNode.incomingAction);
            currentNode = currentNode.parent;
        }
        return actions;
    }

    private String success(Node targetNode, int expandedNodes) {
        List<ActionEnum> actions = actionSequence(targetNode);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < actions.size(); i++) {
            result.append(actions.get(i));
            if (i < actions.size() - 1) {
                result.append(",");
            }
        }
        result.append(";");
        result.append(targetNode.pathCost);
        result.append(";");
        result.append(expandedNodes);
        return result.toString();
    }

    private String fail(int expandedNodes) {
        return ";;" + expandedNodes;
    }

    private Node makeNode(Cell cell) {
        return new Node(cell, null, null, 0, 0);
    }

    @Override
    public String search(Grid grid, Cell startStore, Cell targetClient) {
        Queue<Node> searchQueue = makeQueue(makeNode(startStore));
        Set<Cell> visited = new HashSet<>();
        visited.add(startStore);
        int expandedNodes = 0;

        while (!searchQueue.isEmpty()) {
            Node currentNode = searchQueue.poll();
            expandedNodes++;

            if (reachedTarget(currentNode, targetClient)) {
                return success(currentNode, expandedNodes);
            }

            for (ActionEnum action : ActionEnum.values()) {
                Transition trans = currentNode.cell.getTransition(action);
                if (trans == null || trans.isBlocked) continue;
                Cell nextCell = trans.nextCell;
                if (!visited.contains(nextCell)) {
                    visited.add(nextCell);
                    enqueue(searchQueue, next(currentNode, action));
                }
            }
        }
        return fail(expandedNodes);
    }

    abstract protected Queue<Node> makeQueue(Node initNode);
    abstract protected void enqueue(Queue<Node> queue, Node node);
}
