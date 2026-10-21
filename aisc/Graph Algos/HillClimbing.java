//step 1 - create values
//step 2 - create graph
//step 3 - write the getbestneighbour -- traverse all the childs and get with the maximum weight
//step 4 - write the hill climbing algo -- get the best neighbour till it is greater than the curr

import java.util.*;

public class HillClimbing {
    static HashMap<String,Integer> values = new HashMap<>();
    static HashMap<String, List<String>> graph = new HashMap<>();

    public static void createStates() {
        values.put("A",2);
        values.put("B",5);
        values.put("C",8);
        values.put("D",6);
    }

    public static void createGraph() {
        graph.put("A",Arrays.asList("B","C"));
        graph.put("B",Arrays.asList("A","D"));
        graph.put("C",Arrays.asList("A","D"));
        graph.put("D",Arrays.asList("B","C"));
    }

    public static String getBestNeighbour(String node) {
        int max = Integer.MIN_VALUE;
        String max_node = node; //strings cannot be updated so we use stringbuilder
        for(String neighbour: graph.get(node)) {
            if(values.get(neighbour) >= max) {
                max = values.get(neighbour);
                max_node = neighbour;
            }
        }
        return max_node;
    }

    public static String HillClimbing(String current) {
        while(true) { //but no parameters like goal this wont work
            String best = getBestNeighbour(current);
            if(values.get(best) <= values.get(current)) {
                return current;
            }
            else {
                current = best;
            }
        }    
        return current;
    }

    public static void main(String[] args) {
        createStates();
        createGraph();
        System.out.println(getBestNeighbour("A"));
        System.out.println(getBestNeighbour("B"));
        System.out.println(getBestNeighbour("C"));
        System.out.println(getBestNeighbour("D"));
        System.out.println(HillClimbing("A"));
    }
}