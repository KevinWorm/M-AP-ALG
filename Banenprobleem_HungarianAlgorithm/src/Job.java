import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin Worm on 29-10-2014.
 */
public class Job {

    private String jobTitle;
    private int[] EmployeePreferences;

    public Job(String jobTitle){
        this.jobTitle = jobTitle;
        EmployeePreferences = new int[0];
    }

    public String getJobTitle(){
        return jobTitle;
    }

    public void setEmployeePreferences(int[] employeePreferences){
        this.EmployeePreferences = employeePreferences;
    }

    public int[] getEmployeePreferences(){
        return EmployeePreferences;
    }
}
