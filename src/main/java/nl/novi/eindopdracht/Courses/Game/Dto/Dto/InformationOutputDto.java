package nl.novi.eindopdracht.Courses.Game.Dto.Dto;

import jakarta.persistence.*;
import nl.novi.eindopdracht.Courses.Game.Dto.Models.Subject;

public class InformationOutputDto {

    @Id
    @GeneratedValue
    Long id;

    private String title;
    private String content;
    private String videoUrl;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "course_id")
//    private Course course;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public InformationOutputDto(Long id, String title, String content, String videoUrl, Subject subject) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
        this.subject = subject;
    }

    public InformationOutputDto(String title, String content, String videoUrl) {
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
    }

    public InformationOutputDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
