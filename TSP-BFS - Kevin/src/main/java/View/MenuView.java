package View;

import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin Worm on 3-10-2014.
 */
public class MenuView extends JPanel{

    JButton startButton;
    JButton cancelButton;
    JComboBox maxVertexList;
    JComboBox TSPAlgorithm;
    ApplicationController appController;

    public MenuView(ApplicationController _appController) {
        appController = _appController;
        setPreferredSize(new Dimension(200, 600));
        setBackground(Color.lightGray);

        startButton = new JButton("Start");
        startButton.addActionListener(appController);
        add(startButton);

        String[] maxVertexes = new String[12];
        for(int i = 1; i < 13; i++){
            maxVertexes[i-1]= i+1 + "";
        }

        //Create the combo box for selecting the max vertexes
        maxVertexList = new JComboBox(maxVertexes);
        maxVertexList.setSelectedIndex(8);
        maxVertexList.addActionListener(appController);
        maxVertexList.setActionCommand("ComboBoxMaxVertexes");
        add(maxVertexList);

        String[] TSPAlgorithms = new String[]{"Brute Force","Greedy"};
        //Create the combo box for selecting the TSP Algorithm
        TSPAlgorithm = new JComboBox(TSPAlgorithms);
        TSPAlgorithm.setSelectedIndex(0);
        TSPAlgorithm.addActionListener(appController);
        TSPAlgorithm.setActionCommand("ComboBoxTSPAlgorithm");
        add(TSPAlgorithm);
    }

    public void setMaxVertexListEnabled(boolean enabled){
        maxVertexList.setEnabled(enabled);
    }

    public void setTSPAlgorithmListEnabled(boolean enabled){
        TSPAlgorithm.setEnabled(enabled);
    }
}
