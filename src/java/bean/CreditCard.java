package bean;

import java.io.Serializable;

/**
 * @author A Di Đà Phật
 */
public class CreditCard implements Serializable {
    private int cardId;
    private int amount;
    
    public int getCardId() {
        return this.cardId;
    }
    
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
