/**
 * Created by cj on 4/15/16.
 */
public class Train {
    int cars;
    int dir;  //0 = west, 1 = east
    int stop;

    public Train (int numCars, int direction, int currentStop){
        cars = numCars;
        dir = direction;
        stop = currentStop;
    }

    public int getCars() {
        return cars;
    }

}
