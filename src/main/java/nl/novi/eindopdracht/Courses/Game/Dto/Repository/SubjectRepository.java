package nl.novi.eindopdracht.Courses.Game.Dto.Repository;

import nl.novi.eindopdracht.Courses.Game.Dto.Models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findByNameSubject(String nameSubject);

}
