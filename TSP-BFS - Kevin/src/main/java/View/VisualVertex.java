package View;

import Model.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;

/**
 * Created by Kevin Worm on 24-9-2014.
 */
public class VisualVertex extends JComponent implements Observer{

    private Vertex vertex;
    private int positionX;
    private int positionY;
    private int width;
    private int height;

    public VisualVertex(Vertex _vertex){
        vertex = _vertex;
        vertex.addObserver(this);

        width = 30;
        height = 30;

        positionX = vertex.getLocationX() - (width/2);
        positionY = vertex.getLocationY() - (height/2);

        this.setSize(new Dimension(300, 300));
        this.setLocation(positionX, positionY);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.orange);

        g.fillOval(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Dialog", Font.PLAIN, 15));
        g.drawString(vertex.getName(), 35, 35);

        //g.fill3DRect(0, 0, width, height, true);
        //
        //g.draw3DRect(0, 0, width, height, true);
    }

    public void update(Observable obs, Object obj)
    {
        if (obs == vertex)
        {
            repaint();
        }
    }
}
