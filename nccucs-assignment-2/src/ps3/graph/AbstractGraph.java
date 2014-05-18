package ps3.graph;
import java.util.Collection;

/**
 * AbstractGraph models a directed, node-weighted graph. The graphs that
 * implement this interface are mutable.
 *
 * @param <N> the type of nodes in this graph
 */
public interface AbstractGraph<N> {

    /**
     * Returns a collection of the children of the specified node.
     *
     * @param node the node whose children will be returned
     * @return a collection of the children of the specified node
     * @throws IllegalArgumentException if the specified node is null or is not in the graph
     * @modifies none
     */
    public Collection<N> children(N node) throws IllegalArgumentException;

    /**
     * Returns a collection of all nodes in this graph.
     *
     * @return a collection of all nodes in this graph
     * @modifies none
     */
    public Collection<N> nodes();

    /**
     * Adds a node to this graph. If the node is already in the graph, this
     * method has no effect.
     *
     * @param node
     *            the node that will be added to this graph
     * @throws IllegalArgumentException
     *             if the specified node is null
     * @modifies this
     */
    public void addNode(N node) throws IllegalArgumentException;

    /**
     * Adds an edge from the specified parent node to the specified child node.
     * If the edge to be added is already in the graph, this method has no
     * effect.
     *
     * @param parent
     *            the node from which the edge will be directed
     * @param child
     *            the node to which the edge will be directed
     * @throws IllegalArgumentException
     *             if any of the specified nodes is null or is not in the graph
     * @modifies this
     */
    public void addEdge(N parent, N child) throws IllegalArgumentException;

}