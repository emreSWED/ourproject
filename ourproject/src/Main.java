
import de.tudresden.sumo.cmd.*;
import de.tudresden.sumo.objects.*;
import de.tudresden.sumo.util.*;
import it.polito.appeal.traci.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        SumoTraciConnection conn = new SumoTraciConnection("sumo-gui", "ourproject/SumoConfig/myconfig.sumocfg");
        conn.addOption("start","true");
        conn.runServer();

        //get map data for UI
        List<String> trafficLights = (List<String>) conn.do_job_get(Trafficlight.getIDList());
        List<String> lanes = (List<String>) conn.do_job_get(Lane.getIDList());



        for (int step = 0; step < 10000; step++) {
            conn.do_timestep();
            List<String> vehicles = (List<String>) conn.do_job_get(Vehicle.getIDList());

            System.out.println("step number " + step + " Number of vehicles in simulation: " + vehicles.size());
            System.out.println("current traffic lights: " + trafficLights.size());
            System.out.println("ID of Traffic lights:"+ trafficLights);
            System.out.println("ID of Lanes:"+ lanes);
            JunctionLoader junctions = new JunctionLoader(conn);

            System.out.println(junctions.JunctionPositionList);
            //trafficLights.forEach(System.out::println);
            //System.out.println(trafficLights.toString());
            TimeUnit.MILLISECONDS.sleep(1000);
        }

        conn.close();
        System.out.println("Connection closed.");
    }
}
