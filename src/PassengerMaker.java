/**
 * Created by cj on 4/15/16.
 */
public class PassengerMaker implements Event {

    int interval;

    public PassengerMaker(int averageArrivalInterval){
        interval = averageArrivalInterval;
    }

    private int intRandomInterval(int low, int high) {
        return (int) Math.floor((high - low) * Math.random() + low + 0.5);
    }

    @Override
    public void run() {
        TrainSim.agenda.add(new PassengerMaker(interval), intRandomInterval(0, 2 * interval));
        
        Passenger newPassenger = new Passenger(TrainSim.agenda.getCurrentTime());
    }
}
