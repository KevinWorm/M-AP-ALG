import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Kevin Worm on 29-10-2014.
 */
public class MatrixGenerator {

    private Employee[] employees;
    private Job[] jobs;
    private String[] names;
    private String[] jobTitles;

    public MatrixGenerator(int size){
        //max 20
        int nSize = size > 20 ? 20 : size;

        employees = new Employee[size];
        jobs = new Job[size];
        names = new String[]{
                "Jacob","Harm","Ralph","Maarten","Diederik","Kevin",
                "Pieter","Klaas","Roderick","Robert","Ellen","Rick",
                "Frederik","Piet","Bart","Niels","Maarten","Cas","Bacardi", "Jan"};
        jobTitles = new String[]{
                "IT","Computer Expert","Schoonmaker","Docent","Vuilnisman","Monteur",
                "Kunstenaar","Schilder","Vaatwasser","Vertaler","Entertainer","Exotisch danser",
                "Web designer","Programmer","Gamer","Secretaries","Chinees","Bakker","Kok","Chef"};

        initialize();
    }

    private void initialize(){
        Random generator = new Random();

        for(int i = 0; i < employees.length; i++){
            employees[i] = new Employee(names[i]);
            jobs[i] = new Job(jobTitles[i]);
        }

        //Every employee has a preference list
        for(int i = 0; i < employees.length; i++){

            int[] jobP = new int[jobs.length];

            for(int j = 0; j < jobs.length; j++){
                jobP[j] = generator.nextInt(9) + 1;
            }
            employees[i].setJobPreferences(jobP);
        }

        for(int i = 0; i < jobs.length; i++){

            int[] employeeP = new int[employees.length];

            for(int j = 0; j < employees.length; j++){
                employeeP[j] = generator.nextInt(9)+1;
            }
            jobs[i].setEmployeePreferences(employeeP);
        }
    }

    public int[][] generate(){
        int[][] matrix = new int[employees.length][jobs.length];

        for(int i = 0; i < employees.length; i++){
            for(int j = 0; j < jobs.length; j++){

                matrix[i][j] = employees[i].getJobPreferences()[j] + jobs[i].getEmployeePreferences()[j];
            }
        }

        return matrix;
    }

    public Employee[] getEmployee(){
        return employees;
    }

    public Job[] getJobs(){
        return jobs;
    }

}
