/**
 * Created by Kevin Worm on 29-10-2014.
 */
public class HungarianAlgorithm {

    int[][] valueMatrix;
    boolean[] coveredRows;
    boolean[] coveredColumns;
    int[] assign;

    HungarianAlgorithm(int[][] valueMatrix){
        this.valueMatrix = valueMatrix;
        coveredRows = new boolean[valueMatrix.length];
        coveredColumns = new boolean[valueMatrix[0].length];
        setCoveredToFalse();

        assign = new int[valueMatrix.length];
    }

    public int[] process(){
        int lines = 0;

        System.out.println("Start: ");
        printMatrix();
        reduceCost();
        System.out.println();
        System.out.println("Subtracted the smallest element of each row and column from all values: ");
        lines = findMinimumLinesToCoverZeros();
        printMatrix();

        while(lines < valueMatrix.length){
            subtractAndAddLowestValue();
            lines = findMinimumLinesToCoverZeros();
            System.out.println();
            System.out.println("Subtracted lowest value that wasn't covert and added it to values covert by 2 lines. Used " + lines + " lines to cover zeros.");
            printMatrix();
        }

        //return assign();
        setCoveredToFalse();
        assignmentBacktrack(0,0,0);
        return assign;

    }

    //recursive function, return true when finished, when false try next solution (backtrack)
    private boolean assignmentBacktrack(int currentRow, int currentColumn, int totalAssignments){
        //if cell is zero and isn't assigned
        if(valueMatrix[currentRow][currentColumn] == 0 && !coveredRows[currentRow] && !coveredColumns[currentColumn]){
            //assign and cover
            assign[currentRow] = currentColumn;
            coveredRows[currentRow] = true;
            coveredColumns[currentColumn] = true;
            totalAssignments++;
        }

        int row = currentRow, column = currentColumn;
        //move to first empty field
        while(row < valueMatrix.length){
            column++;
            //if last column, move to next row
            if(column == valueMatrix[row].length) {
                column = 0;
                row++;
            }
            //end of field, check of everything is assigned
            if(row == valueMatrix.length){
                if(totalAssignments < assign.length){break;}
                else {return true;}
            }
            //if cell is zero and isn't assigned
            if(valueMatrix[row][column] == 0 && !coveredRows[row] && !coveredColumns[column]){
                //recursive function, if return is true, solution is found or worker is canceled
                if(assignmentBacktrack(row, column, totalAssignments))return true;
                //try next solution
            }
        }

        //clear current sub field
        coveredRows[currentRow] = false;
        coveredColumns[currentColumn] = false;
        totalAssignments--;
        //backtrack
        return false;
    }

    /**
     * Find the lowest uncovered value and subtract that from all uncovered values and add it
     * values covert by 2 lines
     */

    private void subtractAndAddLowestValue(){
        int lowestValue = Integer.MAX_VALUE;
        for(int i = 0; i < valueMatrix.length; i++){
            for(int j = 0; j < valueMatrix[i].length; j++){
                //if value is not covert and smaller than current smallest value, set as smallest value
                if(!coveredRows[i] && !coveredColumns[j] && valueMatrix[i][j] < lowestValue){
                    lowestValue = valueMatrix[i][j];
                }
            }
        }
        for(int i = 0; i < valueMatrix.length; i++){
            for(int j = 0; j < valueMatrix[i].length; j++){
                //if value is not covert subtract
                if(!coveredRows[i] && !coveredColumns[j]){
                    valueMatrix[i][j] -= lowestValue;
                }
                //if value is covert double add
                else if(coveredRows[i] && coveredColumns[j]){
                    valueMatrix[i][j] += lowestValue;
                }
            }
        }
    }

    /**
     * Try to cover all zero's with as little possible horizontal and vertical lines.
     * When total lines matches the grid size a solution has been found.
     */

    private int findMinimumLinesToCoverZeros(){
        //reset covert rows and columns
        setCoveredToFalse();
        boolean zerosFound;
        int covert = 0;

        do{
            zerosFound = false;

            int[] totalZerosInRows = new int[valueMatrix.length];
            int[] totalZerosInColumns = new int[valueMatrix[0].length];

            int maxRow = 0, maxColumn = 0;

            for(int i = 0; i < valueMatrix.length; i++){
                for(int j = 0; j < valueMatrix[i].length; j++){
                    //if value is zero and row or column hasn't been covert, count
                    if(valueMatrix[i][j] == 0 && coveredRows[i] == false && coveredColumns[j] == false){
                        totalZerosInRows[i] += 1;
                        totalZerosInColumns[j] += 1;
                        //Keep the row and column id with the highest value
                        if(totalZerosInRows[maxRow] < totalZerosInRows[i]){
                            maxRow = i;
                        }
                        if(totalZerosInColumns[maxColumn] < totalZerosInColumns[j]){
                            maxColumn = j;
                        }

                        zerosFound = true;
                    }
                }
            }

            if (zerosFound) {
                if(totalZerosInColumns[maxColumn] < totalZerosInRows[maxRow]){
                    coveredRows[maxRow] = true;
                    covert++;
                }
                else{
                    coveredColumns[maxColumn] = true;
                    covert++;
                }
            }
        }
        while (zerosFound);

        return covert;
    }

    private void setCoveredToFalse(){
        for(int i = 0; i < coveredRows.length; i++){
            coveredRows[i] = false;
            coveredColumns[i] = false;
        }
    }

    /**
     * Reduce the value matrix by subtracting the smallest element of each row from
     * all elements of the row as well as the smallest element of each column from
     * all elements of the column.
     */

    private void reduceCost(){
        //rows
        for(int i = 0; i < valueMatrix.length; i++){
            int minimumValue = Integer.MAX_VALUE;
            //Check all rows and save lowest value
            for(int j = 0; j < valueMatrix[i].length; j++){
                if(valueMatrix[i][j] < minimumValue){
                    minimumValue = valueMatrix[i][j];
                }
            }
            //subtract lowest value
            for(int j = 0; j < valueMatrix[i].length; j++){
                valueMatrix[i][j] -= minimumValue;
            }
        }
        //columns
        for(int i = 0; i < valueMatrix[0].length; i++){
            int minimumValue = Integer.MAX_VALUE;
            //Check all columns and save lowest value
            for(int j = 0; j < valueMatrix[i].length; j++){
                if(valueMatrix[j][i] < minimumValue){
                    minimumValue = valueMatrix[j][i];
                }
            }
            //subtract lowest value
            for(int j = 0; j < valueMatrix[i].length; j++){
                valueMatrix[j][i] -= minimumValue;
            }
        }
    }

    private void printMatrix(){
        printGridLine(true);
        for(int i = 0; i < valueMatrix.length; i++){
            System.out.print(coveredRows[i] ? "* " : "  ");
            System.out.print("| ");
            for(int j = 0; j < valueMatrix[i].length; j++){
                if(valueMatrix[i][j] < 10 ){System.out.print("0");}
                System.out.print(valueMatrix[i][j] + " | ");
            }
            System.out.println();
            printGridLine(false);
        }
    }

    private void printGridLine(boolean first){
        if(first){
            System.out.print(coveredColumns[0] ? "     * " : "       ");
            for(int i = 1; i < valueMatrix[0].length; i++){
                System.out.print(coveredColumns[i] ? "  *  " : "     ");
            }
            System.out.println();
        }

        System.out.print("   ---- ");
        for(int i = 1; i < valueMatrix[0].length; i++){
            System.out.print("---- ");
        }
        System.out.println();
    }

}
