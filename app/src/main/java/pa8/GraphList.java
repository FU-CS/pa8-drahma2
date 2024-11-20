package pa8;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GraphList implements Graph{
    Map<Integer, ArrayList<Integer>> map;
    int nodes;

    public GraphList(int nodes){
        map = new HashMap<>();
        this.nodes = nodes;
    }

    public void addEdge(int v, int w){
        if (!this.map.containsKey(v)){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(w);
            this.map.put(v, list);
        }
        else{
            this.map.get(v).add(w);
        }
    }

    public void addWeightedEdge(int v, int w, int weight){
        //Pair<Integer, Integer> pair = new Pair<>(w, weight);
        //this.map.get(v).add(pair);
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
                ArrayList<Integer> neighbor = this.map.get(curr);
                for (Integer n : neighbor){
                    queue.add(n);
                    visited[n] = true;
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
            List<Integer> neighbors = this.map.get(start);
            for (Integer n : neighbors) {
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
            ArrayList<Integer> neighbors = this.map.get(current);
            for (Integer neighbor : neighbors) {
                if (hasCycleHelper(neighbor, visited, recursionStack)) {
                    return true;
                }
            }
        }
        recursionStack[current] = false;
        return false;
    }


    public String shortestPath(int v, int w){
        String shortPath = "";
        boolean[] visited = new boolean[this.nodes];
        int[] parent = new int[this.nodes];
        Queue<Integer> queue = new LinkedList<>();

        visited[v] = true;
        queue.add(v);
        parent[v] = -1;

        boolean found = false;

        while (!queue.isEmpty() && !found){
            int curr = queue.poll();
            if (this.map.containsKey(curr)){
                ArrayList<Integer> neighbors = this.map.get(curr);
                for (Integer neighbor : neighbors){
                    if (!visited[neighbor]){
                        visited[neighbor] = true;
                        parent[neighbor] = curr;
                        queue.add(neighbor);
                        if (neighbor == w){
                            found = true;
                            break;
                        }
                    }
                }
            }
        }
        if (found){
            int curr = w;
            while (curr != -1) {
                if (!shortPath.isEmpty()) {
                    shortPath = curr + " -> " + shortPath;
                } else {
                    shortPath = curr + "";
                }
                curr = parent[curr];
            }
        return shortPath;
        }
        else{
            return "No path";
        }
    }
}
