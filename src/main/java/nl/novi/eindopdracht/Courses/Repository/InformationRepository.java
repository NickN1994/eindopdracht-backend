package nl.novi.eindopdracht.Courses.Repository;

import nl.novi.eindopdracht.Courses.Models.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Long> {

}
