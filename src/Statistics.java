/**
 * Created by cj on 4/15/16.
 */
public class Statistics {

    public static void displayStats(){
        for (int i = 0; i <= 22; i ++){
            System.out.println(TrainSim.stopArr[i]);
            System.out.println(TrainSim.stopArr[i].numPeople() + " at Stop: " + i);
        }
    }
}
