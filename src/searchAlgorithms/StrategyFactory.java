package searchAlgorithms;

import contracts.ISearch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class StrategyFactory {
    private static final Map<String,  ISearch> REGISTRY = new ConcurrentHashMap<>();
    static {
        REGISTRY.put("BF" ,  new BreadthFirstSearch());
        REGISTRY.put("DF" ,  new DepthFirstSearch());
        REGISTRY.put("ID" , new IterativeDeepeningSearch());
        // REGISTRY.put("UC" ,  new UniformCostSearch());
        // REGISTRY.put("AS1" ,  new AStarSearch(heuristic));
        // REGISTRY.put("AS2" ,  new AStarSearch(heuristic));
        // REGISTRY.put("GR1" ,  new GreedySearch(heuristic));
        // REGISTRY.put("GR2" ,  new GreedySearch(heuristic));
    }

    private StrategyFactory() {
    }

    public static ISearch getStrategy(String strategyKey) {
        return REGISTRY.get(strategyKey);
    }
}
