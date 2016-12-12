package DAO;

import bean.User;
import java.sql.*;

/**
 *
 * @author A Di Đà Phật
 */
public class TestDAO {
    
    private static User select(int id) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
         
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * "
                + "FROM customer "
                + "WHERE Cus_id= ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            // Because of selecting by id,
            //we get only one row (1 customer) or null
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("Cus_id"));
                u.setEmail(rs.getString("Cus_email"));
                u.setFullName(rs.getString("Cus_name"));
                u.setPoneNumber(rs.getInt("Cus_phone"));
                u.setUserName(rs.getString("User_name"));
                u.setPassword(rs.getString("Cus_pass"));
                return  u;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        
        System.out.println(select(1));
    }
    
}
