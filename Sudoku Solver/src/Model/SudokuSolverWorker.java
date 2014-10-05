package Model;

import View.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kevin Worm on 4-10-2014.
 */
public class SudokuSolverWorker extends SwingWorker<Integer[][], Integer[][]> {

    private SudokuView sudokuView;
    private Integer[][] sudoku;
    private Integer[][] solution;

    public SudokuSolverWorker(SudokuView _sudokuView, Integer[][] _sudoku){
        sudokuView = _sudokuView;
        sudoku = _sudoku;
        solution = new Integer[9][9];
    }

    @Override
    protected Integer[][] doInBackground() throws Exception {
        setProgress(0);
        System.out.println("Start Sudoku Backtrack worker.");

        sudokuBacktrack(0,0);

        System.out.println("Sudoku Backtrack worker is done.");
        setProgress(100);
        return null;
    }

    //recursive function, return true when finished, when false try next solution (backtrack)
    private boolean sudokuBacktrack(int currentRow, int currentColumn){

        //get values that aren't used yet in the current row or column or 3x3 grid
        Integer[] unusedNumbers = getUnusedNumbers(currentRow, currentColumn);

        //if there are no more possible values return false - backtrack
        if(unusedNumbers.length == 0) return false;

        for(int i=0; i < unusedNumbers.length; i++){
            //if worker is canceled return
            if(isCancelled())return true;

            //fill current sub field with a available value
            solution[currentRow][currentColumn] = unusedNumbers[i];
            //publish current solution
            publish(solution);

            //slow down for visual
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int row = currentRow, column = currentColumn;
            //move to first empty field
            while(row < 9){
                column++;
                //if last column, move to next row
                if(column == 9) column = 0; row++;
                //end of field, solution found return
                if(row == 9)return true;

                //if field is empty, use field
                if(sudoku[row][column] == null){
                    //recursive function, if return is true, solution is found or worker is canceled
                    if(sudokuBacktrack(row, column))return true;
                    //break and try next value
                    break;
                }
            }
        }

        //clear current sub field
        solution[currentRow][currentColumn] = null;
        //backtrack
        return false;
    }

    //return numbers thar are not used in the current row or column or 3x3 grid
    private Integer[] getUnusedNumbers(int row, int column){
        HashSet<Integer> unusedNumbers = new HashSet<Integer>();
        for(int i=1; i <= 9; i++){
            unusedNumbers.add(i);
        }

        //remove all numbers from the set if they are present in the current row or column
        for(int i=0; i<9; i++){
            //check sudoku
            unusedNumbers.remove(sudoku[row][i]);
            unusedNumbers.remove(sudoku[i][column]);
            //check solution
            unusedNumbers.remove(solution[row][i]);
            unusedNumbers.remove(solution[i][column]);
        }

        //calculate 3x3 grid position top left
        int grid3x3row = (row/3)*3;
        int grid3x3column = (column/3)*3;

        //remove all numbers from the set that are in the 3x3 grid
        for(int i=grid3x3row; i<grid3x3row+3; i++){
            for(int j=grid3x3column; j<grid3x3column+3; j++){
                unusedNumbers.remove(sudoku[i][j]);
                unusedNumbers.remove(solution[i][j]);
            }
        }
        return unusedNumbers.toArray(new Integer[0]);
    }

    @Override
    protected void process(List<Integer[][]> chunks){
        Integer[][] lastElement = null;
        if (!chunks.isEmpty() && chunks.size() > 0) {
            lastElement = chunks.get(chunks.size() - 1);
        }
        sudokuView.setMatrix(lastElement, Color.RED);
        print();
    }

    //Print sudoku field with solution in console
    private void print(){
        System.out.println();
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print((sudoku[i][j] != null) ? sudoku[i][j] : "-");
                System.out.print((solution[i][j] != null) ? solution[i][j] : "-");
                System.out.print(", ");
                if(j==8){
                    System.out.println();
                }
            }
        }
    }
}
