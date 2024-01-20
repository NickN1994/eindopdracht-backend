package nl.novi.eindopdracht.Courses;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AddEducation {

    @Id
    @GeneratedValue
    Long id;

    private String title;
    private String youTubeEmbedLink;
    private String information;

}
