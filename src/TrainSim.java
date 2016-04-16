/**
 * Created by cj on 4/15/16.
 */
public class TrainSim {

    static PQ agenda = new PQ();
    static Stop[] stopArr = new Stop[23];

    public static void main(String[] args) {

        int numberOfTrains = 10; //1 - 23
        int numOfCars = 3; //1 - 3

        Train[] trainArr = new Train[numberOfTrains - 1];


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

        //westbound trains
        for (int i = 0; i < numberOfTrains / 2; i++){
            trainArr[i] = new Train(numOfCars, 0, i);
        }
        //eastbound trains
        for (int i = 0; i < numberOfTrains - numberOfTrains / 2; i++){
            trainArr[i] = new Train(numOfCars, 1, i);
        }

        for (int i = 0; i <= 22; i++){
            stopArr[i].createPassenger();
        }


        //agenda.add(new PassengerMaker(), 10);

        while (agenda.getCurrentTime() <= 100){
            agenda.remove().run();
        }

        Statistics.displayStats();

    }
}
