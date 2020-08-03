package dev.masivian.exam.repository;
import java.util.Map;
import dev.masivian.exam.domain.Roulette;
import dev.masivian.exam.response.UpdateResponse;
public interface RedisRepository {
    String save(Roulette roulette);
    Map<String, Roulette> findAll();
    Roulette findById(String id);
    void update(UpdateResponse updateResponse);
}
