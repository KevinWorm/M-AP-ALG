package Controller;

import View.MainView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin Worm on 4-10-2014.
 */
public class MainApplication {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Open new window
                MainView mainView = new MainView();
                mainView.setVisible(true);

                new ApplicationController(mainView);
            }
        });
    }
}
