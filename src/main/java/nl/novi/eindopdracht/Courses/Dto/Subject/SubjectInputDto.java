package nl.novi.eindopdracht.Courses.Dto.Subject;

import jakarta.persistence.*;
import nl.novi.eindopdracht.Courses.Models.Course;
import nl.novi.eindopdracht.Courses.Models.Information;

import java.util.List;

public class SubjectInputDto {

    private String nameSubject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "subject")
    private List<Information> informationList;

    public SubjectInputDto(String nameSubject, Course course, List<Information> informationList) {
        this.nameSubject = nameSubject;
        this.course = course;
        this.informationList = informationList;
    }

    public SubjectInputDto(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public SubjectInputDto() {
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
