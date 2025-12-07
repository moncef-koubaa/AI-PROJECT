package searchAlgorithms;

import contracts.ISearch;
import heuristics.EuclideanHeuristic;
import heuristics.ManhattanHeuristic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class StrategyFactory {
    private static final Map<String,  ISearch> REGISTRY = new ConcurrentHashMap<>();
    static {
        REGISTRY.put("BF" ,  new BreadthFirstSearch());
        REGISTRY.put("DF" ,  new DepthFirstSearch());
        REGISTRY.put("ID" , new IterativeDeepeningSearch());
         REGISTRY.put("UC" ,  new UniformCostSearch());
         REGISTRY.put("AS1" ,  new AStarSearch(new ManhattanHeuristic()));
         REGISTRY.put("AS2" ,  new AStarSearch(new EuclideanHeuristic()));
         REGISTRY.put("GR1" ,  new GreedySearch(new ManhattanHeuristic()));
         REGISTRY.put("GR2" ,  new GreedySearch(new EuclideanHeuristic()));
    }

    private StrategyFactory() {
    }

    public static ISearch getStrategy(String strategyKey) {
        return REGISTRY.get(strategyKey);
    }
}
