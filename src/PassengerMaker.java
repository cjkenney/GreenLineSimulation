/**
 * Created by cj on 4/15/16.
 */

//        10% of the time: 75% above average arrival interval (30 + .75 * 30)
//        15% of the time: 50% above average arrival interval (30 + .50 * 30)
//        20% of the time: 20% above average arrival interval (30 + .20 * 30)
//        10% of the time: right at average arrival interval (30)
//        20% of the time: 20% below average arrival interval (30 - .20 * 30)
//        15% of the time: 50% below average arrival interval (30 - .50 * 30)
//        10% of the time: 75% below average arrival interval (30 - .75 * 30)

public class PassengerMaker implements Event {

    int interval;  //average arrival interval
    int stopnum;  //used in calculation of destination (downtown stops are 5 times more likely than other stops, campus is 3 times)
    int prob;  //probability used with arrival interval (see comments above)
    int departure;
    int destination;
    Stop stopIndex;

    public PassengerMaker(int departureStop, int averageInterval, Stop s){
        departure = departureStop;
        destination = departureStop;
        interval = averageInterval;
        stopIndex = s;
    }

    private double arrivalModifier(){
        prob = intRandomInterval(1, 100);
        if (prob >= 1 && prob <= 10){
            return .75;
        }
        else if (prob >= 11 && prob <= 25){
            return .5;
        }
        else if (prob >= 26 && prob <= 45){
            return .2;
        }
        else if (prob >= 46 && prob <= 55){
            return 0;
        }
        else if (prob >= 56 && prob <= 75){
            return -.2;
        }
        else if (prob >= 76 && prob <= 90){
            return -.5;
        }
        else if (prob >= 91 && prob <= 100){
            return -.75;
        }
        else{
            System.out.print("arrivalModifier Error");
            return 0;
        }
    }

    private int destinationGenerate(){
        prob = intRandomInterval(1, 69);

        //System.out.println(prob);

        if (prob >= 1 && prob <= 50){  //10 downtown stops, 5 times more likely than other stops

            prob = intRandomInterval(1, 10);
            switch (prob) {
                case 1:
                    stopnum = 0;
                break;
                case 2:
                    stopnum = 1;
                break;
                case 3:
                    stopnum = 2;
                break;
                case 4:
                    stopnum = 3;
                break;
                case 5:
                    stopnum = 4;
                break;
                case 6:
                    stopnum = 18;
                break;
                case 7:
                    stopnum = 19;
                break;
                case 8:
                    stopnum = 20;
                break;
                case 9:
                    stopnum = 21;
                break;
                case 10:
                    stopnum = 22;
                break;
                default:
                    stopnum = -1;
                break;
            }
            return stopnum;
        }

        else if (prob >= 51 && prob <= 59){   //3 campus stops, 3 times as likely than other stops
            return intRandomInterval(5, 7);
        }

        else if (prob >= 60 && prob <= 69){   //10 other stops
            return intRandomInterval(8, 17);
        }

        else{
            System.out.println("destinationGenerate error, prob: " + prob);
            return -1;
        }
    }

    private int intRandomInterval(int low, int high) {
        return (int) Math.floor((high - low) * Math.random() + low + 0.5);
    }

    @Override
    public void run() {

        //makes sure passengers don't have duplicate starting points and departures
        while (destination == departure){
            destination = destinationGenerate();
        }

        Passenger newPassenger = new Passenger(TrainSim.agenda.getCurrentTime(), 0.0,
                departure, destination);

        newPassenger.printInfo();

        TrainSim.stopArr[departure].addPassenger(newPassenger);
//        System.out.println("Added " + newPassenger + " to Stop " + departure);
//        System.out.println("westLength: " +TrainSim.stopArr[departure].getWestLength());
//        System.out.println("eastLength: " +TrainSim.stopArr[departure].getEastLength());



        //schedule new PassengerMaker according to intervalModifier
        TrainSim.agenda.add(new PassengerMaker(departure, interval, stopIndex), interval + arrivalModifier() * interval);
    }
}
