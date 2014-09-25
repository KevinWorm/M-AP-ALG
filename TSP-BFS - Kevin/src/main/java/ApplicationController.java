import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Kevin Worm on 24-9-2014.
 */
public class ApplicationController {

    private JFrame tspVisual;
    private JLayeredPane graphPane;
    private Graph graph;

    public ApplicationController(JFrame _jFrame){
        tspVisual = _jFrame;
        graphPane = new JLayeredPane();
        tspVisual.add(graphPane);
        graph  = new Graph();

        generateDemoGraph();

        //start thread for tsp solver
        TSPSolver tspThread = new TSPSolver(graph, "TSP Solver Thread");
        tspThread.start();
    }

    private void generateDemoGraph()
    {
        Vector<Vertex> vertexes = new Vector<Vertex>();

        vertexes.add(new Vertex("Nijmegen", 250, 750));
        vertexes.add(new Vertex("Beuningen", 650, 750));
        vertexes.add(new Vertex("Afferden", 150, 250));
        vertexes.add(new Vertex("Deest", 550, 250));
        vertexes.add(new Vertex("Groningen", 800, 475));

        graph.clear();
        for(Vertex vertex : vertexes){
            graph.addVertex(vertex);
            //add vertexes to view
            graphPane.add(new VisualVertex(vertex), (Integer) 30);
        }

        generateEdgesCompleteGraph(vertexes);
    }

    //Connect all vertexes with edges for a complete graph
    private void generateEdgesCompleteGraph(Vector<Vertex> vertexes)
    {
        Iterator<Vertex> it = vertexes.iterator();

        while(it.hasNext()){
            Vertex vertex = it.next();
            it.remove();

            for(Object o : vertexes){
                Edge edge = graph.addEdge(vertex, (Vertex) o);
                //add to view
                graphPane.add(new VisualEdge(vertex, edge), (Integer) 20);
            }
        }
    }
}
