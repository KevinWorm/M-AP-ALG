import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Kevin Worm on 25-9-2014.
 */
public class TSPSolver implements Runnable{
    private Graph graph;
    private Vertex start;
    private String threadName;
    private Thread t;

    private List<Vertex> cheapestRoute;
    private double cheapestRouteCost;

    public TSPSolver(Graph _graph, String name)
    {
        graph = _graph;
        start = graph.getVertexes().iterator().next();
        cheapestRouteCost = 0;
        cheapestRoute = null;
        threadName = name;
    }

    public void run() {
        System.out.println("Running " +  threadName );
        bfs();
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start ()
    {
        System.out.println("Starting " +  threadName );
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    public void bfs()
    {
        Queue searchQueue = new LinkedList();
        //add first vertex to queue
        searchQueue.add(start);
        start.setVisited(true);

        bfs(searchQueue, new ArrayList<Vertex>());
    }

    private void bfs(Queue _searchQueue, List<Vertex> _result) {
        boolean startVertex = false;

        Queue searchQueue = (LinkedList) ((LinkedList) _searchQueue).clone();
        List<Vertex> result = (ArrayList<Vertex>) ((ArrayList<Vertex>) _result).clone();

        while (searchQueue.size() != 0) {

            //Get first Vertex and remove it from the queue
            Vertex current = (Vertex) searchQueue.remove();
            //add current vertex to result list
            result.add(current);
            //get edges connected to the vertex
            LinkedList<Edge> edges = graph.getEdges(current);
            //get all neighbour vertexes
            for (Edge edge : edges) {
                if (!edge.getNeighbor().isVisited()) {
                    //if vertex isn't visited before, add to traversal queue
                    searchQueue.add(edge.getNeighbor());
                    edge.getNeighbor().setVisited(true);
                    bfs(searchQueue, result);
                } else {
                    if (edge.getNeighbor() == start) {
                        startVertex = true;
                    }
                }
            }
        }
        //if there are no more vertexes left and one of the neighbours is the start vertex, successful route is found.
        if (startVertex) {
            result.add(start);
            calculateRouteCost(result);
        }
    }

    private void calculateRouteCost(List<Vertex> result)
    {
        double total = 0;
        for(int i = 0; i < result.size()-1; i++){
            total += graph.getEdgeWeight(result.get(i),result.get(i+1));
        }

        if(total < cheapestRouteCost || cheapestRouteCost == 0){
            cheapestRouteCost = total;
            cheapestRoute = result;
            System.out.println("Er is een betere route gevonden! : "  + total);

            markRoute(result);
        }
        else{
            System.out.println("Er is een route gevonden.. : " + total);
        }
    }

    private void markRoute(List<Vertex> route)
    {
        //clear mark
        if(cheapestRoute != null){
            for(int i = 0; i < cheapestRoute.size()-1; i++){
                graph.getEdge(cheapestRoute.get(i),cheapestRoute.get(i+1)).setMark(false);
            }
            System.out.print("Route: ");
            for(Vertex vertex : cheapestRoute){
                vertex.setMark(false);
                System.out.print(vertex.getName() + " - ");
            }
        }

        //set mark
        if(route != null){
            for(int i = 0; i < route.size()-1; i++){
                graph.getEdge(route.get(i),route.get(i+1)).setMark(true);
            }
            for(Vertex vertex : route){
                vertex.setMark(true);
            }
        }
    }
}
