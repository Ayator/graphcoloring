import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Graph {
    private int V, E;
    private Map<Integer, HashSet<Integer>> edges; 

    public int getNumberOfVertices() {
        return V;
    }

    public int getNumberOfEdges() {
        return E;
    }

    public Set<Integer> V(){
        return edges.keySet();
    }
    
    public Map<Integer, HashSet<Integer>> E(){
        return edges;
    }

    private Graph(int V, int E){
        this.V = V;
        this.E = E;
        edges = new HashMap<>(V);
        for (int i = 0; i < V; i++) {
            edges.put(i, new HashSet<>());
        }
    }

    private boolean addEdge(int u, int v){
        return edges.get(u).add(v) && edges.get(v).add(u);
    }
    
    public static Graph LoadFromInputFile(String inputFilepath){
        try{
            File file = new File(inputFilepath);
            Scanner sc = new Scanner(file);
            int V = readNextInt(sc);
            int E = readNextInt(sc);
            Graph gc = new Graph(V, E);
            for(int i = 0; i < E; i++){
                int u = readNextInt(sc);
                int v = readNextInt(sc);
                if(!gc.addEdge(u, v)){
                    System.out.println("Duplicate Edge: [" + u + "-" + v + "].");
                    System.exit(1);
                }
            }
            sc.close();
            return gc;
        } catch (FileNotFoundException e) {
            System.out.println("Error while reading the file.");
            e.printStackTrace();
        }
        return null;
    }

    public static int readNextInt(Scanner sc){
        if(!sc.hasNextInt()){
            sc.close();
            System.out.println("There is no next integer to read.");
            System.exit(1);
        }
        return sc.nextInt();
    }

    @Override
    public String toString() {
        Iterator<Integer> iterator = edges.keySet().iterator();
        if(!iterator.hasNext())
            return null;
        int i = iterator.next();
        String str = "Graph: [\n\t" + i + " -> " + edges.get(i);
        while(iterator.hasNext()){
            i = iterator.next();
            str += ",\n\t" + i + " -> " + edges.get(i);
        }
        str += "\n]";
        return str;
    }
}
