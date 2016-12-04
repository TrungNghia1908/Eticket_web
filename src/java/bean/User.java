package bean;

public class User {
    
    private String fullName;
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
    
    public User() {}
    
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
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
