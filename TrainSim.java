/**
 * Created by cj on 4/15/16.
 */
public class TrainSim {

    //Global Variables
    static PQ agenda = new PQ();
    static Stop[] stopArr = new Stop[23];
    static Train[] trainArr;



    public static void main(String[] args) {


        //Vary numberOfTrains, numberOfCars, and The Load
        int numberOfTrains = 10; //range 1 to 23
        int numOfCars = 1; //range 1 to 3


        //create static train Array
        trainArr = new Train[numberOfTrains];

        //Varied number of trains from each location so it's spaced out to get max stops
        int eastBoundTrains = (int) Math.ceil( (double) numberOfTrains / 2.0);
        int westBoundTrains = numberOfTrains - eastBoundTrains;

        //Just output number of trains
        System.out.println(eastBoundTrains + " east bound trains");
        System.out.println(westBoundTrains + " west bound trains");

        //Train[] trainArr = new Train[numberOfTrains];


        Q1 west = new Q1();
        Q1 east = new Q1();

        //Create stop in stop array
        //Format, NameField, west, east, are the queues associated, -10 is a downtain, -5 is a campus stop
        //0 is a regular stop, these are just arrival modifiers due to number of people using it
        //Downtown stops in minneapolis and saint paul on either side of the green line
        stopArr[0] = new Stop("Target Field", 0, west, east, -10);
        stopArr[1] = new Stop("Hennepin Ave", 1, west, east, -10);
        stopArr[2] = new Stop("Nicollet Mall", 2, west, east, -10);
        stopArr[3] = new Stop("Government Plaza", 3, west, east, -10);
        stopArr[4] = new Stop("US Bank Stadium", 4, west, east, -10);
        stopArr[5] = new Stop("West Bank", 5, west, east, -5);
        stopArr[6] = new Stop("East Bank", 6, west, east, -5);
        stopArr[7] = new Stop("Stadium Village", 7, west, east, -5);
        stopArr[8] = new Stop("Prospect Park", 8, west, east, 0);
        stopArr[9] = new Stop("Westgate", 9, west, east, 0);
        stopArr[10] = new Stop("Raymond Ave", 10, west, east, 0);
        stopArr[11] = new Stop("Fairview Ave", 11, west, east, 0);
        stopArr[12] = new Stop("Snelling Ave", 12, west, east, 0);
        stopArr[13] = new Stop("Hamline Ave", 13, west, east, 0);
        stopArr[14] = new Stop("Lexington Pkwy", 14, west, east, 0);
        stopArr[15] = new Stop("Victoria St", 15, west, east, 0);
        stopArr[16] = new Stop("Dale St", 16, west, east, 0);
        stopArr[17] = new Stop("Western Ave", 17, west, east, 0);
        stopArr[18] = new Stop("Capitol/Rice St", 18, west, east, -10);
        stopArr[19] = new Stop("Robert St", 19, west, east, -10);
        stopArr[20] = new Stop("10th St", 20, west, east, -10);
        stopArr[21] = new Stop("Central", 21, west, east, -10);
        stopArr[22] = new Stop("Union Depot", 22, west, east, -10);

        //Spacing out the trains, calculated on next lines of code
        int eastSpacer = 0;
        int westSpacer = 0;

        //space out trains evenly

        if (eastBoundTrains % 2 == 0){
            eastSpacer = (int) Math.ceil(23.0 / (double) eastBoundTrains);
        }
        else if (eastBoundTrains % 2 != 0){
            eastSpacer = 23 / eastBoundTrains;
        }
        else {
            System.out.println("error TrainSim eastSpacer");
            eastSpacer = 0;
        }

        if (westBoundTrains != 0  && westBoundTrains % 2 == 0){
            westSpacer = (int) Math.ceil(23.0 / (double) westBoundTrains);
        }
        else if (westBoundTrains != 0  && westBoundTrains % 2 != 0){
            westSpacer = 23 / westBoundTrains;
        }

        if (numberOfTrains >= 19){
            eastSpacer = 2;
            westSpacer = 2;
        }

        System.out.println("eastSpacer " + eastSpacer);
        System.out.println("westSpacer " + westSpacer);

        int index = 0;  //index of trainArr
        int j = 0;  //used for stop number

        //-----------------------------Train(numOfCars, direction, currentStop)--------------------
        //--------------------------------------------------^(0 = west, 1 = east)------------------

        //After this comment is where trains start being created

        Train tempTrain = null;

        //Eastbound Trains

        while (index < eastBoundTrains) {

            //CREATE TRAIN, if train is created at turnaround, direction is switched
            if (j != 22){
                tempTrain = new Train(numOfCars, 1, j, j+1);
                trainArr[index] = tempTrain;
            }
            //Stop 22 is the last stop, so have to switch the way it's going to make sense
            else if (j == 22){
                //j = current stop
                tempTrain = new Train(numOfCars, 0, j, 21);
                trainArr[index] = tempTrain;
            }

            //ADD TRAIN TO STOP
            stopArr[j].addTrain(tempTrain);


            j += eastSpacer;
            index++;
        }

        //Now starting at westbound
        //22 is stop 22, the last one in the east
        j = 22;  //set stop number to decrease from east stations

        //Westbound Trains

        while (index < numberOfTrains){

            //CREATE TRAIN, if train is created at turnaround, direction is switched
            if (j != 0){
                tempTrain = new Train(numOfCars, 0, j, j-1);
                trainArr[index] = tempTrain;
            }
            else if (j == 0){
                tempTrain = new Train(numOfCars, 1, j, 1);
                trainArr[index] = tempTrain;
            }

            //ADD TRAIN TO STOP
            stopArr[j].addTrain(tempTrain);

            j -= westSpacer;
            index++;
        }

        boolean err = false;
        System.out.println();
        for (int i = 0; i < trainArr.length; i++){
            System.out.println("Train: " + i + trainArr[i].getInfo());
            if (trainArr[i].getStop() > 22 || trainArr[i].getStop() < 0){
                err = true;
            }
        }

        if (err){
            System.out.println("ERROR INVALID STOP");
        }

        //START CREATING EVENTS
        //adds new PassengerMaker to each stop
        for (int i = 0; i <= 22; i++){
            stopArr[i].createPassenger();
        }

        //Adds Events
        for (int i = 0; i < numberOfTrains; i++){
            trainArr[i].startTrain();
        }


        //Removes Events
        while (agenda.getCurrentTime() <= 1000){
            agenda.remove().run();
        }



        //Statistics Output
        Statistics.displayStats();
        Statistics.outputPassengerData();
        System.out.println("Number of trains used: " + numberOfTrains);
        System.out.println("Number of cars used: " + numOfCars);

    }
}
