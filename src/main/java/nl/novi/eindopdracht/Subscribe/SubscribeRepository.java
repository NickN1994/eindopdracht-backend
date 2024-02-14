package nl.novi.eindopdracht.Subscribe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    int countByActivityId(Long activityId);

    Optional<Subscribe> findByUserUsernameAndActivityId(String username, Long activityId);

    List<Subscribe> findAllByActivityId(Long activityId);

    List<Subscribe> findByUserUsername(String username);

//    List<Subscribe> findByUserUsername(Long userId);

}
