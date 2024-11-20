package pa8;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GraphListTest {
    
    @Test
    void testAddEdgeBFS(){
        GraphList g = new GraphList(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        assertEquals("0 1 3 2 4", g.bfs(0));
    }

    @Test
    void testAddEdgeDFS(){
        GraphList g = new GraphList(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        assertEquals("0 1 2 3 4 ", g.dfs(0));
    }

    @Test
    void testHasCycle(){
        GraphList g = new GraphList(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 1);
        assertEquals(false, g.hasCycle());

        GraphList g1 = new GraphList(4);
        g1.addEdge(1, 0);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 1);
        assertEquals(true, g1.hasCycle());
    }

    @Test
    void testCycle(){
        GraphList g = new GraphList(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        assertEquals(true, g.hasCycle());
    }

    @Test
    void testCycle2(){
        GraphList g = new GraphList(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        assertEquals(false, g.hasCycle());
    }

    @Test
    void testCycle3(){
        GraphList g = new GraphList(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4,0);
        assertEquals(true, g.hasCycle());
    }

    @Test
    void testShortestPath() {
        GraphList g = new GraphList(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        assertEquals("0 -> 3 -> 4", g.shortestPath(0, 4));  
    }

    @Test
    void testBFS(){
        GraphList g = new GraphList(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        assertEquals("2 3 4 5", g.bfs(2)); 
    }

    @Test
    void testDFS(){
        GraphList g = new GraphList(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(4, 5); 
        assertEquals("0 1 2 3 ", g.dfs(0));
    }

    @Test
    void testShortestPath1(){
        GraphList g = new GraphList(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(4, 5);
        assertEquals("No path", g.shortestPath(0, 5));
    }

    @Test
    void testCycle1(){
        GraphList g = new GraphList(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        assertEquals(true, g.hasCycle());
    }

    @Test
    void testBFSInEmptyGraph(){
        GraphList g = new GraphList(0);
        assertEquals("", g.bfs(0));
    }

    @Test
    void testDFSInEmptyGraph(){
        GraphList g = new GraphList(0);
        assertEquals("", g.dfs(0));
    }

    @Test
    void testShortestPath2() {
        GraphList g = new GraphList(9);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(0, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(7, 8);
        assertEquals("0 -> 4 -> 5 -> 7 -> 8", g.shortestPath(0, 8));  
    }

}