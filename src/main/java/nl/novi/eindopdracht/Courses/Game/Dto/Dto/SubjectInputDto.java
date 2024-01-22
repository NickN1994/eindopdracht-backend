package nl.novi.eindopdracht.Courses.Game.Dto.Dto;

import jakarta.persistence.*;
//import nl.novi.eindopdracht.Courses.Models.Course;
import nl.novi.eindopdracht.Courses.Game.Dto.Models.Information;

import java.util.ArrayList;
import java.util.List;

public class SubjectInputDto {

    private String nameSubject;
    private List<Long> informationIds;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "course_id")
//    private Course course;

//    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Information> informationList = new ArrayList<>();

    public SubjectInputDto(String nameSubject, List<Long> informationIds) {
        this.nameSubject = nameSubject;
        this.informationIds = informationIds;
//        this.informationList = informationList;
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

    public List<Long> getInformationIds() {
        return informationIds;
    }

    public void setInformationIds(List<Long> informationIds) {
        this.informationIds = informationIds;
    }



}
