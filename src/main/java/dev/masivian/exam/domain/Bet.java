package dev.masivian.exam.domain;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
public class Bet implements Serializable {
    @Id
    private int number;
    private int bill;
    private User user;
    public int getBill() {

        return bill;
    }
    public void setBill(int bill) {
        this.bill = bill;
    }
    public int getNumber() {

        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public User getUser() {

        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}