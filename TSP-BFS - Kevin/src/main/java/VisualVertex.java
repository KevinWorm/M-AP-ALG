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

        width = 150;
        height = 50;

        positionX = vertex.getLocationX() - (width/2);
        positionY = vertex.getLocationY() - (height/2);

        this.setSize(new Dimension(width+1, height+1));
        this.setLocation(positionX, positionY);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        if(vertex.isMark()){
            g.setColor(Color.LIGHT_GRAY);
        }
        else{
            g.setColor(Color.orange);
        }

        g.fill3DRect(0, 0, width, height, true);
        g.setColor(Color.BLACK);
        g.draw3DRect(0, 0, width, height, true);
        g.drawString("Name: " + vertex.getName(), 10, 20);
        g.drawString("X: " + vertex.getLocationX() + " Y: " + vertex.getLocationY(), 10, 40);
    }

    public void update(Observable obs, Object obj)
    {
        if (obs == vertex)
        {
            repaint();
        }
    }
}
