/**
 * Created by Kevin Worm on 4-10-2014.
 */
public class MainApplication {
    public static void main(String[] args) {
//        int[][] matrix = {
//                {20,22,14,24},
//                {20,19,12,20},
//                {13,10,18,16},
//                {22,23,9,28}};

        MatrixGenerator matrixGenerator =  new MatrixGenerator(4);
        int[][] matrix = matrixGenerator.generate();
        HungarianAlgorithm hAlgorithm = new HungarianAlgorithm(matrix);

        Employee[] employees = matrixGenerator.getEmployee();
        Job[] jobs = matrixGenerator.getJobs();
        int[] solution = hAlgorithm.process();

        System.out.println();
        for(int i = 0; i < solution.length; i++){
            int ePreference = 0;
            int eAssignedPreference = 0;
            int jPreference = 0;

            //get the preferred job and employee to show difference
            for(int j = 0; j < jobs.length; j++){
                if(employees[i].getJobPreferences()[j] == 1){
                    ePreference = j;
                }
                if(j == solution[i]){
                    eAssignedPreference = j;
                }
            }
            for(int j = 0; j < employees.length; j++){
                if(jobs[solution[i]].getEmployeePreferences()[j] == 1){
                    jPreference = j;
                }
            }

            System.out.println("Employee name: " + employees[i].getName() + ", Row: " + i + ", Job Title: " +
                    jobs[solution[i]].getJobTitle() + ", Column: " + solution[i] + ", preference was: " +
                    jobs[ePreference].getJobTitle() + ", Assigned job was number " + (eAssignedPreference + 1) + " choice." +
                    "\t\t Preference of the job was employee number: " + jPreference);

//            System.out.println("Row: " + i + "Column: " + solution[i]);
        }
    }
}
