package bean;

import java.io.Serializable;

/**
 * @author A Di Đà Phật
 */
public class Trip implements Serializable {
    
    private int tripId;
    private String arrival;
    private String destination;
    private int price;
    
    public Trip () {
        
    }
    
    public int getTripId() {
        return tripId;
    }
    
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }
    
    public String getArrival() {
        return arrival;
    }
    
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
    
    public String getDestination () {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public int getPrice () {
        return price;
    }
    
    public void setPrice (int price) {
        this.price = price;
    }
}
