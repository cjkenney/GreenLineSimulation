/**
 * Created by cj on 4/15/16.
 */
public class Train {
    int cars;
    String dir;
    int stop;

    public Train (int numCars, String direction, int currentStop){
        cars = numCars;
        dir = direction;
        stop = currentStop;
    }

    public int getCars() {
        return cars;
    }

    public void setCars(int cars) {
        this.cars = cars;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }
}
