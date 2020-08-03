package dev.masivian.exam.repository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import dev.masivian.exam.domain.Roulette;
import dev.masivian.exam.response.UpdateResponse;
import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;
@Repository
public class RouletteRepository implements RedisRepository {
    private static final String KEY = "Roulette";
    private RedisTemplate<String, Roulette> redisTemplate;
    private HashOperations hashOperations;
    public RouletteRepository(RedisTemplate<String, Roulette> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public String save(Roulette roulette) {
        String id = UUID.randomUUID().toString();
        roulette.setId(id);
        hashOperations.put(KEY, id, roulette);

        return id;
    }
    @Override
    public Map<String, Roulette> findAll() {

        return hashOperations.entries(KEY);
    }
    @Override
    public Roulette findById(String id) {

        return (Roulette) hashOperations.get(KEY, id);
    }
    @Override
    public void update(UpdateResponse updateResponse){
        hashOperations.put(KEY, updateResponse.getId(), updateResponse.getRoulette());
    }
}