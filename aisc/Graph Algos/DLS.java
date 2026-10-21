
import java.util.*;

public class DLS {

    static Map<String, List<String>> graph = new HashMap<>();

    static boolean dls(String node, String goal, int limit,
                       List<String> path, List<String> visited) {

        visited.add(node);
        path.add(node);

        if (node.equals(goal)) {
            return true;
        }

        if (limit <= 0) {
            path.remove(path.size() - 1);
            return false;
        }

        for (String child : graph.get(node)) {
            if (dls(child, goal, limit - 1, path, visited)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {

        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D", "E"));
        graph.put("C", Arrays.asList("F", "G"));
        graph.put("D", Arrays.asList("H", "I"));
        graph.put("E", Arrays.asList("J"));
        graph.put("F", Arrays.asList("K"));

        graph.put("G", Arrays.asList());
        graph.put("H", Arrays.asList());
        graph.put("I", Arrays.asList());
        graph.put("J", Arrays.asList());
        graph.put("K", Arrays.asList());

        String start = "A";
        String goal = "J";

        for (int limit = 2; limit <= 3; limit++) {
            
            List<String> path = new ArrayList<>();
            List<String> visited = new ArrayList<>();

            boolean found = dls("A", "J", limit, path, visited);

            System.out.println("Limit: " + limit);
            System.out.println("Visited: " + visited);

            if (found) {
                System.out.println("Path: " + path);
            } else {
                System.out.println("Goal not found");
            }
        }
    }
}
