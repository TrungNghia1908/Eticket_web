package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author A Di Đà Phật
 */
public class Ticket implements Serializable {
    
    private int ticketId;
    private User user;
    private String dateBooking;
    private String timeAvailable;
    private int numOfTicket;
    private int ticketPrice;
    private String pinCode;
    

    public Ticket() {
    }
    
    public int getTicketId() {
        return this.ticketId;
    }
    
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    
    public void setUser (User user) {
        this.user = user;
    }
    
    public User getUser () {
        return this.user;
    }
    
    public void setDateBooking(String dateBooking) {
        this.dateBooking = dateBooking;
    }
    
    public String getDateBooking () {
        return this.dateBooking;
    }
    
    public String getTimeAvailable() {
        return this.timeAvailable;
    }
    
    public void setTimeAvailable(String date, String time) {
        this.timeAvailable = date + " " + time;
    }
    
    public void setTimeAvailable(String TimeAvailable) {
        this.timeAvailable = TimeAvailable;
    }
    
    public int getTicketPrice() {
        return this.ticketPrice;
    }
    
    public void setTicketPrice(int price) {
        this.ticketPrice = price;
    }
    
    public int getNumOfTicket() {
        return this.numOfTicket;
    }
    
    public void setNumOfTicket(int numOfTicket) {
        this.numOfTicket = numOfTicket;
    }
    
    public String getPinCode() {
        return this.pinCode;
    }
    
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
    
        
}
