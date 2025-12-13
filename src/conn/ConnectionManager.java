package conn;

import de.tudresden.sumo.cmd.*;
import de.tudresden.sumo.util.*;
import it.polito.appeal.traci.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.MyVehicle;
import model.MyTrafficLight;

public class ConnectionManager {
    private String configFile;
    public SumoTraciConnection traciConnection;

    public ConnectionManager(String configFile) {
        this.configFile = configFile;
        this.traciConnection = new SumoTraciConnection("sumo-gui", this.configFile);
    }

    public Object dojobget(SumoCommand cmd) throws Exception {

        return this.traciConnection.do_job_get(cmd);
    }

    public void dojobset(SumoCommand cmd) throws Exception {
        this.traciConnection.do_job_set(cmd);
    }

    public void startConnection() throws IOException {
        traciConnection.addOption("start","true");
        traciConnection.runServer();
    }

    public void stopConnection() {
        traciConnection.close();
    }

    public void step() throws Exception {
        traciConnection.do_timestep();
    }

    public List<MyVehicle> getVehicles() throws Exception {
        List<String> vehicles = (List<String>) traciConnection.do_job_get(Vehicle.getIDList());
        List<MyVehicle> myVehicles = new ArrayList<MyVehicle>();
        for (String vehicleID : vehicles) {
            MyVehicle v = new MyVehicle(vehicleID, this.traciConnection);
            myVehicles.add(v);
        }
        return myVehicles;
    }

    public List<MyTrafficLight> getTrafficLights() throws Exception {
        List<String> trafficLights = (List<String>) traciConnection.do_job_get(Trafficlight.getIDList());
        List<MyTrafficLight> mytrafficLights = new ArrayList<MyTrafficLight>();
        for (String trafficLightID : trafficLights) {
            MyTrafficLight t = new MyTrafficLight(trafficLightID, this.traciConnection);
            mytrafficLights.add(t);
        }
        return mytrafficLights;
    }
}