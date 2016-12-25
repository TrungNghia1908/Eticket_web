/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOTest;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DBconnect {

    private String Data_URL ="jdbc:mysql://localhost:3306/ebusticket";
    private String user="root";
    private String password="sesame";
    public java.sql.Connection con;
    public DBconnect() throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            this.con = DriverManager.getConnection(Data_URL, user, password);
        }
        catch (SQLException e){   
            System.out.println("Something went wrong in data connection");
        }
    }
}
