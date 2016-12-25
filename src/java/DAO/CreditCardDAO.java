package DAO;

import bean.CreditCard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author A Di Đà Phật
 */
public class CreditCardDAO {
    
    public static CreditCard selsect(int cardId) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM credit_card WHERE Card_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, cardId);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                CreditCard card = new CreditCard();
                card.setCardId(rs.getInt("Card_id"));
                card.setAmount(rs.getInt("Amount"));
                return card;
            }
            return null;
        } catch (SQLException e) {
            System.out.print(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
    
    public static boolean payFee(int cardId, int pay) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "UPDATE credit_card SET Amount= Amount - ? "
                        + "WHERE Card_id= ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pay);
            ps.setInt(2, cardId);
            
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.print(e);
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
    
//    public static void main (String[] args) throws ClassNotFoundException {
//        
//        CreditCard card = CreditCardDAO.selsect(555555);
//        System.out.println("Id: " + card.getCardId());
//        System.out.println("Amount: " + card.getAmount());
//        
//        CreditCardDAO.payFee(555555, 1000);
//        
//        card = CreditCardDAO.selsect(555555);
//        System.out.println("Id: " + card.getCardId());
//        System.out.println("Amount: " + card.getAmount());
//        
//    }
}
