package Model;

import java.awt.*;
import java.util.Observable;

/**
 * Created by Kevin Worm on 22-9-2014.
 */
public class Edge extends Observable {
    private Vertex neighbor;
    private boolean mark;

    public Edge(Vertex _neighbor)
    {
        neighbor = _neighbor;
    }

    public double getWeight(Vertex root) {
        //calculate euclidean distance between vertexes
        return Math.sqrt(Math.pow((neighbor.getLocationX() - root.getLocationX()),2) +
                Math.pow((neighbor.getLocationY() - root.getLocationY()),2));
    }

    public Vertex getNeighbor() {
        return neighbor;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        if(this.mark != mark){
            this.mark = mark;

            setChanged();
            update();
        }
    }

    private void update(){
        // Notify the observers on the EDT.
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                notifyObservers();
            }
        });
    }
}
