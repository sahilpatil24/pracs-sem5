import java.util.*;

public class A_star {

    //create a subclass named node
    static class Node {
        String name;
        int g, f;

        Node(String name, int g, int f) {
            this.name = name;
            this.g = g;
            this.f = f;
        }
    }

    //create the graph and heursitic values
    static Map<String, Map<String, Integer>> graph = new HashMap<>();
    static Map<String, Integer> h = new HashMap<>();

    //add edges
    static void addEdge(String from, String to, int cost) {
        graph.putIfAbsent(from, new HashMap<>());
        graph.putIfAbsent(to, new HashMap<>());

        graph.get(from).put(to, cost);
    }

    static void aStar(String start, String goal) {

        PriorityQueue<Node> pq =
            new PriorityQueue<>(Comparator.comparingInt(n -> n.f));

        Map<String, Integer> g = new HashMap<>();
        Map<String, String> parent = new HashMap<>();

        g.put(start, 0);
        pq.offer(new Node(start, 0, h.get(start)));

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            // Ignore outdated entries
            if (current.g != g.get(current.name))
                continue;

            // Goal reached
            if (current.name.equals(goal))
                break;

            for (String next : graph.get(current.name).keySet()) {

                int cost = graph.get(current.name).get(next);
                int newG = current.g + cost;

                if (newG < g.getOrDefault(next, Integer.MAX_VALUE)) {

                    g.put(next, newG);
                    parent.put(next, current.name);

                    int f = newG + h.get(next);

                    pq.offer(new Node(next, newG, f));
                }
            }
        }

        if (!g.containsKey(goal)) {
            System.out.println("No path found");
            return;
        }

        List<String> path = new ArrayList<>();

        String current = goal;

        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);

        System.out.println("Path: " + path);
        System.out.println("Total cost: " + g.get(goal));
    }

    public static void main(String[] args) {

        addEdge("A", "B", 1);
        addEdge("A", "C", 4);
        addEdge("B", "C", 2);
        addEdge("B", "D", 5);
        addEdge("C", "D", 1);
        addEdge("C", "E", 6);
        addEdge("D", "E", 2);
        addEdge("D", "G", 5);
        addEdge("E", "G", 2);

        h.put("A", 6);
        h.put("B", 5);
        h.put("C", 3);
        h.put("D", 2);
        h.put("E", 2);
        h.put("G", 0);

        aStar("A", "G");
    }
}