/**
 * Created by cj on 4/15/16.
 */

//int randomWithRange(int min, int max)
//        {
//        int range = (max - min) + 1;
//        return (int)(Math.random() * range) + min;
//        }


public class PassengerEvent implements Event {

    Passenger p;

    public PassengerEvent(Passenger passenger){
        p = passenger;
    }

    @Override
    public void run() {

    }



}
