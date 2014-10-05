package View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin Worm on 4-10-2014.
 */
public class SudokuView extends JPanel {
    SudokuBlock[][] sudokuBlocks;

    public SudokuView(){
        super(new GridLayout(3,3));
        setPreferredSize(new Dimension(600, 600));
        setBorder(BorderFactory.createLineBorder(Color.black));

        sudokuBlocks = new SudokuBlock[3][3];

        init();
    }

    private void init(){
        //Add Sudoku blocks
        for(int i=0; i < 3; i++){
            for(int j=0; j < 3; j++){
                sudokuBlocks[i][j] = new SudokuBlock();
                add(sudokuBlocks[i][j]);
            }
        }
    }

    public void reset(){
        for(int i=0; i < 3; i++){
            for(int j=0; j < 3; j++){
                sudokuBlocks[i][j].reset();
            }
        }
    }

    public SudokuSubField getSubField(int row, int column){
        int blockY = row / 3;
        int blockX = column / 3;

        return sudokuBlocks[blockY][blockX].getField(row, column);
    }

    public void setTextField(int row, int column, Integer value, Color color){
        setTextField(row, column, value, color, false);
    }

    public void setTextField(int row, int column, Integer value, Color color, boolean puzzle){
        SudokuSubField subField = getSubField(row,column);
        //set value to field, if its null set empty string
        subField.setText(((value != null) ? value : "") + "");
        subField.setColor(color);
        //only if value is not null and puzzle is true, lock sub field
        if(value != null && puzzle){
            subField.setLocked();
        }
    }

    public void setMatrix(Integer[][] field, Color color) {
        setMatrix(field, color, false);
    }

    public void setMatrix(Integer[][] field, Color color, boolean puzzle){
        for(int i=0; i < 9; i++){
            for(int j=0; j < 9; j++){
                setTextField(i,j,field[i][j], color, puzzle);
            }
        }
    }
}
