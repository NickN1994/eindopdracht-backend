//package nl.novi.eindopdracht.Courses.Models;
//
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//public class Course {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String title;
//
//    @OneToMany(mappedBy = "leeromgeving")
//    private List<Information> informationList;
//
//    @ManyToMany
//    private List<Subject> subjects;
//
//    public Course(Long id, String title, List<Information> informationList, List<Subject> subjects) {
//        this.id = id;
//        this.title = title;
//        this.informationList = informationList;
//        this.subjects = subjects;
//    }
//
//    public Course(String title) {
//        this.title = title;
//    }
//
//    public Course() {
//
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public List<Information> getInformationList() {
//        return informationList;
//    }
//
//    public void setInformationList(List<Information> informationList) {
//        this.informationList = informationList;
//    }
//
//    public List<Subject> getSubjects() {
//        return subjects;
//    }
//
//    public void setSubjects(List<Subject> subjects) {
//        this.subjects = subjects;
//    }
//}
