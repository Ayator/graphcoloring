import java.util.HashSet;
import java.util.Set;

public class GraphColoring {

    public Set<Set<Integer>> GreedyIndependentSet(Graph G){
        // sets = nullSet
        Set<Set<Integer>> sets = new HashSet<>();
        // while V is not empty do:
        while(!G.V().isEmpty()){
            // newSet = GetMaximalIndependentSet(graph)
            Set<Integer> newSet = GetMaximalIndependentSet(G);
            // sets.add(newSet)
            sets.add(newSet);
            // remove all vertex v of newSet from V
        }
        // return sets
        return sets;
    }

    private Set<Integer> GetMaximalIndependentSet(Graph G){
        // X = V
        // Y = nullSet
        // newSet = nullSet
        // while X is not empty do:
            // first time remove vertex u from X such that u has highest degree in X
            // else remove vertex u from X such that u has highest degree in Y
            // remove u from graph ---
            // newSet.add(u)
            // for all v adjacent to u do:
                // if v in X:
                    // X.remove(v)
                // if v not in Y:
                    // Y.add(v)
        // return newSet
        return null;
    } 
}
