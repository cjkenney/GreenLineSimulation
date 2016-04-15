/**
 * Created by cj on 4/15/16.
 */
public class TrainSim {

    static PQ agenda = new PQ();
    
    public static void main(String[] args) {

        Train[] trainArr = new Train[22];
        Stop[] stopArr = new Stop[22];
        Q1 west = new Q1();
        Q1 east = new Q1();

        stopArr[0] = new Stop("UnionDepot", west, east, 0);

        trainArr[0] = new Train(3, "west", 0);

        agenda.add(new PassengerMaker(30), 10);

    }
}
