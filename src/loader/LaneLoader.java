package loader;

import de.tudresden.sumo.cmd.*;
import de.tudresden.sumo.objects.*;


import java.util.ArrayList;
import java.util.List;

import util.ConnectionManager;

public class LaneLoader {
    public static int laneCount; //keeps track of number of lanes
    public static List<String> laneIDs; //list of all the lanes in the simulation
    public static List<SumoGeometry> lanePositions; //List of a List of the singular "lines" the lanes is drawn out of.
    public static List<SumoPosition2D> atomicLanePositions;

    //when a loader.LaneLoader object is created, all the lanes are loaded into the Lists.
    public LaneLoader(ConnectionManager conn) throws Exception {
        laneCount = (int) conn.dojobget(Lane.getIDCount()); //gets the number of lanes into laneCount
        laneIDs = (List<String>) conn.dojobget(Lane.getIDList());

        lanePositions       = new ArrayList<>();//creates a list of all the lanes in laneIDs
        atomicLanePositions = new ArrayList<>();

        for(int i = 0; i < laneCount; i++){ //loops through laneIDs List and grabs those coordinate List of the lines
            lanePositions.add(i, (SumoGeometry) conn.dojobget(Lane.getShape(laneIDs.get(i))));
        }
    }

    public static void printAllLaneCoordinates(){
        for(int i = 0; i < laneCount; i++) {
            for(int j = 0; j < lanePositions.get(i).coords.size(); j++){
                atomicLanePositions.add(lanePositions.get(i).coords.get(j));
            }
        }
        System.out.println(atomicLanePositions);
    }
    public static void convertToRenderLaneCoordinates(){

    }
    public static void printAllLaneIDs(){
        System.out.println(laneIDs);
    }
}
