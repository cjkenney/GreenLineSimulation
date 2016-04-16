/**
 * Created by cj on 4/15/16.
 */
public class Stop {
    String name;
    Q1 west;
    Q1 east;
    int id;
    int interval = 30; //average arrival interval

    public Stop(String stopName, int idNum, Q1 westQ, Q1 eastQ, int intervalModifier){
        name = stopName;
        west = westQ;
        east = eastQ;
        id = idNum;

        interval -= intervalModifier;
    }

    public void add(Passenger p){
        if (p.getDirection() == 0){
            west.add(p);
        }
        else if (p.getDirection() == 1){
            east.add(p);
        }
        else{
            System.out.println("Stop class add() error");
        }

    }

    public void createPassenger(){
        TrainSim.agenda.add(new PassengerMaker(id, interval, null), 0);
    }

    public int numPeople(){
        return west.length() + east.length();
    }
}
