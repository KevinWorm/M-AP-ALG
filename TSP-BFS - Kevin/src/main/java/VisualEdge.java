import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Kevin Worm on 24-9-2014.
 */
public class VisualEdge extends JComponent implements Observer {

    private Vertex fromVertex;
    private Edge edge;

    public VisualEdge(Vertex _fromVertex, Edge _edge)
    {
        fromVertex = _fromVertex;
        edge = _edge;
        edge.addObserver(this);

        this.setSize(new Dimension(1000, 1000));
        this.setLocation(0, 0);
    }

    public void paint(Graphics g)
    {
        if(edge.isMark()){
            g.setColor(Color.blue);
        }
        else{
            g.setColor(Color.BLACK);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.draw(new Line2D.Float(fromVertex.getLocationX(),fromVertex.getLocationY(),edge.getNeighbor().getLocationX(),edge.getNeighbor().getLocationY()));
    }

    public void update(Observable obs, Object obj)
    {
        if (obs == edge)
        {
            repaint();
        }
    }
}
