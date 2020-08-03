package dev.masivian.exam.response;
import dev.masivian.exam.domain.Roulette;
public class UpdateResponse {
    private String id;
    private Roulette roulette;
    public String getId() {

        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Roulette getRoulette() {
        
        return roulette;
    }
    public void setRoulette(Roulette roulette) {
        this.roulette = roulette;
    }
}