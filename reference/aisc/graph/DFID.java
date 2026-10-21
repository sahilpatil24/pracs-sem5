import java.util.*;

public class DFID {
    static Map<String, List<String>> graph = new LinkedHashMap<>();

    static void createGraph() {
        graph.put("A", Arrays.asList("B","C"));
        graph.put("B", Arrays.asList("D","E"));
        graph.put("C", Arrays.asList("F","G"));
        graph.put("D", Arrays.asList("H","I"));
        graph.put("E", Arrays.asList("J"));
        graph.put("F", Arrays.asList("K"));

        graph.put("G", Arrays.asList());
        graph.put("H", Arrays.asList());
        graph.put("I", Arrays.asList());
        graph.put("J", Arrays.asList());
        graph.put("K", Arrays.asList());
    }

    public static boolean dls(String node, String goal, int limit, Set<String> visited, ArrayList<String> path) {
        visited.add(node);
        path.add(node);

        if(node.equals(goal)) return true;

        if(limit <= 0) {
            path.remove(path.size() - 1);
            return false;
        }

        for(String child: graph.get(node)) {
            if(!visited.contains(child)) {
                if(dls(child,goal, limit - 1, visited,path)) return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static boolean dfid(String node, String goal, int limit) {
        for(int i = 0; i <= limit; i++) {
            HashSet<String> visited = new HashSet<>();
            ArrayList<String> path = new ArrayList<>();
            if(dls(node,goal,i, visited,path)) {
                System.out.println(path);
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        createGraph();
        // System.out.println(dls("A","J",3,new HashSet<>(), path));
        ArrayList<String> path = new ArrayList<>();
        System.out.println(dls("A","H",4,new HashSet<>(), path));
        System.out.println(path);
        System.out.println(dfid("A","H",3));
        System.out.println(dfid("A","H",4));
    }
}