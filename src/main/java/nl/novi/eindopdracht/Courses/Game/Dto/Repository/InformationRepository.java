package nl.novi.eindopdracht.Courses.Game.Dto.Repository;

import nl.novi.eindopdracht.Courses.Game.Dto.Models.Information;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InformationRepository extends JpaRepository<Information, Long> {

    Optional<Information> findByTitle(String title);

}
