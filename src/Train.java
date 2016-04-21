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

    public int getStop(){
        return stop;
    }

    public String getInfo(){
        String rtr = "";
        if (this != null){
            rtr = " currentStop: " + stop + " direction: " + dirToString();
        }
        return rtr;
    }

    public String dirToString(){
        String directionStr;

        if (dir == 0){
            directionStr = "west";
        }
        else if (dir == 1){
            directionStr = "east";
        }
        else{
            System.out.println("Null direction Train class dirToString()");
            directionStr = null;
        }
        return directionStr;
    }

    public int getCars() {
        return cars;
    }

}
