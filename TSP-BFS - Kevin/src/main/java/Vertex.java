import java.io.Serializable;
import java.util.Observable;

/**
 * Created by Kevin Worm on 22-9-2014.
 */
public class Vertex extends Observable implements Serializable {
    private static final long serialVersionUID = 7526472295622776147L;

    private String name;
    private boolean visited;
    private int locationX;
    private int locationY;
    private boolean mark;

    public Vertex(String _name, int _locationX, int _locationY)
    {
        name = _name;
        visited = false;
        locationX = _locationX;
        locationY = _locationY;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;

        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        setChanged();
        notifyObservers();
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;

        setChanged();
        notifyObservers();
    }

    public int getLocationX() {
        return locationX;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;

        setChanged();
        notifyObservers();
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;

        setChanged();
        notifyObservers();
    }
}
