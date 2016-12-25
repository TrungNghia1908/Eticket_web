package DAO;

import bean.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author A Di Đà Phật
 */
public class TicketDAO {
    public static String insert (Ticket ticket) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = 
            "INSERT INTO ticket (Cus_id, Time_available, Num_of_seats,"
                                + "  Ticket_price, Pin_code) "
                                + " VALUES (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            
            ps.setInt(1, ticket.getUser().getUserId());
            ps.setString(2, ticket.getTimeAvailable());
            ps.setInt(3, ticket.getNumOfTicket());
            ps.setInt(4, ticket.getTicketPrice());
            ps.setString(5, ticket.getPinCode());
            
            ps.executeUpdate();
            return ps.toString();
        } catch (SQLException e) {
           System.err.println(e);
           return e.getMessage();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Ticket select(String pinCode) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM ticket WHERE Pin_code = ?";
        try {
            ps =connection.prepareStatement(query);
            ps.setString(1, pinCode);
            
            rs = ps.executeQuery();
            Ticket t = new Ticket();
            if(rs.next()) {
                int cusId  = rs.getInt("Cus_id");
                t.setUser(UserDAO.select(cusId));
                t.setTicketId(rs.getInt("Ticket_id"));
                t.setPinCode(rs.getString("Pin_code"));
                t.setNumOfTicket(rs.getInt("Num_of_seats"));
                t.setTicketPrice(rs.getInt("Ticket_price"));
                t.setDateBooking(rs.getTimestamp("Date_booking").toLocaleString());
                t.setTimeAvailable(rs.getTimestamp("Time_available").toLocaleString());
                return t;
            } else {
                return null;
            }
        } catch(SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static boolean checkTime(String pinCode) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM ticket t WHERE t.Pin_code = ? "
                + "AND Time_available > '2016-12-17 12:00:00'";
        try {
            ps =connection.prepareStatement(query);
            ps.setString(1, pinCode);
            
            rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch(SQLException e) {
            System.err.println(e);
            return true;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static boolean deleteTicket(String pinCode) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM ticket WHERE  Pin_code = ?";
        try {
            ps =connection.prepareStatement(query);
            ps.setString(1, pinCode);
            
            return ps.executeUpdate() != 0;
        } catch(SQLException e) {
            System.err.println(e);
            return true;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static void main (String[] args) throws ClassNotFoundException {
//        TicketDAO.insert();

//    Time time = new Time(10, 30, 00);
//    String date = "2013-12-11";
//    
//    System.out.println(time.toString());
//    System.out.println(date);
//    
//    String dateTime = date + " " + time;
//    System.out.println(dateTime);

    
    }
    
    
}
