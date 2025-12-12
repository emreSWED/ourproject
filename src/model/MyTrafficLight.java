package model;
import de.tudresden.sumo.cmd.Trafficlight;
import it.polito.appeal.traci.SumoTraciConnection;

public class MyTrafficLight {
    private String id;
    private SumoTraciConnection conn;

    public MyTrafficLight(String id, SumoTraciConnection conn){
        this.id = id;
        this.conn = conn;
    }

    public String getId(){
        return id;
    }

    public String getState(){
        try{
            return (String) conn.do_job_get(Trafficlight.getRedYellowGreenState(this.id));
        }catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

    //setProg

    //setPhase
}
