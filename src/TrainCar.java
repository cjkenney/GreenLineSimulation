/**
 * Created by cj on 4/15/16.
 */
public class TrainCar {

    int numSeats;
    int seatsAvailable;
    int ptr;
    Passenger[] passArr;
    Q1 passQ;

    public TrainCar(){
        numSeats = 50;
        seatsAvailable = numSeats;
        passArr = new Passenger[numSeats];
        ptr = 0;
        passQ = new Q1();
    }

    public void addPassenger(Passenger p){
        if (!isFull()) {
            passQ.add(p);
            seatsAvailable--;
        }
    }

    public int numOfPassengers(){
        return passQ.length();
    }

    public Passenger removePassenger(){
        seatsAvailable++;
        return (Passenger) passQ.remove();
    }

    public Passenger removePassenger(Passenger p){
        if (passQ.length() == 0){
            return null;
        }
        Passenger test;
        int qLen = passQ.length();

        while (qLen > 0){
            test = (Passenger) passQ.remove();
            if(test.equals(p)){
                seatsAvailable++;
                return test;
            }
            else{
                passQ.add(test);
                qLen--;
            }
        }

        return null;
    }

    public boolean isFull(){
        if (seatsAvailable > 0){
            return false;
        }
        else {
            return true;
        }
    }

    public void subtractSeat(){
        seatsAvailable --;
    }

    public int getSeatsAvailable(){
        return seatsAvailable;
    }
}
