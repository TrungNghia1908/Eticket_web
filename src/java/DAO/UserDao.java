package DAO;

import bean.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

public class UserDao {
    
    public static String insert (User user) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "INSERT INTO customer "
                    + "(User_name, Cus_email, Cus_name, Cus_pass, Cus_phone) "
                    + "VALUES (?, ?, ?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getPhoneNumber());
            
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
    
    public static User select (String email, String password) {
        
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
                u.setUserId(rs.getInt("Cus_id"));
                u.setEmail(rs.getString("Cus_email"));
                u.setFullName(rs.getString("Cus_name"));
                u.setPoneNumber(rs.getInt("Cus_phone"));
                u.setUserName(rs.getString("User_name"));
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
    
    public static User select (int userId) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * "
                + "FROM customer "
                + "WHERE Cus_id= ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
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
    
    /**
     * select email from customer. 
     * if result = 0 (email not exist in database)
     *  return false
     * else return true
     */
    
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
    
    // Same idea with emailExit
    public static boolean userNameExist(String userName) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT User_name "
                    + "FROM customer "
                    + "WHERE User_name= ?";
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
    
    // Using email to delete customer in customer table
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
                + "SET User_name = ?, Cus_name= ?, Cus_phone =? "
                + "WHERE  Cus_email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getFullName());
            ps.setInt(3, user.getPhoneNumber());
            ps.setString(4, user.getEmail());
            
            return  ps.executeUpdate() != 0;
//                    
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List<User> select () {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM customer";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            List<User> users = new ArrayList<>();
            while(rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("Cus_id"));
                u.setUserName(rs.getString("User_name"));
                u.setEmail(rs.getString("cus_email"));
                u.setFullName(rs.getString("Cus_name"));
                u.setPassword(rs.getString("Cus_pass"));
                u.setPoneNumber(rs.getInt("Cus_phone"));
                
                users.add(u);
            }
            return users;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
