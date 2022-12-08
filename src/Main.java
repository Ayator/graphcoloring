import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        LinkedHashMap<String, Integer> goals = getGoals();
        for (String filename : goals.keySet()) {
            System.out.println();
            while(!isFileBeaten(filename, goals)){
                // SpaceTime st = new SpaceTime();
                // st.start();
                System.out.println("Running: " + filename);
                Graph graph = Graph.LoadFromInputFile("../input/" + filename);
                Set<Set<Integer>> solution = GraphColoring.GreedyIndependentSet(graph);
                int nOfColors = solution.size();
                System.out.println(filename + ": " + nOfColors);
                saveToFile(nOfColors, graph.getNumberOfVertices(), solution, "../output/" + filename);
                // st.stopThread();
                // st.printData(true, true);
            }
        }
    }

    // returns true if the output is better than the existing one
    public static boolean saveToFile(int nOfColors, int V, Set<Set<Integer>> solution, String outputFilepath){
        try {
            File currentOutput = new File(outputFilepath);
            // check if the best output file already exists
            if(currentOutput.exists() && !currentOutput.isDirectory()){
                // if it exists then check if the color amount is higher
                Scanner sc = new Scanner(currentOutput);
                int currentColorAmount = sc.nextInt();
                sc.close();
                // return if so
                if(nOfColors >= currentColorAmount)
                    return false;
            }
            System.out.println("Color amount is lower. Saving the file!");
            // save to output
            FileWriter outputFile = new FileWriter(outputFilepath);
            String output = GraphColoring.SetsToString(solution, V);
            outputFile.write(output);
            outputFile.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error while saving the file.");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isFileBeaten(String filename, LinkedHashMap<String, Integer> goals){
        try {
            String path = "../output/" + filename;
            File output = new File(path);
            if(output.exists() && !output.isDirectory()){
                // if it exists then check if the best cost is lower than the goal
                Scanner sc = new Scanner(output);
                int colorAmount = sc.nextInt();
                sc.close();
                int goalAmount = goals.get(filename);
                if(colorAmount <= goalAmount){
                    System.out.println(filename + " beaten." + colorAmount + " vs " + goalAmount);
                    return true;
                }
                System.out.println(filename + " not beaten." + colorAmount + " vs " + goalAmount);
                return false;
            }
            else {
                System.out.println(filename + " not beaten. It doesn't exist.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while reading " + filename);
            return false;
        }

    }

    public static LinkedHashMap<String, Integer> getGoals(){
        LinkedHashMap<String, Integer> goals = new LinkedHashMap<>(36);
        // goals.put("gc_4_1", 2);
        // goals.put("gc_20_1", 3);
        // goals.put("gc_20_3", 5);
        // goals.put("gc_20_5", 5);//
        // goals.put("gc_20_7", 8);
        // goals.put("gc_20_9", 11);
        // goals.put("gc_50_1", 4);
        // goals.put("gc_50_3", 6);//
        // goals.put("gc_50_5", 9);
        // goals.put("gc_50_7", 14);
        // goals.put("gc_50_9", 22);
        // goals.put("gc_70_1", 4);
        // goals.put("gc_70_3", 8);//
        // goals.put("gc_70_5", 12);
        // goals.put("gc_70_7", 17);
        // goals.put("gc_70_9", 28);//
        // goals.put("gc_100_1", 5);
        // goals.put("gc_100_3", 9);//
        // goals.put("gc_100_5", 17);
        // goals.put("gc_100_7", 24);
        // goals.put("gc_100_9", 39);
        // goals.put("gc_250_1", 9);
        // goals.put("gc_250_3", 21);
        // goals.put("gc_250_5", 35);
        // goals.put("gc_250_7", 53);
        // goals.put("gc_250_9", 84);
        // goals.put("gc_500_1", 15);
        // goals.put("gc_500_3", 37);
        // goals.put("gc_500_5", 63);
        // goals.put("gc_500_7", 93);
        goals.put("gc_500_9", 146);//
        // goals.put("gc_1000_1", 26);
        // goals.put("gc_1000_3", 66);
        // goals.put("gc_1000_5", 113);
        // goals.put("gc_1000_7", 173);
        // goals.put("gc_1000_9", 280);
        return goals;
    }
}
