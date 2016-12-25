package DAO;

import bean.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author A Di Đà Phật
 */
public class ReportDAO {
    
    public static Workbook getUserList() {
        
        // create the workbook, its worksheet, and its title row
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("User Report");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("The Customer List");
        
        // create the header row
        row = sheet.createRow(2);
        row.createCell(0).setCellValue("STT");
        row.createCell(1).setCellValue("Full Name");
        row.createCell(2).setCellValue("Email");
        row.createCell(3).setCellValue("User Name");
        row.createCell(4).setCellValue("Phone Number");
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM customer ";
        
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            int i = 3;
            while(rs.next()) {
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(rs.getInt("Cus_id"));
                row.createCell(1).setCellValue(rs.getString("User_name"));
                row.createCell(2).setCellValue(rs.getString("cus_email"));
                row.createCell(3).setCellValue(rs.getString("Cus_name"));
                row.createCell(4).setCellValue(rs.getInt("Cus_phone"));
                i++;
            }
            return workbook;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
        }
    }
    
    public static Workbook getFeedbackList() {
        
        // create the workbook, its worksheet, and its title row
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Feedback Report");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("The Feeback List");
        
        // create the header row
        row = sheet.createRow(2);
        row.createCell(0).setCellValue("User Name");
        row.createCell(1).setCellValue("Email");
        row.createCell(2).setCellValue("Subject");
        row.createCell(3).setCellValue("Comment");
        row.createCell(4).setCellValue("Date Created");
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM feedback";
        
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            int i = 3;
            while (rs.next()) {
                row = sheet.createRow(i);
                row.createCell(3).setCellValue(rs.getString("Comment"));
                row.createCell(2).setCellValue(rs.getString("Subject"));
                row.createCell(4).setCellValue(rs.getTimestamp("Date_feedback").toLocaleString());
                
                User u = UserDAO.select(rs.getInt("Cus_id"));
                row.createCell(1).setCellValue(u.getEmail());
                row.createCell(0).setCellValue(u.getFullName());
                i++;
            }
            return workbook;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeStatement(statement);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
    }
}
