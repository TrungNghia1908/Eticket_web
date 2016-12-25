package DAOTest;

import bean.CreditCard;
import java.sql.*;

/**
 * @author A Di Đà Phật
 */
public class CreditCardDAO {
    
    public static CreditCard selsect(int cardId) throws ClassNotFoundException {
        
        DBconnect con = new DBconnect();
        Connection connection = con.con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM credit_card WHERE Card_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, cardId);
            
            rs = ps.executeQuery();
            CreditCard card = new CreditCard();
            if (rs.next()) {
                card.setCardId(rs.getInt("Card_id"));
                card.setAmount(rs.getInt("Amount"));
            }
            return card;
        } catch (SQLException e) {
            System.out.print(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
        }
    }
    
    public static boolean payFee(int cardId, int Amount) throws ClassNotFoundException {
        
        DBconnect con = new DBconnect();
        Connection connection = con.con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "UPDATE credit_card SET Amount= Amount - 1000 "
                        + "WHERE Card_id= ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, cardId);
            
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.out.print(e);
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
        }
    }
    
    public static void main (String[] args) throws ClassNotFoundException {
        
        CreditCard card = CreditCardDAO.selsect(32351);
        System.out.println("Id: " + card.getCardId());
        System.out.println("Amount: " + card.getAmount());
        
//        CreditCardDAO.payFee(555555, 1000);
//        
//        card = CreditCardDAO.selsect(555555);
//        System.out.println("Id: " + card.getCardId());
//        System.out.println("Amount: " + card.getAmount());
        
    }

}
