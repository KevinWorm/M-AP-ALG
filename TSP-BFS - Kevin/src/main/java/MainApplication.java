import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin Worm on 24-9-2014.
 */
public class MainApplication {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Open new window
                JFrame tspVisual = new TSPVisual();
                tspVisual.setVisible(true);

                ApplicationController appController = new ApplicationController(tspVisual);
            }
        });
    }
}
