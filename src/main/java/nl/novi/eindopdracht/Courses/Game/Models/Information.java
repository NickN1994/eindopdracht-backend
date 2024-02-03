package nl.novi.eindopdracht.Courses.Game.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Information {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Naam onderwerp is verplicht")
    private String title;
    @NotBlank(message = "Content veld is verplicht")
    @Lob
    private String content;
    private String videoUrl;

    public Information(Long id, String title, String content, String videoUrl) {
        this.id = id;
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

}
