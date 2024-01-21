package nl.novi.eindopdracht.Courses.Game.Dto.Repository;

import nl.novi.eindopdracht.Courses.Game.Dto.Models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
