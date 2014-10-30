import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin Worm on 29-10-2014.
 */
public class Employee {

    private String name;
    private int[] jobPreferences;

    public Employee(String name){
        this.name = name;
        jobPreferences = new int[0];
    }

    public String getName(){
        return name;
    }

    public void setJobPreferences(int[] jobs){
        this.jobPreferences = jobs;
    }

    public int[] getJobPreferences(){
        return jobPreferences;
    }
}
