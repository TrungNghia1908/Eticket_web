package bean;

import java.io.Serializable;

public class User implements Serializable{
    
    private int userId;
    private String fullName;
    private String email;
    private String userName;
    private String password;
    private String hashPass;
    private String staltPass;
    private int phoneNumber;
    
    public User() {}
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int id) {
        this.userId = id;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName= userName;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public int getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getHashPass() {
        return this.hashPass;
    }
    
    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }
    
    public String getStaltPass() {
        return this.staltPass;
    }
    
    public void setStaltPass(String hashPass) {
        this.hashPass = hashPass;
    }
}
