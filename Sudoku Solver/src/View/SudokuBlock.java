package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin Worm on 4-10-2014.
 */
public class SudokuBlock extends JPanel {

    SudokuSubField[][] sudokuSubFields;

    public SudokuBlock(){
        super(new GridLayout(3,3));
        setPreferredSize(new Dimension(200, 200));
        setBorder(BorderFactory.createLineBorder(Color.black));

        sudokuSubFields = new SudokuSubField[3][3];

        init();
    }

    private void init(){
        //Add Sudoku blocks
        for(int i=0; i < 3; i++){
            for(int j=0; j < 3; j++){
                sudokuSubFields[i][j] = new SudokuSubField();
                add(sudokuSubFields[i][j]);
            }
        }
    }

    public void reset(){
        for(int i=0; i < 3; i++){
            for(int j=0; j < 3; j++){
                sudokuSubFields[i][j].reset();
            }
        }
    }

    public SudokuSubField getField(int row, int column){
        int fieldX = column % 3;
        int fieldY = row % 3;

        return sudokuSubFields[fieldY][fieldX];
    }
}
