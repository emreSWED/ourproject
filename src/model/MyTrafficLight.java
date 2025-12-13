package model;
import de.tudresden.sumo.cmd.Trafficlight;
import it.polito.appeal.traci.SumoTraciConnection;

import java.util.List;

public class MyTrafficLight {
    private String id;
    private SumoTraciConnection conn;

    public MyTrafficLight(String id, SumoTraciConnection conn) {
        this.id = id;
        this.conn = conn;
    }

    public String getId(){
        return id;
    }

    public List<String> getControlledJunctions() throws Exception {
        return (List<String>) this.conn.do_job_get(Trafficlight.getControlledJunctions(this.id));
    }

    public List<String> getControlledLanes() throws Exception {
        return (List<String>) this.conn.do_job_get(Trafficlight.getControlledLanes(this.id));
    }

    public List<String> getControlledLinks() throws Exception {
        return (List<String>) this.conn.do_job_get(Trafficlight.getControlledLinks(this.id));
    }

    public String getState() throws Exception {
        return (String) this.conn.do_job_get(Trafficlight.getRedYellowGreenState(this.id));
    }

    //setProg

    //setPhase
}
