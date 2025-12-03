
import de.tudresden.sumo.cmd.*;
import de.tudresden.sumo.util.*;
import it.polito.appeal.traci.*;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import conn.ConnectionManager;

public class Main {
    public static void main(String[] args) throws Exception {
        ConnectionManager conn = new ConnectionManager("ourproject/SumoConfig/myconfig.sumocfg");
        conn.startConnection();

        for (int step = 0; step < 10000; step++) {
            conn.step();
            List<String> vehicles = conn.getVehicles();
            List<String> trafficLights = conn.getTrafficLights();
            System.out.println("step number " + step + " Number of vehicles in simulation: " + vehicles.size());
            System.out.println("current traffic lights: " + trafficLights.size());
            System.out.println("ID of Traffic lights:"+ trafficLights);
            //trafficLights.forEach(System.out::println);
            //System.out.println(trafficLights.toString());
            TimeUnit.MILLISECONDS.sleep(50);
        }

        conn.stopConnection();
        System.out.println("Connection closed.");
    }
}
