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
        Edge edge = new Edge(nVertex);
        if(graph.containsKey(vertex)){
            graph.get(vertex).add(edge);
        }

        Edge edge2 = new Edge(vertex);
        if(graph.containsKey(nVertex)){
            graph.get(nVertex).add(edge2);
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

        return null;
    }

    public double getEdgeWeight(Vertex vertex, Vertex nVertex)
    {
        double weight = 0;

        for(Edge edge : graph.get(vertex)){
            if(edge.getNeighbor() == nVertex){
                weight = edge.getWeight(vertex);
                break;
            }
        }

        return weight;
    }
}
