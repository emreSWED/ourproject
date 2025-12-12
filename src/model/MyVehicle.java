package model;

import de.tudresden.sumo.cmd.Vehicle;
import de.tudresden.sumo.objects.SumoPosition2D;
import it.polito.appeal.traci.SumoTraciConnection;

public class MyVehicle {
    private String id;
    private SumoTraciConnection conn;

    public MyVehicle(String id, SumoTraciConnection conn){
        this.id = id;
        this.conn = conn;
    }

    public String getId(){
        return id;
    }

    public double getX(){
        try{
            SumoPosition2D pos = (SumoPosition2D) conn.do_job_get(de.tudresden.sumo.cmd.Vehicle.getPosition(this.id));
            return pos.x;
        }catch(Exception e){
            return -1.0;
        }
    }

    public double getY(){
        try{
            SumoPosition2D pos = (SumoPosition2D) conn.do_job_get(de.tudresden.sumo.cmd.Vehicle.getPosition(this.id));
            return pos.y;
        }catch(Exception e){
            return -1.0;
        }
    }

    public double getSpeed(){
        try{
            return (double) conn.do_job_get(de.tudresden.sumo.cmd.Vehicle.getSpeed(this.id));
        }catch(Exception e){
            return 0.0;
        }
    }

    public void setSpeed(double speedMetersPerSecond){
        try{
            conn.do_job_get(de.tudresden.sumo.cmd.Vehicle.setSpeed(this.id, speedMetersPerSecond));
            System.out.println("Fahrzeug " + id + " auf " + speedMetersPerSecond + " m/s gesetzt.");
        }catch(Exception e){
            System.out.println("Fehler" + id);

        }
    }

    //setColor soon...

}
