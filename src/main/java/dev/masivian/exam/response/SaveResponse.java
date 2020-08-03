package dev.masivian.exam.response;
import java.io.Serializable;
public class SaveResponse implements Serializable{
    private String status;
    private String created_id;
    public String getCreated_id() {

        return created_id;
    }
    public void setCreated_id(String created_id) {
        this.created_id = created_id;
    }
    public String getStatus() {
        
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}