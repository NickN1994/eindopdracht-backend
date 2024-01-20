package nl.novi.eindopdracht.Courses.Repository;

import nl.novi.eindopdracht.Courses.Models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
