import java.util.Set;

public class Main {
    public static void main(String[] args){
        String[] filenames = new String[]{
            "gc_4_1"//,
            // "gc_20_1",
            // "gc_20_3",
            // "gc_20_5",
            // "gc_20_7",
            // "gc_20_9",
            // "gc_50_1",
            // "gc_50_3",
            // "gc_50_5",
            // "gc_50_7",
            // "gc_50_9",
            // "gc_70_1",
            // "gc_70_3",
            // "gc_70_5",
            // "gc_70_7",
            // "gc_70_9",
            // "gc_100_1",
            // "gc_100_3",
            // "gc_100_5",
            // "gc_100_7",
            // "gc_100_9",
            // "gc_250_1",
            // "gc_250_3",
            // "gc_250_5",
            // "gc_250_7",
            // "gc_250_9",
            // "gc_500_1",
            // "gc_500_3",
            // "gc_500_5",
            // "gc_500_7",
            // "gc_500_9",
            // "gc_1000_1",
            // "gc_1000_3",
            // "gc_1000_5",
            // "gc_1000_7",
            // "gc_1000_9"
        };
        for (String filename : filenames) {
            // SpaceTime st = new SpaceTime();
            // st.start();
            System.out.println();
            System.out.println("Running: " + filename);
            Graph graph = Graph.LoadFromInputFile("../input/" + filename);
            Set<Set<Integer>> solution = GraphColoring.GreedyIndependentSet(graph);
            System.out.println(solution);
            // st.stopThread();
            // st.printData(true, true);
        }
    }
}
