package nl.novi.eindopdracht.Courses.Models;


import jakarta.persistence.*;

@Entity
public class Information {

    @Id
    @GeneratedValue
    Long id;

    private String title;
    private String content;
    private String videoUrl;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Information(Long id, String title, String content, String videoUrl, Course course, Subject subject) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
        this.course = course;
        this.subject = subject;
    }

    public Information(String title, String content, String videoUrl) {
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
    }

    public Information() {
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
