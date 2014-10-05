package Controller;

import Model.SudokuSolverWorker;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin Worm on 4-10-2014.
 */
public class ApplicationController implements ActionListener {
    private JFrame mainView;
    private MenuView menuView;
    private SudokuView sudokuView;
    private SudokuSolverWorker sudokuSolverWorker;
    private Integer[][] sudokuMatrix;

    public ApplicationController(MainView _mainView) {
        mainView = _mainView;
        sudokuView = new SudokuView();
        menuView = new MenuView(this);

        mainView.getContentPane().add(sudokuView, BorderLayout.LINE_START);
        mainView.getContentPane().add(menuView, BorderLayout.LINE_END);

        sudokuMatrix = new Integer[][]{
                {null,null,5,null,9,null,null,null,1},
                {null,null,null,null,null,2,null,7,3},
                {7,6,null,null,null,8,2,null,null},
                {null,1,2,null,null,9,null,null,4},
                {null,null,null,2,null,3,null,null,null},
                {3,null,null,1,null,null,9,6,null},
                {null,null,1,9,null,null,null,5,8},
                {9,7,null,5,null,null,null,null,null},
                {5,null,null,null,3,null,7,null,null}};

        sudokuView.setMatrix(sudokuMatrix, Color.blue);
    }

    private void startSudokuSolver(){
        sudokuSolverWorker = new SudokuSolverWorker(sudokuView, sudokuMatrix);
        sudokuSolverWorker.execute();
    }

    private void reset(){
        sudokuSolverWorker.cancel(false);
        while(!sudokuSolverWorker.isDone()){
        }

        //remove from main, create new view and add again
        mainView.getContentPane().remove(sudokuView);
        sudokuView = new SudokuView();
        mainView.getContentPane().add(sudokuView, BorderLayout.LINE_START);
        sudokuView.setMatrix(sudokuMatrix, Color.blue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if(action.equals("Start")){
            startSudokuSolver();

            //change button
            JButton jb = (JButton)e.getSource();
            jb.setText("Cancel");
        }
        else if(action.equals("Cancel")){
            reset();

            //change button
            JButton jb = (JButton)e.getSource();
            jb.setText("Start");
        }
    }
}
