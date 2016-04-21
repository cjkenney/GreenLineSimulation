/**
 * Created by cj on 4/15/16.
 */
public class TrainEvent implements Event {

    Train tempTrain;
    TrainCar[] tempCarArr;
    Passenger tempPass;
    int numPassenger = 0;
    Stop currentStop;

    public TrainEvent(Train t){
        tempTrain = t;
        tempCarArr = tempTrain.getCarArr();
    }

    @Override
    public void run() {

        //Passengers get off train
        //iterates through each TrainCar to see if Passengers destination matches the current Stop
        for (int i = 0; i < tempCarArr.length; i++){
            numPassenger = tempCarArr[i].numOfPassengers();
            while(numPassenger > 0){

                tempPass = tempCarArr[i].removePassenger();

                if (tempPass.getDestination() == tempTrain.getStop()){
                    TrainSim.agenda.add(new PassengerEvent(tempPass), 2);
                }
                else if (tempPass.getDestination() != tempTrain.getStop()){
                    tempCarArr[i].addPassenger(tempPass);
                }
                numPassenger--;
            }
        }

        //Passengers get on train





        tempTrain.generateNextStop();
        TrainSim.agenda.add(new TrainEvent(tempTrain), 180);
    }
}
