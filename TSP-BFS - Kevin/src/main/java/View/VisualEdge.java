package View;

import Model.Edge;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Kevin Worm on 24-9-2014.
 */
public class VisualEdge extends JComponent implements Observer {

    private Edge edge;
    private Edge edgeOpposite;

    public VisualEdge(Edge _edge, Edge _edgeOpposite)
    {
        edgeOpposite = _edgeOpposite;
        edge = _edge;
        edge.addObserver(this);
        edgeOpposite.addObserver(this);

        this.setSize(new Dimension(1000, 1000));
        this.setLocation(0, 0);
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        if(edge.isMark()){
            g.setColor(Color.RED);
            g2.setStroke(new BasicStroke(3));
            g2.draw(new Line2D.Float(
                    edge.getNeighbor().getLocationX(),
                    edge.getNeighbor().getLocationY(),
                    edgeOpposite.getNeighbor().getLocationX(),
                    edgeOpposite.getNeighbor().getLocationY()));
        }
        else{
            //g.setColor(Color.BLACK);
            //g2.setStroke(new BasicStroke(1));
        }

    }

    public void update(Observable obs, Object obj)
    {
        if (obs == edge)
        {
            repaint();
        }
    }
}
