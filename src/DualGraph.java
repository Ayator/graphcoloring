import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DualGraph extends Graph{
    private Map<Integer, Boolean> isInX;
    private int countX;
    private Map<Integer, Integer> degreesX;
    private Set<Integer> Y;

    private DualGraph(int V, int E){
        super(V, E);
        this.degreesX = new HashMap<>(V);
        this.isInX = new HashMap<>(V);
        for (int i = 0; i < V; i++) {
            this.degreesX.put(i, 0);
            this.isInX.put(i, true);
        }
        this.countX = V;
        this.Y = new HashSet<>();
    }

    public static DualGraph FromGraph(Graph graph) {
        DualGraph newGraph = new DualGraph(graph.V, graph.E);
        for (int u : graph.V()) {
            HashSet<Integer> adjU = newGraph.edges.get(u);
            for (int v : graph.E().get(u)) {
                adjU.add(v);
            }
            newGraph.degrees.put(u, graph.degrees.get(u));
        }
        return newGraph;
    }

    @Override
    public HashSet<Integer> removeVertex(int u){
        countX--;
        degreesX.remove(u);
        return super.removeVertex(u);
    }

    public boolean isXEmpty(){
        return countX == 0;
    }

    public int getDegreeX(int v){
        return degreesX.get(v);
    }

    public int getDegreeY(int v){
        return degrees.get(v) - degreesX.get(v);
    }

    private void decreaseDegreeX(int v){
        int degV = degreesX.get(v);
        degreesX.put(v, degV - 1);
    }

    public boolean isInX(int v){
        return isInX.get(v);
    }

    public void moveToY(int u){
        Y.add(u);
        isInX.put(u, false);
        countX--;
        for (int v : edges.get(u)) {
            decreaseDegreeX(v);
        }
    }

    public Set<Integer> Y() {
        return Y;
    }
}
