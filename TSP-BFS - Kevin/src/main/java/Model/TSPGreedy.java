package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Kevin Worm on 3-10-2014.
 */
public class TSPGreedy extends TravelingSalesmanSolver {

    private Vertex start;
    private List<Vertex> cheapestRoute;

    public TSPGreedy(Graph _graph, String _threadName){
        super(_graph, _threadName);

        start = graph.getVertexes().iterator().next();
    }

    @Override
    protected void solve() {
        for(Vertex vertex : graph.getVertexes()){
            start = vertex;
            greedy(start, new ArrayList<Vertex>());
        }
    }

    private void greedy(Vertex vertex, List<Vertex> _result){
        boolean startVertex = false;
        //add to result list
        _result.add(vertex);

        //get edges connected to the vertex
        PriorityQueue<Edge> pQueueEdges = new PriorityQueue<Edge>(graph.getEdges(vertex));

        while(pQueueEdges.size() > 0){
            Edge edge = pQueueEdges.poll();
            if(!_result.contains(edge.getNeighbor())){
                greedy(edge.getNeighbor(), _result);
                //return if thread stop is requested.
                if(isStopRequested())return;
            }
            else if(edge.getNeighbor() == start){
                startVertex = true;
            }
        }

        //if there are no more vertexes left and one of the neighbours is the start vertex, successful route is found.
        if(_result.size() == graph.getVertexes().size() && startVertex){
            //increase total
            increaseTotalRoutesCalculated(1);

            _result.add(start);
            calculateRouteCost(_result);
        }
    }

    private void calculateRouteCost(List<Vertex> result)
    {
        double total = 0;
        for(int i = 0; i < result.size()-1; i++){
            Edge edge = graph.getEdge(result.get(i),result.get(i+1));
            if(edge != null){
                total += edge.getWeight();
            }
        }

        if(total < getCheapestRouteCost() || getCheapestRouteCost() == 0){
            setCheapestRouteCost(total);
            cheapestRoute = result;
            System.out.println("Er is een betere route gevonden! : "  + total);

            markRoute(result);

            System.out.print("Route: ");
            int id = 0;
            for(Vertex vertex : result){
                id++;
                System.out.print("ID:" + id + " " + vertex.getName() + " - ");
            }
            System.out.println();
        }
    }

    private void markRoute(List<Vertex> route)
    {
        //clear mark
        for(LinkedList<Edge> edges : graph.getEdges()){
            for(Edge edge : edges){
                edge.setMark(false);
            }
        }

        //set mark
        if(route != null){
            for(int i = 0; i < route.size()-1; i++){
                Edge edge = graph.getEdge(route.get(i),route.get(i+1));
                Edge edgeOpposite = graph.getEdge(route.get(i+1), route.get(i));
                if(edge != null){
                    edge.setMark(true);
                    edgeOpposite.setMark(true);

                    //slow down for visual
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
