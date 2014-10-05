package View;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Created by Kevin Worm on 4-10-2014.
 */
public class SudokuSubField extends JFormattedTextField  {

    private boolean locked;

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
        setEnabled(false);
        locked = false;
    }

    public void reset(){
        setText("");
        locked = false;
    }

    @Override
    public void setText(String text){
        if(!locked){
            super.setText(text);
        }
    }

    public void setLocked(){
        locked = true;
    }

    public void setColor(Color color){
        setDisabledTextColor(color);
    }
}
