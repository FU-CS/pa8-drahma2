package pa8;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GraphMatrixTest{

    @Test
    void testAddEdgeBFS(){
        GraphMatrix g = new GraphMatrix(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        assertEquals("0 1 3 2 4 ", g.bfs(0));
    }

    @Test
    void testAddEdgeDFS(){
        GraphMatrix g = new GraphMatrix(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        assertEquals("0 1 2 3 4 ", g.dfs(0));
    }

    @Test
    void testHasCycle(){
        GraphMatrix g = new GraphMatrix(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 1);
        assertEquals(false, g.hasCycle());

        GraphMatrix g1 = new GraphMatrix(4);
        g1.addEdge(1, 0);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 1);
        assertEquals(true, g1.hasCycle());
    }

    @Test
    void testCycle(){
        GraphMatrix g = new GraphMatrix(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        assertEquals(true, g.hasCycle());
    }

    @Test
    void testCycle2(){
        GraphMatrix g = new GraphMatrix(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        assertEquals(false, g.hasCycle());
    }

    @Test
    void testCycle3(){
        GraphMatrix g = new GraphMatrix(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4,0);
        assertEquals(true, g.hasCycle());
    }

    @Test
    void testShortestPath() {
        GraphMatrix g = new GraphMatrix(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        assertEquals("0 -> 3 -> 4", g.shortestPath(0, 4));  
    }

    @Test
    void testShortestPathDisconnected(){
        GraphMatrix g = new GraphMatrix(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(4, 5);
        assertEquals("No path", g.shortestPath(0, 5));
    }

    @Test
    void testBFS(){
        GraphMatrix g = new GraphMatrix(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        assertEquals("2 3 4 5 ", g.bfs(2));
    }

    @Test
    void testDFS(){
        GraphMatrix g = new GraphMatrix(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(4, 5);
        assertEquals("0 1 2 3 ", g.dfs(0));
    }
}
