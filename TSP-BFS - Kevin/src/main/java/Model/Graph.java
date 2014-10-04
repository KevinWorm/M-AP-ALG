package Model;

import java.util.*;

/**
 * Created by Kevin Worm on 22-9-2014.
 */
public class Graph {
    Map<Vertex, LinkedList<Edge>> graph;

    public Graph()
    {
        graph = new HashMap<Vertex, LinkedList<Edge>>();
    }

    public void clear()
    {
        graph.clear();
    }

    public void addVertex(Vertex vertex)
    {
        if(!graph.containsKey(vertex)){
            graph.put(vertex, new LinkedList<Edge>());
        }
    }

    public Edge addEdge(Vertex vertex, Vertex nVertex)
    {
        Edge edge = new Edge(vertex, nVertex);
        if(graph.containsKey(vertex)){
            graph.get(vertex).add(edge);
        }

        return edge;
    }

    public Set<Vertex> getVertexes()
    {
        return graph.keySet();
    }

    public Collection<LinkedList<Edge>> getEdges(){
        return graph.values();
    }

    public LinkedList<Edge> getEdges(Vertex vertex)
    {
        return graph.get(vertex);
    }

    public Edge getEdge(Vertex vertex, Vertex nVertex)
    {
        for(Edge edge : graph.get(vertex)){
            if(edge.getNeighbor() == nVertex){
                return edge;
            }
        }
        System.out.println("DEBUG: no edge, vertex: "  + vertex.getName() + " to: " + nVertex);
        return null;
    }
}
