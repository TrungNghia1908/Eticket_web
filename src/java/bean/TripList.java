package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author A Di Đà Phật
 */
public class TripList {
    
    private List<Trip> trips;
    
    public TripList() {
        trips = new ArrayList<>();
    }
    
    public void setTrip (List<Trip> trips) {
        this.trips = trips;
    }
    
    public List<Trip> getTrips() {
        return trips;
    }
    
    public void add(Trip trip) {
        trips.add(trip);
    }
    
    public boolean isEmpty () {
        return trips.isEmpty();
    }
}
