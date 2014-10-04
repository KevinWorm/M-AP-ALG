package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Kevin Worm on 24-9-2014.
 */
public class ApplicationController implements ActionListener {

    private JFrame mainView;
    private GraphView graphView;
    private MenuView menuView;
    private Graph graph;
    private TravelingSalesmanSolver tspSolver;
    private int maxVertexes = 10;
    private String selectedAlgorithm;

    public ApplicationController(JFrame _jFrame){
        mainView = _jFrame;
        graphView = new GraphView();
        menuView = new MenuView(this);

        mainView.getContentPane().add(graphView, BorderLayout.LINE_START);
        mainView.getContentPane().add(menuView, BorderLayout.LINE_END);

        selectedAlgorithm = "Brute Force";
        graph  = new Graph();

        init();
    }

    private void init(){
        generateDemoGraph();

        if("Brute Force".equals(selectedAlgorithm)){
            tspSolver = new TSPBruteForce(graph, "TSP Brute force thread");
        }
        else if("Greedy".equals(selectedAlgorithm)){
            tspSolver = new TSPGreedy(graph, "TSP Greedy thread");
        }
        tspSolver.addObserver(graphView);
    }


    private void startTSPSolver(){
        //start thread for tsp solver
        tspSolver.start();
    }

    private void reset(){
        tspSolver.requestStop();
        while(tspSolver.isAlive()){
        }
        graphView.reset();
        init();
    }

    private void generateDemoGraph()
    {
        Vector<Vertex> vertexes = new Vector<Vertex>();

        vertexes.add(new Vertex("Nijmegen", 50, 100));
        vertexes.add(new Vertex("Druten", 250, 100));
        vertexes.add(new Vertex("Beuningen", 450, 100));
        vertexes.add(new Vertex("Ewijk", 250, 200));
        vertexes.add(new Vertex("Deest", 50, 300));
        vertexes.add(new Vertex("Leeuwen", 150, 300));
        vertexes.add(new Vertex("Elst", 350, 300));
        vertexes.add(new Vertex("Dreumel", 450, 300));
        vertexes.add(new Vertex("Horssen", 50, 500));
        vertexes.add(new Vertex("Lent", 250, 500));
        vertexes.add(new Vertex("Arnhem", 450, 500));
        vertexes.add(new Vertex("Rotterdam", 250, 400));
        vertexes.add(new Vertex("Midden", 250, 300));

        //remove vertexes if max is set
        if(vertexes.size() > maxVertexes){
            int delete = vertexes.size() - maxVertexes;
            for(int i = 0; i < delete; i++){
                vertexes.remove(vertexes.lastElement());
            }
        }

        graph.clear();
        for(Vertex vertex : vertexes){
            graph.addVertex(vertex);
            //add vertexes to view
            graphView.add(new VisualVertex(vertex), (Integer) 30);
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
                Edge edgeOpposite = graph.addEdge((Vertex) o, vertex);
                //add to view
                graphView.add(new VisualEdge(edge, edgeOpposite), (Integer) 20);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if(action.equals("Start")){
            startTSPSolver();

            //change button
            JButton jb = (JButton)e.getSource();
            jb.setText("Cancel");

            menuView.setMaxVertexListEnabled(false);
            menuView.setTSPAlgorithmListEnabled(false);
        }
        else if(action.equals("Cancel")){
            reset();

            //change button
            JButton jb = (JButton)e.getSource();
            jb.setText("Start");

            menuView.setMaxVertexListEnabled(true);
            menuView.setTSPAlgorithmListEnabled(true);
        }
        else if(action.equals("ComboBoxMaxVertexes")){
            JComboBox cb = (JComboBox)e.getSource();
            maxVertexes = Integer.parseInt((String)cb.getSelectedItem());
            reset();
        }
        else if(action.equals("ComboBoxTSPAlgorithm")){
            JComboBox cb = (JComboBox)e.getSource();
            selectedAlgorithm = (String)cb.getSelectedItem();
            reset();
        }
    }
}
