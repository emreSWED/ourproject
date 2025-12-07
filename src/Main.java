
import de.tudresden.sumo.cmd.*;
import de.tudresden.sumo.objects.SumoLink;
import de.tudresden.sumo.objects.SumoLinkList;
import de.tudresden.sumo.objects.SumoTLSController;
import de.tudresden.sumo.util.*;
import it.polito.appeal.traci.*;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import conn.ConnectionManager;

import conn.ConnectionManager;

public class Main {
    public static void main(String[] args) throws Exception {

        ConnectionManager conn = new ConnectionManager("SumoConfig/myconfig.sumocfg");
        conn.startConnection();

        //get map data for UI
       // List<String> trafficLights = conn.getTrafficLights();
       // List<String> lanes = (List<String>) conn.dojobget(Lane.getIDList());
       // JunctionLoader junctions = new JunctionLoader(conn.traciConnection);

        //github test 17:32
      // System.out.println("current number of traffic lights: " + trafficLights.size());
      //  System.out.println("ID of Traffic lights:"+ conn.getTrafficLights());
       // System.out.println("ID of first Lane:"+ lanes.getFirst());
       // System.out.println(conn.dojobget(Lane.getShape(lanes.getFirst())));
      //  System.out.println("Positions of Junctions:" + junctions.JunctionPositionList);
       // System.out.println("Links:"+ conn.dojobget(Lane.getLinks(lanes.getFirst())));
       // SumoLinkList links = new SumoLinkList();
       // links = (SumoLinkList) conn.dojobget(Lane.getLinks(lanes.getFirst()));
       // System.out.println(" First list of links for first Lane:"+ links);
       // trafficLightLanes  = conn.dojobget(Trafficlight.getControlledJunctions(trafficLights.getFirst()));
       // System.out.println("Controlled links by traffic light 1: " + trafficLightLinks);
        //System.out.println("First link :"+ trafficLightLinks.getFirst());
        //System.out.println(conn.dojobget(Lane.getShape("28378604_0")));
        int numberOfTrafficLights = (int)conn.dojobget(Trafficlight.getIDCount());
        System.out.println("Number of Traffic Lights: " + numberOfTrafficLights);

        List<String> trafficLightIDs = (List<String>)conn.dojobget(Trafficlight.getIDList());
        System.out.println("List of Traffic Lights: " + trafficLightIDs);

        List<String> controlledjunctions = (List<String>)conn.dojobget(Trafficlight.getControlledJunctions(trafficLightIDs.get(0)));
        System.out.println("Controlled junctions by that traffic light:"+ controlledjunctions);

        List<String> controlledLanes = (List<String>) conn.dojobget(Trafficlight.getControlledLanes(trafficLightIDs.get(0)));
        System.out.println("List of Controlled Lanes: " + controlledLanes);

        SumoLinkList controlledLinks = (SumoLinkList) conn.dojobget(Trafficlight.getControlledLinks(trafficLightIDs.get(0)));
        for(int i= 0; i<(int)controlledLinks.size(); i++){
            System.out.println("List of Controlled Links: " + controlledLinks.get(i));
        }

        String trafficLightState = (String)conn.dojobget(Trafficlight.getRedYellowGreenState(trafficLightIDs.get(0)));
        System.out.println("State of first traffic light: " + trafficLightState);

        System.out.println("Location of lane 28378604_0: " + conn.dojobget(Lane.getShape("28378604_0")));
        //Last coordinates on Lanes "going from" are those where traffic lights should be placed. In this case: 85.42, 107.99 since
        //its the first in List of controlled links.

        for (int step = 0; step < 10000; step++) {
            conn.step();

            System.out.println("step number " + step + ". Number of vehicles in simulation: " + conn.getVehicles().size());
            System.out.println("List of cars in simulation: " + conn.getVehicles());

            TimeUnit.MILLISECONDS.sleep(100000);
        }

        conn.stopConnection();
        System.out.println("Connection closed.");
    }
}
