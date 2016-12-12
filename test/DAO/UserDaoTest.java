package DAO;

import bean.User;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.PasswordUtil;

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

public class UserDaoTest {
    
    public static String insert (User user) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "INSERT INTO customer "
                    + "(User_name, Cus_email, Cus_name, "
                    + "Cus_phone, Cus_pass, Cus_hashPass) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getFullName());
            ps.setInt(4, user.getPhoneNumber());
            ps.setString(5, user.getPassword());
//            ps.setString(7, user.getStaltPass());
            String hash = user.getHashPass();
            ps.setString(6, hash);
            
            ps.executeUpdate();
            return "success full";
        } catch (SQLException e) {
           Logger.getLogger(UserDaoTest.class.getName()).log(Level.SEVERE, null, e);
           return e.getMessage();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
        }
    }
    
    public static User select (String email, String password) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
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
        }
    }
    
    public static User select (int userId) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
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
        }
    }
    
    public static boolean emailExist(String email) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
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
        }
    }
    
    // Same idea with emailExit
    public static boolean userNameExist(String userName) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
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
        }
    }
    
    // Using email to delete customer in customer table
    public static boolean removeUser(String email) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
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
        }
    }
    
    public static boolean updateInfo(User user) throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
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
        }
    }
    
    public static List<User> select () throws ClassNotFoundException {
        
        DBconnect DBconnect = new DBconnect();
        Connection connection = DBconnect.con;
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
        }
    }
    
    public static void main(String[] args) {
        
            User u = new User();
            u.setEmail("tuthapduoc@gmail.com");
            u.setFullName("Pham Trong Yem");
            u.setPoneNumber(123455);
            u.setUserName("NghiaLun");
            u.setPassword("adidaphat");
            
        try {
//            String salt = PasswordUtil.getSalt();
            String hash =PasswordUtil.hashPassword("adidaphat");
//            u.setStaltPass(salt);
            u.setHashPass(hash);
//            System.out.println("Stalt: " + salt);
            System.out.println("hashStalt: " + u.getHashPass());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        try {    
            insert(u);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
