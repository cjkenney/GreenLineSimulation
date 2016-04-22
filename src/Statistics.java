/**
 * Created by cj on 4/15/16.
 */
public class Statistics {

    Passenger p;
    static int passengersProcessed = 0; //number of Passengers arrived at their finalDestination

    public static void displayStats(){
        for (int i = 0; i <= 22; i ++){
            //TrainSim.stopArr[i].printPeople();
            //System.out.println(TrainSim.stopArr[i]);
            System.out.println(TrainSim.stopArr[i].numPeople() + " at Stop " + i);
        }
    }

    public static void analyzePassenger(Passenger p) {
        passengersProcessed ++;
        System.out.println();
        System.out.println("analyzing " + p);
        System.out.println("Departure " + p.getDepartureStop());
        System.out.println("Arrived at Depart Stop @ " + p.getArrivalTime());
        System.out.println("Destination " + p.getDestination());
        System.out.println("Arrived at Destination Stop @ " + p.getFinalTime());
    }
}
