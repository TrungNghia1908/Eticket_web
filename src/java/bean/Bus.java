package bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

/**
 * @author A Di Đà Phật
 */
public class Bus implements Serializable {
    
    private int busId;
    private int busNo;
    private Trip trip;
    private String dirverName;
    private Time runTime;
    private List<Seat> seats;
    
    public int getBusId(){
        return this.busId;
    }
    
    public void setBusId(int busId) {
        this.busId = busId;
    }
    
    public int getBusNo () {
        return this.busNo;
    }
    
    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }
    
    public Trip getTrip () {
        return this.trip;
    }
    
    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    
    public String getDriverName () {
        return this.dirverName;
    }
    
    public void setDriverName(String driverName) {
        this.dirverName = driverName;
    }
    
    public void setRuntime (Time runTime) {
        this.runTime = runTime;
    }
    
    public Time getRuntime () {
        return this.runTime;
    }
    
    public List<Seat> getSeats() {
        return this.seats;
    }
    
    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    
    @Override
    public String toString() {
        return "busId: "+busId+" busNo: "+busNo+" trip: "+trip
                +" dirverName: "+dirverName+" runTime: "+runTime+"\n"+
                seats;
    }
}
