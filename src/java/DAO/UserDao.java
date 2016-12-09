package DAO;

import bean.User;
import java.sql.*;

public class UserDao {
    
    public static void insert (User user) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "INSERT INTO customer "
                    + "(user_name, Cus_email, Cus_name, Cus_pass, Cus_phone) "
                    + "VALUES (?, ?, ?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhoneNumber());
            
            ps.executeUpdate();
        } catch (SQLException e) {
           System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static User selectUser (String email, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * "
                + "FROM customer "
                + "WHERE Cus_email= ?"
                + "AND Cus_pass = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setEmail(rs.getString("Cus_email"));
                u.setFullName(rs.getString("Cus_name"));
                u.setPoneNumber(rs.getString("Cus_phone"));
                u.setUserName(rs.getString("user_name"));
                u.setPassword(rs.getString("Cus_pass"));
                return u;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
    
    public static boolean emailExist(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT Cus_email "
                + "FROM customer WHERE Cus_email = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static boolean userNameExist(String userName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT user_name "
                    + "FROM customer "
                    + "WHERE user_name= ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static boolean removeUser(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM customer "
                + "WHERE Cus_email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            
            // executeUpdate return 0 when delete query is false
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            DBUtil.closeStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static boolean updateInfo(User user) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE customer "
                + "SET user_name = ?, Cus_name= ?, Cus_phone =?"
                + "WHERE  Cus_email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getEmail());
            
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
