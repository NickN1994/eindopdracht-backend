package nl.novi.eindopdracht.Courses.Game.Dto.Dto;



public class InformationInputDto {

    private Long id;
    private String title;
    private String content;
    private String videoUrl;

    public InformationInputDto(Long id, String title, String content, String videoUrl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
    }

    public InformationInputDto() {
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
