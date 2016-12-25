package DAO;

import bean.Seat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author A Di Đà Phật
 */
public class SeatDAO {
    public static List<Seat> selectSeatMap(int busId) {

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
            pool.freeConnection(connection);
        }
    }
    
    public static String checkSeat(Seat seat) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        String query = "UPDATE seat SET Status = 'Y', Pin_code = ? "
                        + "WHERE  Seat_no = ? AND Bus_id = ?";
        try {
                ps = connection.prepareStatement(query);
                ps.setString(1, seat.getPinCode());
                ps.setString(2, seat.getSeatNo());
                ps.setInt(3, seat.getBusId());
                ps.executeUpdate();
                
                return ps.toString();
        } catch(SQLException e) {
            System.err.println(e);
            return e.toString();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
    
    public static String resetSeat(Seat seat) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        String query = "UPDATE seat SET Status = 'N', Pin_code = '0' "
                        + "WHERE  Seat_no = ? AND Bus_id = ?";
        try {
                ps = connection.prepareStatement(query);
                ps.setString(1, seat.getSeatNo());
                ps.setInt(2, seat.getBusId());
                ps.executeUpdate();
                
                return ps.toString();
        } catch(SQLException e) {
            System.err.println(e);
            return e.toString();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
    
    public static List<Seat> selectSeats(String pinCode) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM seat WHERE Pin_code = ?";
        try {
                ps = connection.prepareStatement(query);
                ps.setString(1, pinCode);
                rs = ps.executeQuery();
                List<Seat> seats = new ArrayList<>();
                
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
            pool.freeConnection(connection);
        }
    }
}
