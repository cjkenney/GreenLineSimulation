/**
 * Created by cj on 4/15/16.
 */
public class Statistics {


    Passenger p;
    static int passengersProcessed = 0; //number of Passengers arrived at their finalDestination

    //Collective Passenger Array
    private static Passenger[] passengerArray = new Passenger[100]; //Default 100, will grow if needed

    public static void displayStopStats(){
        for (int i = 0; i <= 22; i ++){
            //TrainSim.stopArr[i].printPeople();
            //System.out.println(TrainSim.stopArr[i]);
            System.out.println(TrainSim.stopArr[i].numPeople() + " currently at Stop " + i);
        }
    }

    public static void analyzePassenger(Passenger p) {
        passengersProcessed ++;
        System.out.println();
        System.out.println("analyzing " + p);
        System.out.println("Departure " + p.getDepartureStop());
        System.out.println("Arrived at Depart Stop @ " + p.getArrivalTime());
        System.out.println("Destination " + p.getDestination());
        System.out.println("Arrived at Destination Stop @ " + p.getFinalTime());
        System.out.println("Total time waited " + (p.getFinalTime() - p.getArrivalTime()));
        System.out.println();


        //Add Exited Passenger to PassengerArray for data collection
        if ((passengersProcessed - 1 >= passengerArray.length)) { //Check to see if passengerArray needs to be expanded
            //Create placeholder array with double size for more values,
            //copy orig array values to it as a placeholder
            //set orig array to the placeHolder, now have double the room
            Passenger[] placeholderPassArr = new Passenger[passengerArray.length * 2];
            System.arraycopy(passengerArray, 0, placeholderPassArr, 0, passengerArray.length);
            passengerArray = placeholderPassArr;

            //Add in the new passenger after expansion
            passengerArray[passengersProcessed-1] = p;
        }
        else {
            passengerArray[passengersProcessed - 1] = p;
        }
    }



    public int getPassengersProcessed(){
        return passengersProcessed;
    }

    public static void outputPassengerData() {
        //Encompassing Variables for Overall Statistics
        double collectiveAvgTimeWaited = 0.0;
        double maxWaitTime = 0.0;
        double minWaitTime = passengerArray[1].getFinalTime() - passengerArray[1].getArrivalTime();


        //Block to output individual Passenger Data
        for (int iter = 0; iter <= passengerArray.length; iter++){
            if(passengerArray[iter] != null) {
                System.out.println("Passenger Number: " + iter);
                passengerArray[iter].printDetailedInfo();

                //Add to Overall Statistics Variables
                collectiveAvgTimeWaited += (passengerArray[iter].getFinalTime() - passengerArray[iter].getArrivalTime());

                //Get Max & Min Wait Time
                double passengerWaitTime = (passengerArray[iter].getFinalTime() - passengerArray[iter].getArrivalTime());
                if (passengerWaitTime > maxWaitTime){
                    maxWaitTime = passengerWaitTime;
                }
                else if (passengerWaitTime < minWaitTime){
                    minWaitTime = passengerWaitTime;
                }
            }
            else {
                //Break if the passengerArray spot is null, aka when you've reached the last passenger stored in the array
                break;
            }
        }

        //More Statistics Calculations
        collectiveAvgTimeWaited /= passengersProcessed;

        //Overall Statistics Output
        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println("Overall Statistics Output: ");
        System.out.println("Number of trains used: " + TrainSim.trainArr.length);
        System.out.println("Number of cars used: " + TrainSim.trainArr[0].getCars());
        System.out.println("Number of Passengers Processed: " + passengersProcessed);
        System.out.println("Minimum Wait Time of Passengers: " + minWaitTime);
        System.out.println("Maximum Wait Time of Passengers: " + maxWaitTime);
        System.out.println("Average Passenger Wait Time: " + collectiveAvgTimeWaited);
        System.out.println("++++++++++++++++++++++++++++++++++++");



    }
}
