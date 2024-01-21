package nl.novi.eindopdracht.Courses.Game.Dto.Dto;

import jakarta.persistence.*;
//import nl.novi.eindopdracht.Courses.Models.Course;
import nl.novi.eindopdracht.Courses.Game.Dto.Models.Information;

import java.util.List;

public class SubjectInputDto {

    private String nameSubject;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "course_id")
//    private Course course;

    @OneToMany(mappedBy = "subject")
    private List<Information> informationList;

    public SubjectInputDto(String nameSubject, List<Information> informationList) {
        this.nameSubject = nameSubject;
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

    public List<Information> getInformationList() {
        return informationList;
    }

    public void setInformationList(List<Information> informationList) {
        this.informationList = informationList;
    }

}
