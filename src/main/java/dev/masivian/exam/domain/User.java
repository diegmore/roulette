package dev.masivian.exam.domain;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
public class User implements Serializable{
    @Id
    private String userId;
    private int credit;
    public int getCredit() {

        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }
    public String getUserId() {
        
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}