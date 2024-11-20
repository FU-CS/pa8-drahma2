package pa8;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;



public class GraphListWeighted implements Graph{
    Map<Integer, ArrayList<Pair<Integer, Integer>>> map;
    Map<Integer, ArrayList<Integer>> unweighted;

    int nodes;

    public GraphListWeighted(int nodes){
        map = new HashMap<>();
        unweighted = new HashMap<>();
        this.nodes = nodes;
    }

    public void addEdge(int v, int w){
        if (!this.map.containsKey(v)){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(w);
            this.unweighted.put(v, list);
        }
        else{
            this.unweighted.get(v).add(w);
        }
    }

    public void addWeightedEdge(int v, int w, int weight){
        if (!this.map.containsKey(v)) {
            ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
            list.add(new Pair<>(w, weight));
            this.map.put(v, list);
        } else {
            this.map.get(v).add(new Pair<>(w, weight));
        }
    }

    public String bfs(int start){
        if (this.nodes == 0) {
            return "";
        }
        
        String bfsStr = "";
        boolean[] visited = new boolean[this.nodes];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            bfsStr += curr + " ";
            if (this.map.containsKey(curr)){
                ArrayList<Pair<Integer, Integer>> neighbor = this.map.get(curr);
                for (Pair<Integer, Integer> pair : neighbor) {
                    int n = pair.getKey();
                    if (!visited[n]) {
                        queue.add(n);
                        visited[n] = true;
                    }
                }
            }
        }
        return bfsStr.trim();
    }

    public String dfs(int start) {
        if (this.nodes == 0) {
            return "";
        }
        
        String dfsStr = "";
        boolean[] visited = new boolean[this.nodes];
        return dfs_helper(start, dfsStr, visited);
    }

    private String dfs_helper(int start, String str, boolean[] visited) {
        visited[start] = true;
        str += start + " ";
        if (this.map.containsKey(start)){
            List<Pair<Integer, Integer>> neighbors = this.map.get(start);
            for (Pair<Integer, Integer> pair : neighbors) {
                int n = pair.getKey();
                if (!visited[n]) {
                    str = dfs_helper(n, str, visited);
                }
            }
        }
        
        return str;
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[this.nodes];
        boolean[] recursionStack = new boolean[this.nodes];
    
        for (int i = 0; i < this.nodes; i++) {
            if (!visited[i]) {
                if (hasCycleHelper(i, visited, recursionStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleHelper(int current, boolean[] visited, boolean[] recursionStack) { 
        if (recursionStack[current]) {
            return true;
        }
        if (visited[current]) {
            return false;
        }
        visited[current] = true;
        recursionStack[current] = true;
    
        if (this.map.containsKey(current)) {
            ArrayList<Pair<Integer, Integer>> neighbors = this.map.get(current);
            for (Pair<Integer, Integer> pair : neighbors) {
                int neighbor = pair.getKey();
                if (hasCycleHelper(neighbor, visited, recursionStack)) {
                    return true;
                }
            }
        }
        recursionStack[current] = false;
        return false;
    }

    private static class Edge implements Comparable<Edge>{
        int destination;
        int weight;
    
        public Edge(int source, int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    
        // Compare edges based on their weight
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public String shortestPath(int v, int w){
        int[] distance = new int[this.nodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[v] = 0;
        int[] parent = new int[this.nodes]; 
        Arrays.fill(parent, -1);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(v, v, 0));
    
        while (!pq.isEmpty()) {
            Edge currEdge = pq.poll();
            int u = currEdge.destination;
            if (distance[u] == currEdge.weight) {
                if (this.map.containsKey(u)) {
                    for (Pair<Integer, Integer> pair : this.map.get(u)) {
                        int neighbor = pair.getKey();
                        int weight = pair.getValue();
                        if (distance[u] + weight < distance[neighbor]) {
                            distance[neighbor] = distance[u] + weight;
                            parent[neighbor] = u;
                            pq.add(new Edge(u, neighbor, distance[neighbor]));
                        }
                    }
                }
            }
        }
    
        if (distance[w] == Integer.MAX_VALUE) {
            return "No path";
        }

        String path = "";
        int current = w;
        boolean first = true;
        
        while (current != v) {
            if (first) {
                path = current + "";
                first = false;
            } else {
                path = current + " -> " + path; 
            }
            current = parent[current];
        }
    
        if (first) {
            path = v + "";
        } else {
            path = v + " -> " + path;
        }
    
        return path;
    }
}
