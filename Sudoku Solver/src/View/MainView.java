package View;

import javax.swing.*;

/**
 * Created by Kevin Worm on 4-10-2014.
 */
public class MainView extends JFrame {
    public MainView(){
        setTitle("Sudoku Solver");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }
}
