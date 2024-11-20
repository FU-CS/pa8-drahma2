package pa8;

import java.util.LinkedList;
import java.util.Queue;

public class GraphMatrix implements Graph{
    private int [][] matrix;

    public GraphMatrix(int n_nodes){
        this.matrix = new int[n_nodes][n_nodes];
    }

    public void addEdge(int v, int w){
        this.matrix[v][w] = 1;
    }

    public void addWeightedEdge(int v, int w, int weight){
        this.matrix[v][w] = weight;
    }

    public String bfs(int start){
        String bfsStr = "";
        boolean[] visited = new boolean[this.matrix.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        
        visited[start] = true;
        queue.add(start);

        while (queue.isEmpty() == false){
            int curr = queue.poll();
            bfsStr += curr + " ";
            int i = 0;
            while (i < this.matrix[curr].length){
                if(this.matrix[curr][i] != 0 && !visited[i]){
                    visited[i] = true;
                    queue.add(i);
                }
                i = i + 1;
            }
        }
        return bfsStr;
    }

    public String dfs(int start){
        String dfsStr = "";
        boolean[] visited = new boolean[this.matrix.length];
        return dfs_helper(start, dfsStr, visited);
    }

    private String dfs_helper(int start, String str, boolean[] visited){
        int[] neighbors = this.matrix[start];
        visited[start] = true;
        str += start + " ";
        for (int i = 0; i < neighbors.length; i++){
            if (neighbors[i] != 0 && !visited[i]){
                str = dfs_helper(i, str, visited);
            }
        }

        return str;
    }

    public boolean hasCycle(){
        // if node is in stack, cycle found
        if (this.matrix.length == 0){
            return false;
        }
        boolean[] visited = new boolean[this.matrix.length];
        boolean[] recursionStack = new boolean[this.matrix.length];
        for (int i=0; i < this.matrix.length; i++){
            if (!visited[i] && hasCycleHelper(i, visited, recursionStack)){
                return true;
            }
        }
        return false;
    }

    private boolean hasCycleHelper(int current, boolean[] visited, boolean[] recursionStack){ 
        // if node is in stack, cycle found
        if(recursionStack[current]){
            return true;
        }
        // node has already been looked at
        if (visited[current]){
            return false;
        }
        // marks nodes visited
        visited[current] = true;
        recursionStack[current] = true;
        //explores all neighbors of current
        int[] neighbors = this.matrix[current];
        for (int i=0; i < neighbors.length; i++){
            if (neighbors[i] != 0){    
                if (hasCycleHelper(i, visited, recursionStack)){
                        return true;
                }
            }
        }
        // after looking at all the neighbors, remove node from recursion stack.
        recursionStack[current] = false;
        return false;
    }


    public String shortestPath(int v, int w){
        String shortPath = "";
        // tracks visited
        boolean[] visited = new boolean[this.matrix.length];
        // stores pointers to help construct the path
        int[] parent = new int[this.matrix.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        
        visited[v] = true;
        queue.add(v);
        parent[v] = -1;

        // helps us know when w is found
        boolean found = false;
        // BFS to find w
        while (!queue.isEmpty() && !found){
            int curr = queue.poll();
            for (int i = 0; i < this.matrix[curr].length; i++) {
                if (this.matrix[curr][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    parent[i] = curr;  
                    queue.add(i);
                    if (i == w) {
                        found = true;
                        break;
                    }
                }
            }
        }

        // constructs the path from v to w starting from the target
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
