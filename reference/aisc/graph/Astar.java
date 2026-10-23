
import java.util.*;

public class Astar {

    static Map<String, List<String>> graph = new LinkedHashMap<>();
    static Map<String, Map<String, Integer>> costs = new LinkedHashMap<>();
    static Map<String, Integer> H = new LinkedHashMap<>();

    // STEP 1: Create the graph
    static void createGraph() {
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D"));
        graph.put("C", Arrays.asList("A", "D"));
        graph.put("D", Arrays.asList("B", "C"));
    }

    // STEP 2: Store edge costs
    static void createCosts() {
        costs.put("A", Map.of("B", 4, "C", 2));
        costs.put("B", Map.of("A", 4, "D", 5));
        costs.put("C", Map.of("A", 2, "D", 3));
        costs.put("D", Map.of("B", 5, "C", 3));
    }

    // STEP 3: Store heuristic values
    static void createH() {
        H.put("A", 5);
        H.put("B", 3);
        H.put("C", 2);
        H.put("D", 0);
    }

    // STEP 4: Perform A* search
    static void aStar(String start, String goal) {

        // Nodes waiting to be explored, ordered by lowest f value
        PriorityQueue<String> open = new PriorityQueue<>(
            Comparator.comparingInt(
                n -> g.getOrDefault(n, Integer.MAX_VALUE) + H.get(n)
            )
        );

        // Actual cost from start to each node
        Map<String, Integer> g = new HashMap<>();

        // Parent of each node, used to reconstruct the path
        Map<String, String> parent = new HashMap<>();

        // Nodes whose best path has been processed
        Set<String> closed = new HashSet<>();

        // Start node has cost 0
        g.put(start, 0);
        open.add(start);

        while (!open.isEmpty()) {

            // STEP 4.1: Select node with lowest f = g + h
            String current = open.poll();

            // STEP 4.2: Stop when the goal is reached
            if (current.equals(goal)) {
                List<String> path = new ArrayList<>();

                while (current != null) {
                    path.add(current);
                    current = parent.get(current);
                }

                Collections.reverse(path);
                System.out.println("Path: " + path);
                System.out.println("Total cost: " + g.get(goal));
                return;
            }

            if (!closed.add(current)) {
                continue;
            }

            // STEP 4.3: Examine each neighbor
            for (String neighbor : graph.get(current)) {

                int newCost = g.get(current)
                             + costs.get(current).get(neighbor);

                // STEP 4.4: Update if this path is cheaper
                if (newCost < g.getOrDefault(neighbor, Integer.MAX_VALUE)) {

                    g.put(neighbor, newCost);
                    parent.put(neighbor, current);

                    // Reinsert so the queue considers the updated cost
                    open.remove(neighbor);
                    open.add(neighbor);

                    // Allow reconsideration if a cheaper route is found
                    closed.remove(neighbor);
                }
            }
        }

        System.out.println("Goal not found");
    }

    public static void main(String[] args) {
        createGraph();
        createCosts();
        createH();

        aStar("A", "D");
    }
}


/*

1 - Initialize
Start at A with g(A) = 0. Add A to the priority queue. Its score is \(f(A)=0+5=5\).

2 - Explore neighbors
From A, B gets \(g=4, f=4+3=7\), while C gets \(g=2, f=2+2=4\). So C is explored next.

3 - Update costs
From C, D gets \(g=2+3=5\), and \(f=5+0=5\). A* selects the lowest-scoring available node.

4 - Reconstruct the path
The parent map tells us D came from C, and C came from A. Reverse that sequence to obtain [A, C, D].