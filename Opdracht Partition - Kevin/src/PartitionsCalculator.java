import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kevin Worm on 2-9-2014.
 */


public class PartitionsCalculator {

    private static Integer base = 10;
    private static ArrayList<ArrayList<Integer>> partitions = new ArrayList<ArrayList<Integer>>();

    public static void main(String [] args)
    {
        partitions.add(new ArrayList<Integer>( Arrays.asList(base)));
        generatePartitions();


        for(int i = 0; i <= partitions.size()-1; i++){
            System.out.print("P: ");
            for(int j = 0; j <= partitions.get(i).size()-1; j++){
                System.out.print(partitions.get(i).get(j));
                if(j != partitions.get(i).size()-1){
                    System.out.print(" + ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Total: " + partitions.size());
    }

    private static void generatePartitions()
    {
        ArrayList<Integer> partition;
        //loop through all partitions
        do{
            partition = partitions.get(partitions.size()-1);
            loopThroughPartition(partition);
        }while(partition.get(0) > 1);
    }

    //loop through parts from right to left
    private static void loopThroughPartition(ArrayList<Integer> partition)
    {
        int partTotal = countPartTotal(partition);
        int remainder = base - partTotal;

        if(partTotal == base){
            addPartition(partition);
            return;
        }
        //if remainder is bigger than current part, divide into smaller numbers
        if(remainder >= partition.get(partition.size()-1)){
            for(int i = 0; i < (remainder / partition.get(partition.size()-1)); i++){
                partition.add(partition.get(partition.size()-1));
            }
            if(remainder % partition.get(partition.size()-1) != 0){
                partition.add(remainder % partition.get(partition.size()-1));
                addPartition(partition);
                return;
            }
        }
        else{
            partition.add(remainder);
            addPartition(partition);
            return;
        }
    }

    //add new partition, copy all parts that are bigger than 1 and subtract 1 from the last part
    private static void addPartition(ArrayList<Integer> partition)
    {
        int part = 0;
        ArrayList<Integer> newPartition = new ArrayList<Integer>();


        //find the first part from the right that's bigger than 1
        for(int i = (partition.size()-1); i >= 0; i--){
            if(partition.get(i) > 1){
                part = i;
                break;
            }
        }

        for(int i = 0; i <= part; i++)
        {
            if(i == part){
                newPartition.add(partition.get(i)-1);
            }
            else{
                newPartition.add(partition.get(i));
            }
        }
        partitions.add(newPartition);
    }

    //count all parts bigger than 1
    private static int countPartTotal(ArrayList<Integer> partition)
    {
        int total = 0;
        for(int i = 0; i < partition.size(); i++)
        {
            total += partition.get(i);
        }
        return total;
    }
}
