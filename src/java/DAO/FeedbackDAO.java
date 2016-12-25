package DAO;

import bean.Feedback;
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

public class FeedbackDAO {
    
    
    public static int insert (Feedback feedback) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = 
                "INSERT INTO feedback (Cus_id, Comment, Subject, Date_feedback) "
                + "VALUES (?, ?, ?, NOW())";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, feedback.getUser().getUserId());
            ps.setString(2, feedback.getComment());
            ps.setString(3, feedback.getSubject());
            return ps.executeUpdate();
        } catch (SQLException e){
            System.err.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static List<Feedback> select() {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM feedback";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            // Because the result (rs) is the list of feedback 
            // so creating a list to store result.
            List<Feedback> feedbacks = new ArrayList<>();
            while (rs.next()) {
                // Create feedback to store each row of the result.
                Feedback f = new Feedback();
                f.setFeedbackId(rs.getInt("Feedback_id"));
                f.setComment(rs.getString("Comment"));
                f.setSubject(rs.getString("Subject"));
                f.setFeedbackDate(rs.getTimestamp("Date_feedback"));
                
                /**
                 * How to set the feedback.user?
                 * 1. Select user by userId (userId we get in the result)
                 * and store that result in new User object.
                 * 2. Set that new user object in the feedback object.
                 */ 
                User u = UserDAO.select(rs.getInt("Cus_id"));
                f.setUser(u);
                
                // add the feedback to the feedback list.
                    feedbacks.add(f);
            }
            return feedbacks;
                    //feedbacks;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
}
