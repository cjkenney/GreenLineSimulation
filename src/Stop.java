/**
 * Created by cj on 4/15/16.
 */
public class Stop {
    String name;
    Q1 west;
    Q1 east;
    int id;

    public Stop(String stopName, Q1 westQ, Q1 eastQ, int stopNum){
        name = stopName;
        west = westQ;
        east = eastQ;
        id = stopNum;
    }
}
