package nl.novi.eindopdracht.Courses.Game.Dto.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Naam onderwerp is verplicht")
    private String nameSubject;

//    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn(name = "course_id")
//    private Course course;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Information> informationList = new ArrayList<>();

    public Subject(Long id, String nameSubject, List<Information> informationList) {
        this.id = id;
        this.nameSubject = nameSubject;
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

    public List<Information> getInformationList() {
        return informationList;
    }

    public void setInformationList(List<Information> informationList) {
        this.informationList = informationList;
    }


}
