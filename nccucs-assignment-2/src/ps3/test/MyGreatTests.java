package ps3.test;
import ps3.graph.*;
import junit.framework.TestCase;

public class MyGreatTests extends TestCase {

    public MyGreatTests(String name) {
        super(name);
    }

     /* Construct an initial graph object for testing use */
    public void testConstructor() {
        // Create a new graph
        Graph<WeightedNode> g = new Graph<WeightedNode>();
        // The collection of nodes shouldn't be null
        assertNotNull(g.nodes());
        // The collection of nodes should be empty
        assertEquals(g.nodes().isEmpty(), true);
    }

    /* Tests addNode(), but uses nodes() to verify the result. */
    public void testAddNode() {
        Graph<WeightedNode> g1 = new Graph<WeightedNode>();
        Graph<WeightedNode> g2 = new Graph<WeightedNode>();
        WeightedNode wn1 = new WeightedNode("wn1", 1);
        WeightedNode wn2 = new WeightedNode("wn2", 3);
        WeightedNode wn3 = new WeightedNode("wn3", 5);
        WeightedNode wn1bis = new WeightedNode("wn1", 1);

        // Test exceptions
        try {
            g1.addNode(null);
            fail();
        } catch (IllegalArgumentException e) {
            // Expected
        }

        // Test if the graph has only wn1 as node
        g1.addNode(wn1);
        assertEquals(g1.nodes().size(), 1);
        assertTrue(g1.nodes().contains(wn1));

        // Verify that g2 is not changed
        assertEquals(g2.nodes().size(), 0);

        // Try to add the same node to the graph
        g1.addNode(wn1);
        g1.addNode(wn1bis);
        assertEquals(g1.nodes().size(), 1);
        assertTrue(g1.nodes().contains(wn1));
        assertTrue(g1.nodes().contains(wn1bis));

        // Add wn2 to the graph
        g1.addNode(wn2);
        assertEquals(g1.nodes().size(), 2);
        assertTrue(g1.nodes().contains(wn1));
        assertTrue(g1.nodes().contains(wn2));

        // Add wn3 to the graph
        g1.addNode(wn3);
        assertEquals(g1.nodes().size(), 3);
        assertTrue(g1.nodes().contains(wn1));
        assertTrue(g1.nodes().contains(wn2));
        assertTrue(g1.nodes().contains(wn3));
    }

    /* Tests addEdge(), but uses children() to verify the result. */
    public void testAddEdge() {
        Graph<WeightedNode> g1 = new Graph<WeightedNode>();
        Graph<WeightedNode> g2 = new Graph<WeightedNode>();
        WeightedNode wn1 = new WeightedNode("wn1", 1);
        WeightedNode wn2 = new WeightedNode("wn2", 3);

        // Add the nodes to g1 and to g2 and test exceptions
        g1.addNode(wn1);
        try {
            g1.addEdge(null, wn1);
            fail();
        } catch (IllegalArgumentException e) {
            // Expected
        }
        try {
            g1.addEdge(wn1, null);
            fail();
        } catch (IllegalArgumentException e) {
            // Expected
        }
        try {
            g1.addEdge(wn1, wn2);
            fail();
        } catch (IllegalArgumentException e) {
            // Expected
        }
        try {
            g1.addEdge(wn2, wn1);
            fail();
        } catch (IllegalArgumentException e) {
            // Expected
        }
        g1.addNode(wn2);
        g2.addNode(wn1);
        g2.addNode(wn2);

        // Add an edge from wn1 to wn2 in g1
        g1.addEdge(wn1, wn2);
        assertEquals(g1.children(wn1).size(), 1);
        assertTrue(g1.children(wn1).contains(wn2));
        assertFalse(g1.children(wn2).contains(wn1));

        // Test if it has an effect on g2
        assertTrue(g2.children(wn1).isEmpty());

        // Try to add another edge from wn1 to wn2 in g1
        g1.addEdge(wn1, wn2);
        assertEquals(g1.children(wn1).size(), 1);
        assertTrue(g1.children(wn1).contains(wn2));
        assertEquals(g1.children(wn2).size(), 0);

        // Add an edge from wn1 to wn1 in g1
        g1.addEdge(wn1, wn1);
        assertEquals(g1.children(wn1).size(), 2);
        assertTrue(g1.children(wn1).contains(wn1));
        assertTrue(g1.children(wn1).contains(wn2));

        // Add an edge in the other direction between wn1 and wn2 in g1
        g1.addEdge(wn2, wn1);
        assertEquals(g1.children(wn2).size(), 1);
        assertTrue(g1.children(wn2).contains(wn1));

        // Add an edge from wn2 to wn2 in g2
        g2.addEdge(wn2, wn2);
        assertEquals(g2.children(wn2).size(), 1);
        assertTrue(g2.children(wn2).contains(wn2));
        assertFalse(g1.children(wn2).contains(wn2));
    }

    /*
     * Tests nodes(). This method is very similar to testAddNode(), because the
     * two methods depend on each other to verify their results.
     */
    public void testNodes() {
        Graph<WeightedNode> g1 = new Graph<WeightedNode>();
        Graph<WeightedNode> g2 = new Graph<WeightedNode>();
        WeightedNode wn1 = new WeightedNode("wn1", 1);
        WeightedNode wn2 = new WeightedNode("wn2", 3);

        // Test that the initial returned collection is not null and is empty
        assertNotNull(g1.nodes());
        assertTrue(g1.nodes().isEmpty());

        // Test that only wn1 is returned when added to g1
        g1.addNode(wn1);
        assertEquals(g1.nodes().size(), 1);
        assertTrue(g1.nodes().contains(wn1));

        // Test that g2 does not have wn1
        assertTrue(g2.nodes().isEmpty());

        // Test that only wn1 and wn2 are returned
        g1.addNode(wn2);
        assertEquals(g1.nodes().size(), 2);
        assertTrue(g1.nodes().contains(wn1));
        assertTrue(g1.nodes().contains(wn2));
    }

    public void testChildren() {
        Graph<WeightedNode> g1 = new Graph<WeightedNode>();
        Graph<WeightedNode> g2 = new Graph<WeightedNode>();
        WeightedNode wn1 = new WeightedNode("wn1", 1);
        WeightedNode wn2 = new WeightedNode("wn2", 3);

        // Test exceptions
        try {
            g1.children(null);
            fail();
        } catch (IllegalArgumentException e) {
            // Expected
        }
        try {
            g1.children(wn1);
            fail();
        } catch (IllegalArgumentException e) {
            // Expected
        }

        // Add wn1 and wn2 to g1 and g2
        g1.addNode(wn1);
        g1.addNode(wn2);
        g2.addNode(wn1);
        g2.addNode(wn2);

        // Test that the children of every node in g1 are not null but are empty
        assertNotNull(g1.children(wn1));
        assertNotNull(g1.children(wn2));
        assertTrue(g1.children(wn1).isEmpty());
        assertTrue(g1.children(wn2).isEmpty());

        // Add an edge from wn1 to wn2 in g1
        g1.addEdge(wn1, wn2);
        assertEquals(g1.children(wn1).size(), 1);
        assertTrue(g1.children(wn1).contains(wn2));
        assertEquals(g1.children(wn2).size(), 0);

        // Try to add the same edge to g1
        g1.addEdge(wn1, wn2);
        assertEquals(g1.children(wn1).size(), 1);
        assertTrue(g1.children(wn1).contains(wn2));
        assertEquals(g1.children(wn2).size(), 0);

        // Add an edge in the other direction in g1
        g1.addEdge(wn2, wn1);
        assertEquals(g1.children(wn1).size(), 1);
        assertTrue(g1.children(wn1).contains(wn2));
        assertEquals(g1.children(wn2).size(), 1);
        assertTrue(g1.children(wn2).contains(wn1));

        // Add an edge from wn1 to itself in g1
        g1.addEdge(wn1, wn1);
        assertEquals(g1.children(wn1).size(), 2);
        assertTrue(g1.children(wn1).contains(wn1));
        assertTrue(g1.children(wn1).contains(wn2));

        // Try to reinsert a node to g1 and verify that its children remain the
        // same, and that it remains as a children of nw2
        g1.addNode(wn1);
        assertEquals(g1.children(wn1).size(), 2);
        assertTrue(g1.children(wn1).contains(wn1));
        assertTrue(g1.children(wn1).contains(wn2));
        assertEquals(g1.children(wn2).size(), 1);
        assertTrue(g1.children(wn2).contains(wn1));

        // Test that g2 does not have the edges added to g1
        assertTrue(g2.children(wn1).isEmpty());
        assertTrue(g2.children(wn2).isEmpty());
    }
}