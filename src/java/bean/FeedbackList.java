package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author A Di Đà Phật
 */
public class FeedbackList implements Serializable {
    
    private List<Feedback> feedbacks;
    
    public FeedbackList() {
        feedbacks = new ArrayList<>();
    }
    
    public void setFeedbacks (List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    
    public List<Feedback> getFeedbacks() {
        return this.feedbacks;
    }
}
