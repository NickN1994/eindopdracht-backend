package nl.novi.eindopdracht.Courses.Dto.Subject;

import jakarta.persistence.*;
import nl.novi.eindopdracht.Courses.Models.Course;
import nl.novi.eindopdracht.Courses.Models.Information;

import java.util.List;

public class SubjectOutputDto {

    @Id
    @GeneratedValue
    private Long id;

    private String nameSubject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "subject")
    private List<Information> informationList;

    public SubjectOutputDto(Long id, String nameSubject, Course course, List<Information> informationList) {
        this.id = id;
        this.nameSubject = nameSubject;
        this.course = course;
        this.informationList = informationList;
    }

    public SubjectOutputDto(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public SubjectOutputDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Information> getInformationList() {
        return informationList;
    }

    public void setInformationList(List<Information> informationList) {
        this.informationList = informationList;
    }


}
