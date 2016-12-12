package DAO;

import DAO.*;
import java.sql.*;
import bean.Trip;
import bean.TripList;

/**
 * @author A Di Đà Phật.
 * 
 * In the any DAO class in this project we follow these step:
 * 1. Get connection.
 *  1.1. Using ConnectionPool class to get a connection Object (getInstance());
 *  1.2. Get connection.
 * 2. Create query
 * 3. Prepared query (replace ?)
 * 4. execute query (using executeQuery using for UPDATE, INSERT, DELETE)
 * 5. Store the result to bean or send signal success execute.
 * 6. Close preparedStatement, Result and Connection.
 */

public class TripDao {
    
    public static void insert (Trip trip) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO trip (Arrival, Destination, Price)"
                        + "VALUES (?, ?, ?)"; 
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, trip.getArrival());
            ps.setString(2, trip.getDestination());
            ps.setInt(3, trip.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
        }               
    }
    
    public static TripList select(String tripName) throws ClassNotFoundException {
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM trip "
                        + "WHERE arrival = ? "
                        + "OR  destination = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, tripName);
            ps.setString(2, tripName);
            rs = ps.executeQuery();
            
            // Because the result (rs) is the list of trip 
            // so creating a list to store result.
            TripList trips = new TripList();
            while (rs.next()) {
                // Create trip to store each row of the result.
                Trip t = new Trip();
                t.setTripId(rs.getInt("TripID"));
                t.setArrival(rs.getString("Arrival"));
                t.setDestination(rs.getString("Destination"));
                t.setPrice(rs.getInt("Price"));
                
                // add the trip to the trip List.
                trips.add(t);
            }
            return trips;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }
    
    public static Trip select(int tripId) throws ClassNotFoundException {
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM trip "
                        + "WHERE TripID = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, tripId);
            rs = ps.executeQuery();
            Trip t = new Trip();
            while (rs.next()) {
                t.setTripId(rs.getInt("TripID"));
                t.setArrival(rs.getString("Arrival"));
                t.setDestination(rs.getString("Destination"));
                t.setPrice(rs.getInt("Price"));
            }
            return t;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }
    
    public static int updateTrip(int tripId,  int price) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
        PreparedStatement ps = null;
        
        String query =  "UPDATE trip "
                        + "SET Price =? "
                        + "WHERE TripID =?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, price);
            ps.setInt(2, tripId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }       
    }
    
    public static int removeTrip(int tripId) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
        PreparedStatement ps = null;
        
        String query = "DELETE FROM trip "
                + "WHERE TripID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, tripId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }
    
    public static void main(String[] agrs) {
        
    }
}