/**
 * Created by cj on 4/15/16.
 */
public class Passenger {

    double arrivalTime;
    int depart;
    int dest;

    public Passenger(double t, int departureStop, int destinationStop){
        arrivalTime = t;
        depart = departureStop;
        dest = destinationStop;
    }

    public void printInfo() {
        System.out.println("Arrival time: " + arrivalTime + " departureStop: " + depart + " destination: " + dest);
    }

}
