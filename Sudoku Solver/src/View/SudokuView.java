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

    public void setField(int row, int column, int value, Color color){
        int blockY = row / 3;
        int blockX = column / 3;

        if(value > 9){
            System.out.println("Error value is higher than 9, value is: " + value);
        }

        sudokuBlocks[blockY][blockX].setField(row, column, value);
        sudokuBlocks[blockY][blockX].setFieldEnabled(row, column, false);
        sudokuBlocks[blockY][blockX].setColor(row, column, color);
    }

    public void setMatrix(Integer[][] field, Color color){
        for(int i=0; i < 9; i++){
            for(int j=0; j < 9; j++){
                if(field[i][j] != null){
                    setField(i,j,field[i][j], color);
                }
            }
        }
    }
}
