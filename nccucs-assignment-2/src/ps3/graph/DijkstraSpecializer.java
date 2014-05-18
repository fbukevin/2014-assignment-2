package ps3.graph;

/**
 * DijkstraSpecializer implements the PathFinderSpecializer interface in order
 * to encapsulate the behavior of Dijkstra's shortest path algorithm on graphs
 * that implement the AbstractGraph interface.
 *
 * @param <N> the type of nodes in the graph passed as an argument
 */
public class DijkstraSpecializer<N> implements PathFinderSpecializer<N> {

    private AbstractGraph<N> graph;

    /**
     * Creates a new DijkstraSpeciaizer on the specified graph.
     *
     * @param graph the graph that the algorithm works on
     * @effects creates a DijkstraSpecializer on the specified graph
     */
    public DijkstraSpecializer(AbstractGraph<N> graph) {
        this.graph = graph;
    }


	/* Specified in PathFinderSpecializer interface */
    public Iterable<N> expandNode(N node) {
        return graph.children(node);
    }

    /* Specified in Comparator interface */
    public int compare(Path<N> o1, Path<N> o2) {
        if (o1.cost() > o2.cost())
            return 1;
        if (o1.cost() < o2.cost())
            return -1;
        return 0;
    }

}


