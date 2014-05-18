package ps3.graph;

import java.util.*;
public class Graph<N> implements AbstractGraph<N>
{

    /**
     * A HashMap which has the nodes of the graph as keys and a HashSet
     * containing its children as values.
     */
    private HashMap<N, HashSet<N>> nodeMap;

    /* Empty constructor of class. */
    public Graph() 
    {
        nodeMap = new HashMap<N, HashSet<N>>();
        checkRep();
    }

    /* Specified in AbstractGraph interface. */ 
    public void addEdge(N parent, N child) 
    {
        if (parent == null || child == null || !nodeMap.containsKey(parent) || !nodeMap.containsKey(child))
        {
            throw new IllegalArgumentException();
        }
        nodeMap.get(parent).add(child);
        checkRep();
    }

    /* Specified in AbstractGraph interface */
    public void addNode(N node) 
    {
        if (node == null)
        {
            throw new IllegalArgumentException();
        }
        if (!nodeMap.containsKey(node)) 
        {
            nodeMap.put(node, new HashSet<N>());
            checkRep();
        }
    }

    /* Specified in AbstractGraph interface. */
    public HashSet<N> children(N node) 
    {
        if (!nodeMap.containsKey(node))
        {
            throw new IllegalArgumentException();
        }
        HashSet<N> children_ = new HashSet<N>();
        children_.addAll(nodeMap.get(node));
        return children_;
    }

    /* Specified in AbstractGraph interface. */
    public HashSet<N> nodes() 
    {
        HashSet<N> nodes_ = new HashSet<N>();
        nodes_.addAll(nodeMap.keySet());
        return nodes_;
    }

    /* Checks that the representation invariant holds. */
    private void checkRep() 
    {
        if (nodeMap == null)
        {
            throw new RuntimeException("The node map is null.");
        }
        Iterator<N> iter = nodeMap.keySet().iterator();
        while (iter.hasNext()) 
        {
            N node = iter.next();
            if (node == null)
            {   
                throw new RuntimeException("A node is null.");
            }
            if (nodeMap.get(node) == null)
            {
                throw new RuntimeException("Children set for a node is null.");
            }
            Iterator<N> childIterator = nodeMap.get(node).iterator();
            while (childIterator.hasNext()) 
            {
                N child_ = childIterator.next();
                if (child_ == null)
                {
                    throw new RuntimeException("A child of a node is null.");
                }
                if (!nodeMap.containsKey(child_))
                {
                    throw new RuntimeException("One child of a node is not a node of the graph.");
                }
            }
        }
    }

}