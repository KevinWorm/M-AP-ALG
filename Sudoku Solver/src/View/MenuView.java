package View;

import Controller.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin Worm on 4-10-2014.
 */
public class MenuView extends JPanel {
    ApplicationController appController;
    JButton startButton;

    public MenuView(ApplicationController _appController){
        setPreferredSize(new Dimension(200, 600));
        setBackground(Color.lightGray);
        appController = _appController;

        startButton = new JButton("Start");
        startButton.addActionListener(appController);
        add(startButton);
    }
}
