package Model;

import java.awt.*;
import java.util.Observable;

/**
 * Created by Kevin Worm on 3-10-2014.
 */
public abstract class TravelingSalesmanSolver extends Observable implements Runnable {

    protected Graph graph;
    private Thread thread;
    private String threadName;
    private boolean fIsStopRequested;
    private int totalRoutesCalculated;
    private double cheapestRouteCost;

    //Limit update of the view to every ... milli seconds
    int minMillisecondsBetweenUpdate = 100;
    long lastUpdateTime;

    public TravelingSalesmanSolver(Graph _graph, String _threadName){
        threadName = _threadName;
        graph = _graph;
        totalRoutesCalculated = 0;
        cheapestRouteCost = 0;
        lastUpdateTime = System.currentTimeMillis();
    }

    public void run(){
        System.out.println("Running " +  threadName );
        solve();
        //force update for last value
        update(true);
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start(){
        System.out.println("Starting " +  threadName );
        if (thread == null)
        {
            thread = new Thread (this, threadName);
            thread.start ();
        }
    }

    protected abstract void solve();

    public boolean isAlive(){
        if (thread == null) {
            return false;
        }
        return thread.isAlive();
    }

    // Request that this thread stop running.
    public synchronized void requestStop(){
        fIsStopRequested = true;
    }

    protected synchronized boolean isStopRequested() {
        return fIsStopRequested;
    }

    public synchronized int getTotalRoutesCalculated() {
        return totalRoutesCalculated;
    }

    protected synchronized void increaseTotalRoutesCalculated(int total) {
        totalRoutesCalculated += total;
        setChanged();
        update();
    }

    public synchronized double getCheapestRouteCost(){
        return cheapestRouteCost;
    }

    protected synchronized void setCheapestRouteCost(double routeCost) {
        cheapestRouteCost = routeCost;
        setChanged();
        update();
    }

    //default
    private void update(){
        update(false);
    }

    private void update(boolean force){
        //if last update is more than minMillisecondsBetweenUpdate update or if force is set true;
        if(System.currentTimeMillis() - lastUpdateTime > minMillisecondsBetweenUpdate || force){
            lastUpdateTime = System.currentTimeMillis();
            // Notify the observers on the EDT.
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    notifyObservers();
                }
            });
        }
    }
}
