package nl.novi.eindopdracht.Courses.Game.Dto.Dto;

import jakarta.persistence.*;

import nl.novi.eindopdracht.Courses.Game.Dto.Models.Information;

import java.util.List;

public class SubjectOutputDto {

    @Id
    @GeneratedValue
    private Long id;

    private String nameSubject;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "course_id")
//    private Course course;

    @OneToMany(mappedBy = "subject")
    private List<Information> informationList;

    public SubjectOutputDto(Long id, String nameSubject, List<Information> informationList) {
        this.id = id;
        this.nameSubject = nameSubject;
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

    public List<Information> getInformationList() {
        return informationList;
    }

    public void setInformationList(List<Information> informationList) {
        this.informationList = informationList;
    }


}
