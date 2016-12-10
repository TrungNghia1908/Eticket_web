package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author A Di Đà Phật
 */
public class UserList implements Serializable {
    
    private List<User> users;
    
    public UserList() {
        users = new ArrayList<>();
    }
    
    public void setUsers (List<User> users) {
        this.users = users;
    }
    
    public List<User> getUsers() {
        return this.users;
    }
}
