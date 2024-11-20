package pa8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphMatrixWeighted implements Graph{
    private int [][] matrix;

    public GraphMatrixWeighted(int n_nodes){
        this.matrix = new int[n_nodes][n_nodes];
    }

    public void addEdge(int v, int w){
        this.matrix[v][w] = 1;
    }

    public void addWeightedEdge(int v, int w, int weight){
        this.matrix[v][w] = weight;
    }

    public String bfs(int start){
        if (this.matrix.length == 0) {
            return "";
        }
        
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
        return bfsStr.trim();
    }

    public String dfs(int start){
        if (this.matrix.length == 0) {
            return "";
        }
        
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
        // recursion stack is used to keep track of the nodes being explored in the search.
        
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

        //marks nodes visited
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


    public String shortestPath(int v, int w) {
        int[] distance = new int[this.matrix.length];
        int[] parent = new int[this.matrix.length];
        boolean[] visited = new boolean[this.matrix.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distance[v] = 0;
        
        // loop through all nodes
        for (int i = 0; i < this.matrix.length; i++) {
            int curr = -1;
            int min = Integer.MAX_VALUE;
            // find unvisited node with smallest distance
            for (int j = 0; j < this.matrix.length; j++) {
                if (!visited[j] && distance[j] < min) {
                    curr = j;
                    min = distance[j];
                }
            }
            // added because I kept getting out of bounds error
            if (curr == -1) {
                break;
            }
            visited[curr] = true;
            // Update distances if shorter path is found
            for (int n = 0; n < this.matrix.length; n++) {
                if (this.matrix[curr][n] != 0) { 
                    int weight = this.matrix[curr][n];
                    if (distance[curr] + weight < distance[n]) {
                        distance[n] = distance[curr] + weight;
                        parent[n] = curr;
                    }
                }
            }
        }
        if (distance[w] == Integer.MAX_VALUE) {
            return "No Path";
        }
        String shortPath = "";
        int current = w;
        while (current != -1) {
            if (!shortPath.isEmpty()) {
                shortPath = current + " -> " + shortPath;
            } else {
                shortPath = current + "";
            }
            current = parent[current];
        }

        return shortPath;
    }

}

