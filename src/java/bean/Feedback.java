package bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author A Di Đà Phật
 */
public class Feedback implements Serializable {
    
    private int feedbackId;
    private User user;
    private String subject;
    private String comment;
    private Date feedbackDate;
    
    public Feedback() {
        user = null;
        feedbackDate = new Date();
    } 
    
    public int getFeedbackId() {
        return feedbackId;
    }
    
    public void setFeedbackId (int feedbackId) {
        this.feedbackId = feedbackId;
    }
    
    public User getUser() {
        return this.user;
    } 
    
    public void setUser (User user) {
        this.user = user;
    }
    
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject (String subject) {
        this.subject = subject;
    }
    
    public String getComment () {
        return this.comment;
    }
    
    public void setComment (String comment) {
        this.comment = comment;
    }
    
    public Date getFeedbackDate () {
        return this.feedbackDate;
    }
    
    public void setFeedbackDate (Date date) {
        this.feedbackDate = date;
    }
}
