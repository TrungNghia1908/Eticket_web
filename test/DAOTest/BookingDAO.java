package DAOTest;

import bean.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
//import java.sql.*;
import java.util.*;


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
        
        DBconnect con = new DBconnect();
        Connection connection = con.con;
        PreparedStatement ps = null;
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
//            pool.freeConnection(connection);
        }
    }
    
    public static Bus selectBus (int busId) throws ClassNotFoundException {
        
        DBconnect con = new DBconnect();
        Connection connection = con.con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * "
                + "FROM bus "
                + "WHERE Bus_id = ?";
        try {
            ps= connection.prepareStatement(query);
            ps.setInt(1, busId);
            
            rs = ps.executeQuery();
            
            Bus bus =  new Bus();
            if(rs.next()) {
                
                // set BusNo into bus
                bus.setBusNo(rs.getInt("Bus_no"));
                //set Driver_name
                bus.setDriverName(rs.getString("Driver_name"));
                // select Trip by tripId and set trip in bus.
                bus.setTrip(TripDAO.select(rs.getInt("Trip_id")));
                //set runtime
                bus.setRuntime(rs.getTime("Run_time"));
                // set busId into bus
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
//            pool.freeConnection(connection);
        }
    }
    
    
    public static List selectRunTime(int tripId) throws ClassNotFoundException {
        DBconnect con = new DBconnect();
        Connection connection = con.con;
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
        }
        
    }
    
    public static void insertSeat(int busId) throws ClassNotFoundException {
        DBconnect con = new DBconnect();
        Connection connection = con.con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "INSERT INTO seat (Seat_no, Bus_id) VALUES (?, ?);";
        try {
            for(int i = 1; i <= 20; i++) {
                ps = connection.prepareStatement(query);
                ps.setInt(1, i);
                ps.setInt(2, busId);
                if (ps.executeUpdate()== 1) {
                    System.out.println("insert" + i + "success");
                }
            }
        } catch(SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
        }
    }
    
    public static String checkSeat(int busId, String seatNo, int ticketId) throws ClassNotFoundException {
        
        DBconnect con = new DBconnect();
        Connection connection = con.con;
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        String query = "UPDATE seat SET Status ='Y', Ticket_id = ? "
                        + "WHERE  Seat_no = ? AND Bus_id = ?";
        try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, ticketId);
                ps.setString(2, seatNo);
                ps.setInt(3, busId);
                ps.executeUpdate();
                
                return ps.toString();
        } catch(SQLException e) {
            System.err.println(e);
            return e.toString();
        } finally {
            DAO.DBUtil.closePreparedStatement(ps);
            DAO.DBUtil.closeResultSet(rs);
        }
    }
    
    public static List<Seat> selectSeats(int busId) throws ClassNotFoundException {

//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
        
        DBconnect con = new DBconnect();
        Connection connection = con.con;
        PreparedStatement ps = null;
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
                    s.setStatus(rs.getString("Status"));
                    seats.add(s);
                }
                return seats;
        } catch(SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
//            pool.freeConnection(connection);
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
////        TEST selectSeats(busId)
//        List<Seat> seats = new ArrayList<>();
//        seats = (ArrayList<Seat>) BookingDAO.selectSeats(1);
////        System.out.println(seats);
//        System.out.println(seats.size());
//        for (int i = 0; i < seats.size(); i++){
//            Seat s = seats.get(i);
//                System.out.print("seatNo: " + s.getSeatNo());
//                System.out.print("\tTicketId: " + s.getTicketId());
//                System.out.print("\tBusNo: " + s.getBusId());
//                System.out.print("\t Status: " + s.getStatus());
//                System.out.println();
//        }


////        TEST selectBus(tripId, Runtime)
//            Date time = new Date(12, 12,2006);
//            System.out.println(time);
//        Bus bus = BookingDAO.selectBus(1, time);
//      
//        System.out.println("Bus Id " + bus.getBusId());
//        System.out.println("Bus No: " + bus.getBusId());
//        System.out.println("Driver Name: " + bus.getDriverName());
//        System.out.println("Run Time: " + bus.getRuntime());
//        
//        System.out.println("\nTrip information");
//        Trip trip = bus.getTrip();
//        System.out.println("Arrival: " + trip.getArrival());
//        System.out.println("Destination: " + trip.getDestination());
//        System.out.println("Price: " + trip.getPrice());
//        System.out.println("TripId: " + trip.getTripId());
//        
//        System.out.println("\nseats map: ");
//        List<Seat> seats = bus.getSeats();
//        for (int i = 0; i < seats.size(); i++) {
//            Seat s = seats.get(i);
//            System.out.print("seatNo: " + s.getSeatNo());
//            System.out.print("\tTicketId: " + s.getTicketId());
//            System.out.print("\tBusNo: " + s.getBusId());
//            System.out.print("\t Status: " + s.getStatus());
//            System.out.println();
//             
//        }

//        TEST selectRunTime
//        System.out.println(BookingDAO.selectRunTime(1));

//        TEST insertSeat(busid)
//          BookingDAO.insertSeat(5);


//        for (int i = 1; i < 6 ; i+=2) {
            System.out.println(BookingDAO.checkSeat(3, "10", 3));
//        }
        
    }
}
