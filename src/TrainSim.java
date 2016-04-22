/**
 * Created by cj on 4/15/16.
 */
public class TrainSim {

    static PQ agenda = new PQ();
    static Stop[] stopArr = new Stop[23];
    static Train[] trainArr;

    public static void main(String[] args) {

        int numberOfTrains = 1; //range 1 to 23
        int numOfCars = 1; //range 1 to 3

        //create static train Array
        trainArr = new Train[numberOfTrains];

        int eastBoundTrains = (int) Math.ceil( (double) numberOfTrains / 2.0);
        int westBoundTrains = numberOfTrains - eastBoundTrains;

        System.out.println(eastBoundTrains + " east bound trains");
        System.out.println(westBoundTrains + " west bound trains");

        //Train[] trainArr = new Train[numberOfTrains];


        Q1 west = new Q1();
        Q1 east = new Q1();

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

        Train tempTrain = null;

        //Eastbound Trains

        while (index < eastBoundTrains) {

            //CREATE TRAIN, if train is created at turnaround, direction is switched
            if (j != 22){
                tempTrain = new Train(numOfCars, 1, j, j+1);
                trainArr[index] = tempTrain;
            }
            else if (j == 22){
                tempTrain = new Train(numOfCars, 0, j, 21);
                trainArr[index] = tempTrain;
            }

            //ADD TRAIN TO STOP
            stopArr[j].addTrain(tempTrain);


            j += eastSpacer;
            index++;
        }

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


        //adds new PassengerMaker to each stop
        for (int i = 0; i <= 22; i++){
            stopArr[i].createPassenger();
        }

        for (int i = 0; i < numberOfTrains; i++){
            trainArr[i].startTrain();
        }


        //agenda.add(new PassengerMaker(), 10);

        while (agenda.getCurrentTime() <= 100000){
            agenda.remove().run();
        }

        Statistics.displayStats();

    }
}
