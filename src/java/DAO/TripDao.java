package DAO;

import java.sql.*;
import bean.Trip;
import bean.TripList;
import java.util.ArrayList;
import java.util.List;

/**
 * @author A Di Đà Phật
 */
public class TripDao {
    
    public static void insert (Trip trip) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
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
            pool.freeConnection(connection);
        }               
    }
    
    public static TripList select(String tripName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
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
            TripList trips = new TripList();
            while (rs.next()) {
                Trip t = new Trip();
                t.setTripId(rs.getInt("TripID"));
                t.setArrival(rs.getString("Arrival"));
                t.setDestination(rs.getString("Destination"));
                t.setPrice(rs.getInt("Price"));
                trips.add(t);
            }
            return trips;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Trip select(int tripId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
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
            pool.freeConnection(connection);
        }
    }
    
    public static int updateTrip(int tripId,  int price) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
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
            pool.freeConnection(connection);
        }       
    }
    
    public static int removeTrip(int tripId) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
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
            pool.freeConnection(connection);
        }
    }
}