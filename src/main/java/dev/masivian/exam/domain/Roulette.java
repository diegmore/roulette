package dev.masivian.exam.domain;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
public class Roulette implements Serializable{
    @Id
    private String id;
    private Boolean status;
    private List<Bet> bet;
    public List<Bet> getBet() {

        return bet;
    }
    public void setBet(List<Bet> bet) {
        this.bet = bet;
    }
    public String getId() {

        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Boolean getStatus() {
        
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
}