package nl.novi.eindopdracht.Courses.Models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    private String nameSubject;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "subject")
    private List<Information> informationList;

    public Subject(Long id, String nameSubject, Course course, List<Information> informationList) {
        this.id = id;
        this.nameSubject = nameSubject;
        this.course = course;
        this.informationList = informationList;
    }

    public Subject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Subject() {
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
