package View;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Created by Kevin Worm on 4-10-2014.
 */
public class SudokuSubField extends JFormattedTextField  {

    public SudokuSubField(){
        init();
    }

    public SudokuSubField(int value){
        super(value);
        init();
    }

    private void init(){
        setFont(new Font("Dialog", Font.PLAIN, 25));
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setColor(Color color){
        setDisabledTextColor(color);
    }
}
