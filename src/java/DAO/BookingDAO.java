package DAO;

import bean.Bus;
import bean.Seat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author A Di Đà Phật.
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

public class BookingDAO {
    
    public static Bus selectBus (int tripId, Time runTime) throws ClassNotFoundException {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        String query = "SELECT * "
                + "FROM bus "
                + "WHERE trip_id = ? AND Run_time = ?";
        try {
            ps= connection.prepareStatement(query);
            ps.setInt(1, tripId);
            ps.setTime(2, runTime);
            
            rs = ps.executeQuery();
            
            Bus bus =  new Bus();
            if(rs.next()) {
                
                // set BusNo into bus
                bus.setBusNo(rs.getInt("Bus_no"));
                //set Driver_name
                bus.setDriverName(rs.getString("Driver_name"));
                // select Trip by tripId and set trip in bus.
                bus.setTrip(TripDAO.select(tripId));
                //set runtime
                bus.setRuntime(rs.getTime("Run_time"));
                // set busId into bus
                int busId = rs.getInt("Bus_id");
                bus.setBusId(busId);

                // select all seats in bus by busId and seat to Bus
                List<Seat> seats = BookingDAO.selectSeats(busId);
                bus.setSeats(seats);
            }
            
            
            return bus;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
    
    public static List selectRunTime(int tripId) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM bus WHERE trip_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, tripId);
            rs= ps.executeQuery();
            List<Time> runTime = new ArrayList<>();
            while(rs.next()) {
                runTime.add(rs.getTime("Run_time"));
            }
            return runTime;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        
    }
    
    public static List<Seat> selectSeats(int busId) throws ClassNotFoundException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM seat WHERE Bus_id = ?";
        try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, busId);
                rs = ps.executeQuery();
                List<Seat> seats = new ArrayList<>(20);
                while(rs.next()) {
                    Seat s = new Seat();
                    s.setSetNo(rs.getString("Seat_no"));
                    s.setBusId(rs.getInt("Bus_id"));
                    s.setPinCode(rs.getString("Pin_code"));
                    seats.add(s);
                }
                return seats;
        } catch(SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
}
