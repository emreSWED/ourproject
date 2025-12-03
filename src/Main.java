
import de.tudresden.sumo.cmd.*;
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
        List<String> trafficLights = (List<String>) conn.traciConnection.do_job_get(Trafficlight.getIDList());
        List<String> lanes = (List<String>) conn.traciConnection.do_job_get(Lane.getIDList());
        JunctionLoader junctions = new JunctionLoader(conn.traciConnection);

        //github test 17:32
        System.out.println("current number of traffic lights: " + trafficLights.size());
        System.out.println("ID of Traffic lights:"+ conn.getTrafficLights());
        System.out.println("ID of Lanes:"+ lanes);
        System.out.println("ID of Junctions:" + junctions.JunctionPositionList);

        for (int step = 0; step < 10000; step++) {
            conn.step();

            System.out.println("step number " + step + ". Number of vehicles in simulation: " + conn.getVehicles().size());
            System.out.println("List of cars in simulation: " + conn.getVehicles());

            TimeUnit.MILLISECONDS.sleep(1000);
        }

        conn.stopConnection();
        System.out.println("Connection closed.");
    }
}
