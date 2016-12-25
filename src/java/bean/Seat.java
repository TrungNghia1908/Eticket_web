package bean;

import java.io.Serializable;

/**
 * @author A Di Đà Phật
 */
public class Seat implements Serializable {
    
    private int seatId;
    private String seatNo;
    private String status = "N";
    private String pinCode;
    private int busId;
    
    
    public int getSeatId() {
        return this.seatId;
    }
    
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
    
    public String getSeatNo() {
        return this.seatNo;
    }
    
    public void setSetNo(String seatNo) {
        this.seatNo = seatNo;
    }
    
    public int getBusId() {
        return this.busId;
    }
    
    public void setBusId(int busId) {
        this.busId = busId;
    }
    
    public String getPinCode() {
        return this.pinCode;
    }
    
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
