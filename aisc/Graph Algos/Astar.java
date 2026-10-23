import java.util.*;
public class Astar {
    public static Map<String, List<String>> graph = new LinkedHashMap<>();
    public static Map<String, Map<String, Integer>> costs = new LinkedHashMap<>();
    public static Map<String, Integer> H = new LinkedHashMap<>();

    public static void createGraph() {
        graph.put("A", Arrays.asList("B","C"));
        graph.put("B", Arrays.asList("A","D"));
        graph.put("C", Arrays.asList("A","D"));
        graph.put("D", Arrays.asList("B","C"));
    }

    public static void createCosts() {
        costs.put("A", Map.of("B",4, "C", 2));
        costs.put("B", Map.of("A",4, "D", 5));
        costs.put("C", Map.of("A",2, "D", 3));
        costs.put("D", Map.of("B",5, "C", 3));
    }

    public static void createH() {
        H.put("A",5);
        H.put("B",3);
        H.put("C",2);
        H.put("D",0);
    }

    //calculate f(n) = estimated cost from start node to next node + heuristic value of that node
    // public static String node(String node) {
    //     int minimum = Integer.MAX_VALUE;
    //     String min_node = node;
    //     for(String neighbour: graph.get(node)) {
    //         int curr = costs.get(node).get(neighbour) + H.get(neighbour);
    //         if(minimum >= curr) {
    //             minimum = curr;
    //             min_node = neighbour;
    //         }
    //     }
    // }

    public static String node(String node, int g) {
        int minimum = Integer.MAX_VALUE;
        String min_node = node;
        for(String neighbour: graph.get(node)) {
            int curr = costs.get(node).get(neighbour) + H.get(neighbour);
            if(minimum >= curr) {
                minimum = curr;
                min_node = neighbour;
            }
        }
    }

    public static void main(String[] args) {
        createGraph();
        createCosts();
        System.out.println(graph);
        System.out.println(costs);
        createH();
    }
}