package DAOTest;

import bean.Seat;
import java.sql.*;
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
    
    public static String checkSeat(Seat seat, String status) throws ClassNotFoundException {
        DBconnect con = new DBconnect();
        Connection connection = con.con;
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        String query = "UPDATE seat SET Status = ?, Pin_code = ? "
                        + "WHERE  Seat_no = ? AND Bus_id = ?";
        try {
                ps = connection.prepareStatement(query);
                ps.setString(1, status);
                ps.setString(2, seat.getPinCode());
                ps.setString(3, seat.getSeatNo());
                ps.setInt(4, seat.getBusId());
                ps.executeUpdate();
                
                return ps.toString();
        } catch(SQLException e) {
            System.err.println(e);
            return e.toString();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
        }
    }
    
    public static List<Seat> selectSeats(String pinCode) throws ClassNotFoundException {

         DBconnect con = new DBconnect();
        Connection connection = con.con;
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM seat WHERE Pin_code= ?";
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
        }
    }
    
    public static void main(String[] agrs) throws ClassNotFoundException {
//        List<Seat> seats = SeatDAO.selectSeats(10);
//        Seat seat = seats.get(0);
//        System.out.println(seat.getBusId());
//        System.out.println(seat.getSeatId());
//        System.out.println(seat.getSeatNo());
//        System.out.println(seat.getTicketId());
//        System.out.println(seat.getStatus());
//        checkSeat(seat, "Y");
//        seat = seats.get(0);
//        System.out.println(seat.getBusId());
//        System.out.println(seat.getSeatId());
//        System.out.println(seat.getSeatNo());
//        System.out.println(seat.getTicketId());
//        System.out.println(seat.getStatus());
    }
}
