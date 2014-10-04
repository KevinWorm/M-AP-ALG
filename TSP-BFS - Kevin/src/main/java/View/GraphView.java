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
    private JLabel routeCost;

    public GraphView(){
        setPreferredSize(new Dimension(600, 600));
        //Counter
        counter = new JLabel();
        counter.setSize(new Dimension(500,100));
        counter.setLocation(25,0);

        //route cost
        routeCost = new JLabel();
        routeCost.setSize(new Dimension(500,100));
        routeCost.setLocation(200,0);

        init();
    }

    private void init(){
        counter.setText("Total routes: 0");
        this.add(counter, 40);

        routeCost.setText("Cheapest route cost: 0");
        this.add(routeCost, 40);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof TravelingSalesmanSolver){
            counter.setText("Total routes: " + ((TravelingSalesmanSolver) o).getTotalRoutesCalculated());
            routeCost.setText("Cheapest route cost: " + ((TravelingSalesmanSolver) o).getCheapestRouteCost());
        }
    }

    public void reset(){
        removeAll();
        init();
    }
}
