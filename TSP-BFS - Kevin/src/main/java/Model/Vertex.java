package Model;

import java.awt.*;
import java.io.Serializable;
import java.util.Observable;

/**
 * Created by Kevin Worm on 22-9-2014.
 */
public class Vertex extends Observable {
    private String name;
    private boolean visited;
    private int locationX;
    private int locationY;
    private boolean mark;

    public Vertex(String _name, int _locationX, int _locationY)
    {
        name = _name;
        locationX = _locationX;
        locationY = _locationY;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;

        setChanged();
        update();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        setChanged();
        update();
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;

        setChanged();
        update();
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;

        setChanged();
        update();
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
