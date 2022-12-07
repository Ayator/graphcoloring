import java.util.HashSet;
import java.util.Set;

public class GraphColoring {

    public static Set<Set<Integer>> GreedyIndependentSet(Graph G){
        // sets = nullSet
        Set<Set<Integer>> sets = new HashSet<>();
        // while V is not empty do:
        while(!G.V().isEmpty()){
            // newSet = GetMaximalIndependentSet(graph)
            Set<Integer> newSet = GetMaximalIndependentSet(G);
            // sets.add(newSet)
            sets.add(newSet);
            // remove all vertices v of newSet from V
            for (int v : newSet)
                G.removeVertex(v);
        }
        // return sets
        return sets;
    }

    private static Set<Integer> GetMaximalIndependentSet(Graph G){
        // X = V, Y = nullSet
        DualGraph XY = DualGraph.FromGraph(G);
        // newSet = nullSet
        Set<Integer> newSet = new HashSet<>();
        boolean isFirstIteration = true;
        // while X is not empty do:
        while(!XY.isXEmpty()){
            // X.remove(u)
            int u = GetVertexFromX(XY, isFirstIteration);
            HashSet<Integer> adjU = XY.removeVertex(u);
            // newSet.add(u)
            newSet.add(u);
            // for all v adjacent to u do:
            for (int v : adjU) {
                // if v in X: X.remove(v)
                // if v not in Y: Y.add(v)
                XY.moveToY(v);
            }
            isFirstIteration = false;
        }
        // return newSet
        return newSet;
    }

    // not aesthetic at all...
    private static int GetVertexFromX(DualGraph XY, boolean isFirstIteration) {
        // if first time then remove vertex u from X such that u has the highest degree in X
        if(isFirstIteration){
            int maxDeg = Integer.MIN_VALUE;
            int maxV = 0;
            for (int v : XY.V()) {
                int degree;
                if(XY.isInX(v) && (degree = XY.getDegreeX(v)) > maxDeg){
                    maxDeg = degree;
                    maxV = v;
                }
            }
            return maxV;
        }
        // else remove vertex u from X such that u has highest degree in Y
        else{
            int maxDeg = Integer.MIN_VALUE;
            int maxV = 0;
            for (int v : XY.Y()) {
                int degree = XY.getDegreeY(v);
                if(degree > maxDeg || (degree == maxDeg && XY.getDegreeX(v) < XY.getDegreeX(maxV))){
                    maxDeg = degree;
                    maxV = v;
                }
            }
            return maxV;
        }
    } 
}
