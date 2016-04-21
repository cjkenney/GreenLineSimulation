/**
 * Created by cj on 4/15/16.
 */
public class Train {
    int cars;
    int dir;  //0 = west, 1 = east
    int stop;
    int nextStop;
    TrainCar[] carArr;


    public Train (int numCars, int direction, int currentStopPtr, int nextStopPtr){
        cars = numCars;

        carArr = new TrainCar[cars];

        for (int i = 0; i < cars; i ++){
            carArr[i] = new TrainCar();
        }

        dir = direction;
        stop = currentStopPtr;
        nextStop = nextStopPtr;
    }

    public int getStop(){
        return stop;
    }

    public int getNextStop(){
        return nextStop;
    }

    public void generateNextStop(){
        //sets next stop. If train is at a turnaround, changes direction and sets next stop accordingly

        //eastbound Trains
        if (dir == 0){
            if (stop != 0){
                nextStop --;
            }
            else if (stop == 0){
                dir = 1;
                nextStop = 1;
            }
        }

        //westbound Trains
        else if (dir == 1){
            if (stop != 22){
                nextStop++;
            }
            else if (stop == 22){
                dir = 0;
                nextStop = 21;
            }
        }


    }

    public String getInfo(){
        String rtr = "";
        if (this != null){
            rtr = " currentStop: " + stop + " direction: " + dirToString() + " nextStop: " + nextStop;
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
