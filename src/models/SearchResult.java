package models;

import java.util.List;
import java.util.stream.Stream;

public class SearchResult {
    public boolean success;
    public int cost;
    public int expandedNodes;
    public List<ActionEnum> actionSequence;

    public String toString() {
        StringBuilder result = new StringBuilder();
        if (success) {
            for (int i = 0; i < actionSequence.size(); i++) {
                result.append(actionSequence.get(i));
                if (i < actionSequence.size() - 1) {
                    result.append(",");
                }
            }
        }
        result.append(";");
        result.append(cost);
        result.append(";");
        result.append(expandedNodes);
        return result.toString();
    }

    public static SearchResult fromString(String resultString) {
        String[] parts = resultString.split(";");
        SearchResult result = new SearchResult();
        result.expandedNodes = Integer.parseInt(parts[2]);

        if (parts[0].isEmpty()) {
            result.success = false;
            result.cost = 0;
            result.actionSequence = List.of();
        } else {
            result.success = true;
            String[] actions = parts[0].split(",");
            result.actionSequence = Stream.of(actions)
                    .map(ActionEnum::valueOf)
                    .toList();
            result.cost = Integer.parseInt(parts[1]);
        }

        return result;
    }
}
