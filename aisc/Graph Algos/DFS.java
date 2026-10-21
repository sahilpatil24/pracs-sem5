//22:35
import java.util.*;
public class DFS {
    static Map<String, List<String>> graph = new LinkedHashMap<>();

    public static void createGraph() {
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

    public static boolean depthFirstSearch(String node, String goal, Set<String> visited, ArrayList<String> path) {
        //dfs
        //step 1 - mark the current node as visited
        visited.add(node);
        path.add(node);
        //step 2 - check if the current node is the goal
        if(node.equals(goal)) return true;

        //step 3 - for every child node call dfs
        for(String child: graph.get(node)) {
            if(!visited.contains(child)) { //if not visited then call dfs
                if(depthFirstSearch(child,goal,visited, path)) return true;
           }
        }
        path.remove(path.size() - 1);
        // System.out.println(path);
        return false;
    }

    public static boolean depthLimitSearch(String node, String goal, int limit, Set<String> visited, ArrayList<String> path) {
        //dls
        //step 1 - add the node to visited and path
        visited.add(node);
        path.add(node);

        //step 2 - chekc if the node is the goal
        if(node.equals(goal)) return true;

        //step 3 - check the limit
        if(limit <= 0) { path.remove(path.size() - 1); return false; }

        //step 4 - call every non visited child of the node
        for(String child: graph.get(node)) {
            if(!visited.contains(child)) {
                if(depthLimitSearch(child, goal, limit - 1, visited, path)) { 
                    System.out.println(path);
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static boolean depthFirstIterativeSearch(String node, String goal, int limit, Set<String> visited, ArrayList<String> path) {
        for(int i = 0; i < limit; i++) {
            if(depthLimitSearch(node, goal, i, visited, path)) {
                System.out.println("found at limit : " + i);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        createGraph();
        // boolean val = depthFirstSearch("A","J",new HashSet<>(), new ArrayList<>());
        // System.out.println(val);
        // boolean val2 = depthFirstSearch("A","L",new HashSet<>(), new ArrayList<>());
        // System.out.println(val2);
        
        // boolean val = depthLimitSearch("A","J", 4, new HashSet<>(), new ArrayList<>());
        // System.out.println(val);
        // boolean val2 = depthLimitSearch("A","L", 3, new HashSet<>(), new ArrayList<>());
        // System.out.println(val2);
        
        boolean val = depthFirstIterativeSearch("A","J", 5, new HashSet<>(), new ArrayList<>());
        System.out.println(val);
    }
}