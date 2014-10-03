package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Kevin Worm on 3-10-2014.
 */
public class GraphView extends JLayeredPane implements Observer {
    private JLabel counter;

    public GraphView(){
        setPreferredSize(new Dimension(600, 600));
        //Counter
        counter = new JLabel();
        counter.setText("Total routes: 0");
        counter.setSize(new Dimension(500,100));
        counter.setLocation(25,0);
        this.add(counter, 40);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof TravelingSalesmanSolver){
            counter.setText("Total routes: " + ((TravelingSalesmanSolver) o).getTotalRoutesCalculated());
        }
    }

    public void reset(){
        removeAll();
        counter.setText("Total routes: 0");
        this.add(counter, 40);
    }
}
