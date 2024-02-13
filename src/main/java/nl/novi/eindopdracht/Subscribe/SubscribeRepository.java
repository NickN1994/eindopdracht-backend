package nl.novi.eindopdracht.Subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    int countByActivityId(Long activityId);

    Optional<Subscribe> findByUserUsernameAndActivityId(String username, Long activityId);
}
