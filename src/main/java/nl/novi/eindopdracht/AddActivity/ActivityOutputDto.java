package nl.novi.eindopdracht.AddActivity;

import java.time.LocalDate;

public class ActivityOutputDto {

    private Long id;
    private String name;
    private int participants;
    private String teacher;
    private LocalDate date;
    private String time;
    private String activityInfo;

    public ActivityOutputDto(String name, int participants, String teacher, LocalDate date, String time, String activityInfo) {
        this.name = name;
        this.participants = participants;
        this.teacher = teacher;
        this.date = date;
        this.time = time;
        this.activityInfo = activityInfo;
    }

    public ActivityOutputDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }
}



