/**
 * Created by cj on 4/15/16.
 */
public class TrainEvent implements Event {

    Train tempTrain;
    TrainCar[] tempCarArr;
    Passenger tempPass;
    int numPassenger = 0;

    Stop currentStop;
    int currentStopId;

    int waitModifier = 0;

    int i;

    public TrainEvent(Train t){
        tempTrain = t;
        tempCarArr = tempTrain.getCarArr();
        currentStop = TrainSim.stopArr[tempTrain.getStop()];
        currentStopId = currentStop.getId();
        System.out.println("Train " + t + " at Stop " + currentStopId + ".  NextStop " + t.getNextStop());
    }

    @Override
    public void run() {

        //Passengers get off train
        //iterates through each TrainCar to see if Passengers destination matches the current Stop
        for (int i = 0; i < tempCarArr.length; i++){

            numPassenger = tempCarArr[i].numOfPassengers();

            while(numPassenger > 0){

                tempPass = tempCarArr[i].removePassenger();

                //checks if Passenger destination matches current Stop
                if (tempPass.getDestination() == currentStopId){
                    tempPass.setFinalTime(TrainSim.agenda.getCurrentTime());
                    Statistics.analyzePassenger(tempPass); //tempPass isn't added back to TrainCar Queue, processed for statistics
                    waitModifier += 2;

                }
                //Adds Passenger back to TrainCar if not their destination Stop
                else if (tempPass.getDestination() != currentStopId){
                    tempCarArr[i].addPassenger(tempPass);
                }
                numPassenger--;
            }
        }

        //Passengers get on train

        //Direction: 0 = westbound; 1 = eastbound

        //Westbound Train, also checks if Westbound Queue is empty
        if (tempTrain.getDirection() == 0  && currentStop.getWestLength() > 0){

            numPassenger = currentStop.getWestLength(); //number of Passengers in West Queue

            i = 0;
            while (numPassenger > 0){
                if (i == tempTrain.getCars()){
                    break;
                }
                if (!tempCarArr[i].isFull()){
                    tempCarArr[i].addPassenger(currentStop.removeWestPassenger());
                    waitModifier += 1;
                    numPassenger--;
                }
                else if (tempCarArr[i].isFull() && i < tempTrain.getCars()){
                    i++;
                }
            }
        }

        //Eastbound Train, also checks if Eastbound Queue is empty
        else if (tempTrain.getDirection() == 1  && currentStop.getEastLength() > 0){

            numPassenger = currentStop.getWestLength(); //number of Passengers in West Queue

            i = 0;
            while (numPassenger > 0){
                if (!tempCarArr[i].isFull()){
                    tempCarArr[i].addPassenger(currentStop.removeEastPassenger());
                    numPassenger--;
                }
                else if (tempCarArr[i].isFull() && i < tempTrain.getCars()){
                    i++;
                }
            }
        }

        else {
            System.out.println(tempTrain + " No one here at Stop " + currentStopId);
        }


        if (waitModifier < 15){
            waitModifier = 15;
        }

        tempTrain.setCurrentStop(tempTrain.getNextStop());
        tempTrain.generateNextStop();
        TrainSim.agenda.add(new TrainEvent(tempTrain), 180 + waitModifier);

    }
}
