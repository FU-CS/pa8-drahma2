package pa8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphMatrixWeightedTest {
    @Test
    void testBFS() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(5);
        g.addWeightedEdge(0, 1, 5);
        g.addWeightedEdge(0, 2, 10);
        g.addWeightedEdge(1, 3, 3);
        g.addWeightedEdge(2, 4, 2);  
        assertEquals("0 1 2 3 4", g.bfs(0));
    }

    @Test
    void testBFS1() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(6);
        g.addWeightedEdge(0, 1, 1);  
        g.addWeightedEdge(2, 3, 2);  
        g.addWeightedEdge(4, 5, 3);  
        assertEquals("0 1", g.bfs(0));
    }

    @Test
    void testBFS2() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(0);
        assertEquals("", g.bfs(0));
    }

    @Test
    void testDFS() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(5);
        g.addWeightedEdge(0, 1, 1);  
        g.addWeightedEdge(0, 2, 1);  
        g.addWeightedEdge(1, 3, 1);  
        g.addWeightedEdge(2, 4, 1);  
        assertEquals("0 1 3 2 4 ", g.dfs(0));
    }

    @Test
    void testDFS1() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(6);
        g.addWeightedEdge(0, 1, 1);  
        g.addWeightedEdge(2, 3, 1);  
        g.addWeightedEdge(4, 5, 1);  
        assertEquals("0 1 ", g.dfs(0)); 
    }

    @Test
    void testDFS2() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(0);
        assertEquals("", g.dfs(0));  
    }

    @Test
    void testHasCycle() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(4);
        g.addWeightedEdge(0, 1, 1);  
        g.addWeightedEdge(1, 2, 1);  
        g.addWeightedEdge(2, 3, 1);  
        assertFalse(g.hasCycle());  
    }

    @Test
    void testHasCycle1() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(4);
        g.addWeightedEdge(0, 1, 1);  
        g.addWeightedEdge(1, 2, 1);  
        g.addWeightedEdge(2, 3, 1);  
        g.addWeightedEdge(3, 0, 1);  
        assertTrue(g.hasCycle());  
    }

    @Test
    void testHasCycle2() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(6);
        g.addWeightedEdge(0, 1, 1);  
        g.addWeightedEdge(1, 2, 1);  
        g.addWeightedEdge(2, 0, 1);  
        g.addWeightedEdge(4, 5, 1);  
        assertTrue(g.hasCycle());  
    }

    @Test
    void testShortestPath() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(5);
        g.addWeightedEdge(0, 1, 1);  
        g.addWeightedEdge(0, 2, 2);  
        g.addWeightedEdge(1, 3, 1);  
        g.addWeightedEdge(2, 4, 3);  
        assertEquals("0 -> 1 -> 3", g.shortestPath(0, 3));
    }

    @Test
    void testShortestPath1() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(5);
        g.addWeightedEdge(0, 1, 1);  
        g.addWeightedEdge(1, 2, 1);  
        g.addWeightedEdge(3, 4, 1);  
        assertEquals("No Path", g.shortestPath(0, 4));
    }

    @Test
    void testShortestPath2() {
        GraphMatrixWeighted g = new GraphMatrixWeighted(6);
        g.addWeightedEdge(0, 1, 2);  
        g.addWeightedEdge(1, 2, 3);  
        g.addWeightedEdge(0, 3, 1);  
        g.addWeightedEdge(3, 2, 4);
        g.addWeightedEdge(2, 4, 5);
        g.addWeightedEdge(3, 5, 1); 
        g.addWeightedEdge(5, 4, 1);  
        assertEquals("0 -> 3 -> 5 -> 4", g.shortestPath(0, 4));
    }
}
