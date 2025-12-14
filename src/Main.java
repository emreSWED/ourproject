
import de.tudresden.sumo.cmd.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import loader.RouteGenerator;
import loader.VehicleAdder;
import util.MySystem;
import util.ConnectionManager;

import loader.LaneLoader;
import loader.MyLane;

import model.MyVehicle;
import model.MyTrafficLight;

public class Main {
    public static void main(String[] args) throws Exception {
        ConnectionManager conn = new ConnectionManager("SumoConfig/myconfig.sumocfg");
        conn.startConnection();
        MySystem mySystem = new MySystem(conn);

        //get map data for UI
       // List<String> trafficLights = conn.getTrafficLights();
       // List<String> lanes = (List<String>) conn.dojobget(Lane.getIDList());
       // loader.JunctionLoader junctions = new loader.JunctionLoader(conn.traciConnection);

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
        int numberOfTrafficLights = (int)conn.dojobget(Trafficlight.getIDCount());
        System.out.println("Number of Traffic Lights: " + numberOfTrafficLights);

        List<MyTrafficLight> trafficLights = mySystem.getTrafficLights();
        System.out.println("List of Traffic Lights: " + trafficLights);

        for (MyTrafficLight t : trafficLights) {
            System.out.println("ID: " + t.getId());
            System.out.println("ControlledJunctions: " + t.getControlledJunctions());
            System.out.println("ControlledLanes: " + t.getControlledLanes());
            System.out.println("ControlledLinks: " + t.getControlledLinks());
            System.out.println("State: " + t.getState());
        }

        System.out.println("Location of lane :254384053_11_0: " + conn.dojobget(Lane.getShape(":254384053_11_0")));
        //Last coordinates on Lanes "going from" are those where traffic lights should be placed. In this case: 85.42, 107.99 since
        //its the first in List of controlled links.
        MyLane.conn = conn; //IMPORTANT;
        LaneLoader currentLanes = new LaneLoader(conn);
        //LaneLoader.printAllLaneCoordinates();
        LaneLoader.printAllLaneIDs();

        RouteGenerator.conn = conn;
        RouteGenerator routeGenerator = new RouteGenerator();

        VehicleAdder.conn = conn;
        VehicleAdder vehicleAdder = new VehicleAdder();



        for (int step = 0; step < 10000; step++) {
            conn.step();
            if(step%5==0) vehicleAdder.addRandomVehicle();
            System.out.println("step number " + step + ". Number of vehicles in simulation: " + mySystem.getVehicles().size());
            System.out.println("List of cars in simulation: " + mySystem.getVehicles());

            if (step == 5) {
                //MyTrafficLight t1 = new MyTrafficLight("254384053", conn.traciConnection);
                //t1.setPhase();
                MyVehicle v = mySystem.getVehicles().getFirst();
                v.setSpeed(1.0);
            }

            List<MyVehicle> vehicles = mySystem.getVehicles();
            for (MyVehicle v : vehicles) {
                System.out.println(v.getX() + ", " + v.getY() + ", " + v.getSpeed() + ", " + v.getId());
            }

            TimeUnit.MILLISECONDS.sleep(2000);
        }

        conn.stopConnection();
        System.out.println("Connection closed.");
    }
}
