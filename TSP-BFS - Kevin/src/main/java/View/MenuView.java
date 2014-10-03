package View;

import Controller.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin Worm on 3-10-2014.
 */
public class MenuView extends JPanel {

    JButton startButton;
    JButton cancelButton;
    JComboBox maxVertexList;
    ApplicationController appController;

    public MenuView(ApplicationController _appController){
        appController = _appController;
        setPreferredSize(new Dimension(200, 600));
        setBackground(Color.lightGray);

        startButton = new JButton("Start");
        startButton.addActionListener(appController);
        add(startButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(appController);
        add(cancelButton);

        String[] maxVertexes = new String[12];
        for(int i = 0; i < 12; i++){
            maxVertexes[i]= i+1 + "";
        }

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        maxVertexList = new JComboBox(maxVertexes);
        maxVertexList.setSelectedIndex(9);
        maxVertexList.addActionListener(appController);
        maxVertexList.setActionCommand("ComboBoxMaxVertexes");
        add(maxVertexList);
    }
}
